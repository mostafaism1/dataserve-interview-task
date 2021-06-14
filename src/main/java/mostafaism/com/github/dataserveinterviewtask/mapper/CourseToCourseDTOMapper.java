package mostafaism.com.github.dataserveinterviewtask.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import mostafaism.com.github.dataserveinterviewtask.dto.CourseDTO;
import mostafaism.com.github.dataserveinterviewtask.model.Course;

@Component
public class CourseToCourseDTOMapper implements Function<Course, CourseDTO> {

    @Override
    public CourseDTO apply(Course c) {
        return c == null ? null : CourseDTO.builder().id(c.getId()).name(c.getName()).build();
    }

}
