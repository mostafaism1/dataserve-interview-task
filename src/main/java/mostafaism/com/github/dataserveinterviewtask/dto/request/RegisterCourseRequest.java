package mostafaism.com.github.dataserveinterviewtask.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterCourseRequest {
    private Long studentId;
    private Long courseId;
}
