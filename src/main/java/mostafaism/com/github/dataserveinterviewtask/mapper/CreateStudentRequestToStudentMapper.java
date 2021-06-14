package mostafaism.com.github.dataserveinterviewtask.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import mostafaism.com.github.dataserveinterviewtask.dto.request.CreateStudentRequest;
import mostafaism.com.github.dataserveinterviewtask.model.Student;

@Component
public class CreateStudentRequestToStudentMapper implements Function<CreateStudentRequest, Student> {

    @Override
    public Student apply(CreateStudentRequest createStudentRequest) {
        return createStudentRequest == null ? null
                : Student.builder().firstName(createStudentRequest.getFirstName())
                        .lastName(createStudentRequest.getLastName()).address(createStudentRequest.getAddress())
                        .email(createStudentRequest.getEmail()).phone(createStudentRequest.getPhone()).build();
    }

}
