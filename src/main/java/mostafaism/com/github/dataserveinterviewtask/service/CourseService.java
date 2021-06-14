package mostafaism.com.github.dataserveinterviewtask.service;

import java.util.List;

import mostafaism.com.github.dataserveinterviewtask.dto.request.CreateCourseRequest;
import mostafaism.com.github.dataserveinterviewtask.model.Course;

public interface CourseService {

    public Course save(CreateCourseRequest createCourseRequest);

    public List<Course> getAll();

    public Course getById(Long courseId);

    public void delete(Long id);

}
