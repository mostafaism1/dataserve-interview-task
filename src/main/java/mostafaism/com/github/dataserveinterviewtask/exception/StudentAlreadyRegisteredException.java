package mostafaism.com.github.dataserveinterviewtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import mostafaism.com.github.dataserveinterviewtask.model.Course;
import mostafaism.com.github.dataserveinterviewtask.model.Student;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentAlreadyRegisteredException extends RuntimeException {

    public StudentAlreadyRegisteredException(Student s, Course c) {
        super("Student " + s.toString() + " is already registered in the course " + c.toString());
    }

}
