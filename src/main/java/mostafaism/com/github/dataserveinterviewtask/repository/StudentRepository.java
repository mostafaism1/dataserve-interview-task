package mostafaism.com.github.dataserveinterviewtask.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mostafaism.com.github.dataserveinterviewtask.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> getAllByOrderById();
}
