package mostafaism.com.github.dataserveinterviewtask.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mostafaism.com.github.dataserveinterviewtask.dto.request.CreateCourseRequest;
import mostafaism.com.github.dataserveinterviewtask.exception.InvalidArgumentException;
import mostafaism.com.github.dataserveinterviewtask.mapper.CreateCourseRequestToCourseMapper;
import mostafaism.com.github.dataserveinterviewtask.model.Course;
import mostafaism.com.github.dataserveinterviewtask.repository.CourseRepository;

@Service
@Transactional
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CreateCourseRequestToCourseMapper createCourseRequestToCourseMapper;

    @Override
    public Course save(CreateCourseRequest createCourseRequest) {
        Course course = createCourseRequestToCourseMapper.apply(createCourseRequest);
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.getAllByOrderById();
    }

    @Override
    public Course getById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new InvalidArgumentException("Could not find course with id = " + courseId));
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

}
