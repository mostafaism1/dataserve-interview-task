package mostafaism.com.github.dataserveinterviewtask.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mostafaism.com.github.dataserveinterviewtask.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> getAllByOrderById();
}
