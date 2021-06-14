package mostafaism.com.github.dataserveinterviewtask.service;

import java.util.List;

import mostafaism.com.github.dataserveinterviewtask.dto.request.CreateStudentRequest;
import mostafaism.com.github.dataserveinterviewtask.dto.request.RegisterCourseRequest;
import mostafaism.com.github.dataserveinterviewtask.model.Student;

public interface StudentService {
    public Student save(CreateStudentRequest createStudentRequest);

    public List<Student> getAll();

    public Student getById(Long id);

    public void registerCourse(RegisterCourseRequest registerCourseRequest);

    // Performs a batch registration in parallel.
    public void batchRegisterCourse(List<RegisterCourseRequest> registerCourseRequests);

    public void delete(Long id);
}
