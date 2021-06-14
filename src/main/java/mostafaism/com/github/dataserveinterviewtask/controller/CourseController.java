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
import mostafaism.com.github.dataserveinterviewtask.dto.CourseDTO;
import mostafaism.com.github.dataserveinterviewtask.dto.request.CreateCourseRequest;
import mostafaism.com.github.dataserveinterviewtask.mapper.CourseToCourseDTOMapper;
import mostafaism.com.github.dataserveinterviewtask.model.Course;
import mostafaism.com.github.dataserveinterviewtask.service.CourseService;

@RestController
@RequestMapping(path = "/courses", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CourseController {

    private final CourseService service;
    private final CourseToCourseDTOMapper mapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO createCourse(@RequestBody CreateCourseRequest createCourseRequest) {
        Course course = service.save(createCourseRequest);
        return convert(course);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CourseDTO getCourse(@PathVariable("id") Long id) {
        Course course = service.getById(id);
        return convert(course);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = service.getAll();
        return convertAll(courses);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable("id") Long id) {
        service.delete(id);
    }

    // helper methods
    private CourseDTO convert(Course course) {
        return mapper.apply(course);
    }

    private List<CourseDTO> convertAll(List<Course> courses) {
        return courses.stream().map(mapper).collect(Collectors.toList());
    }

}
