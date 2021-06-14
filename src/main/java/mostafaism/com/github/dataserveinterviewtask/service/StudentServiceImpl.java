package mostafaism.com.github.dataserveinterviewtask.service;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mostafaism.com.github.dataserveinterviewtask.dto.request.CreateStudentRequest;
import mostafaism.com.github.dataserveinterviewtask.dto.request.RegisterCourseRequest;
import mostafaism.com.github.dataserveinterviewtask.exception.InvalidArgumentException;
import mostafaism.com.github.dataserveinterviewtask.exception.StudentAlreadyRegisteredException;
import mostafaism.com.github.dataserveinterviewtask.mapper.CreateStudentRequestToStudentMapper;
import mostafaism.com.github.dataserveinterviewtask.model.Course;
import mostafaism.com.github.dataserveinterviewtask.model.Student;
import mostafaism.com.github.dataserveinterviewtask.repository.CourseRepository;
import mostafaism.com.github.dataserveinterviewtask.repository.StudentRepository;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CreateStudentRequestToStudentMapper createStudentRequestToStudentMapper;

    @Override
    public Student save(CreateStudentRequest createStudentRequest) {
        Student student = createStudentRequestToStudentMapper.apply(createStudentRequest);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.getAllByOrderById();
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new InvalidArgumentException("Could not find student with id = " + id));
    }

    @Override
    public void registerCourse(RegisterCourseRequest registerCourseRequest) {
        final Long studentId = registerCourseRequest.getStudentId();
        final Long courseId = registerCourseRequest.getCourseId();
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new InvalidArgumentException("Could not find student with id = " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new InvalidArgumentException("Could not find course with id = " + courseId));
        List<Course> courses = student.getCourses();
        if (courses.contains(course)) {
            throw new StudentAlreadyRegisteredException(student, course);
        }
        courses.add(course);
        student.setCourses(courses);
        studentRepository.save(student);
    }

    @Override
    public void batchRegisterCourse(List<RegisterCourseRequest> registerCourseRequests) {
        /*
         * There are multiple ways to synchronize multiple threads:
         * 
         * 1. Thread.join() -> dis-advantage: very low level.
         * 
         * 2. CountDownLatch -> dis-advantage: requires that we know the exact number of
         * threads.
         * 
         * 3. ExecutorService.awaitTermination()
         * 
         * 4. Streams.parallelStream() -> advantages: clear, readable and declarative.
         * 
         * I will use the CountDownLatch approach, since I know how many threads are
         * required.
         */

        // Initialize a thread pool.
        ExecutorService pool = Executors.newFixedThreadPool(registerCourseRequests.size());

        // Initialize a latch with the number of registration requests.
        CountDownLatch latch = new CountDownLatch(registerCourseRequests.size());

        for (RegisterCourseRequest registerCourseRequest : registerCourseRequests) {
            pool.submit(() -> {
                try {
                    registerCourse(registerCourseRequest);
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            // block until the latch count is 0.
            latch.await();
            log.info("Success");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

}
