package mostafaism.com.github.dataserveinterviewtask.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import mostafaism.com.github.dataserveinterviewtask.dto.request.CreateCourseRequest;
import mostafaism.com.github.dataserveinterviewtask.model.Course;

@Component
public class CreateCourseRequestToCourseMapper implements Function<CreateCourseRequest, Course> {

    @Override
    public Course apply(CreateCourseRequest cDTO) {
        return cDTO == null ? null : Course.builder().name(cDTO.getName()).build();
    }

}
