package mostafaism.com.github.dataserveinterviewtask.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import mostafaism.com.github.dataserveinterviewtask.dto.StudentDTO;
import mostafaism.com.github.dataserveinterviewtask.dto.request.CreateStudentRequest;
import mostafaism.com.github.dataserveinterviewtask.dto.request.RegisterCourseRequest;
import mostafaism.com.github.dataserveinterviewtask.mapper.StudentToStudentDTOMapper;
import mostafaism.com.github.dataserveinterviewtask.model.Student;
import mostafaism.com.github.dataserveinterviewtask.service.StudentService;

@RestController
@RequestMapping(path = "/students", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class StudentController {

    private final StudentService service;
    private final StudentToStudentDTOMapper mapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public StudentDTO createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        Student student = service.save(createStudentRequest);
        return convert(student);
    }

    @PostMapping(path = "/courses/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registerCourse(@RequestBody RegisterCourseRequest registerCourseRequest) {
        service.registerCourse(registerCourseRequest);
    }

    @PostMapping(path = "/courses/register/batch")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void batchRegisterCourse(@RequestBody List<RegisterCourseRequest> registerCourseRequests) {
        service.batchRegisterCourse(registerCourseRequests);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public StudentDTO getStudent(@PathVariable("id") Long id) {
        Student student = service.getById(id);
        return convert(student);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<StudentDTO> getAllStudents() {
        List<Student> students = service.getAll();
        return convertAll(students);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("id") Long id) {
        service.delete(id);
    }

    // helper methods
    private StudentDTO convert(Student student) {
        return mapper.apply(student);
    }

    private List<StudentDTO> convertAll(List<Student> students) {
        return students.stream().map(mapper).collect(Collectors.toList());
    }

}
