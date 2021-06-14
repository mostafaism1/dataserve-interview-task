package mostafaism.com.github.dataserveinterviewtask.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import mostafaism.com.github.dataserveinterviewtask.dto.StudentDTO;
import mostafaism.com.github.dataserveinterviewtask.model.Student;

@Component
public class StudentToStudentDTOMapper implements Function<Student, StudentDTO> {

    @Override
    public StudentDTO apply(Student s) {
        return s == null ? null
                : StudentDTO.builder().id(s.getId()).firstName(s.getFirstName()).lastName(s.getLastName())
                        .address(s.getAddress()).email(s.getEmail()).phone(s.getPhone()).build();
    }

}
