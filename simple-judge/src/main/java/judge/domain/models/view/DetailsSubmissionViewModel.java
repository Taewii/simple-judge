package judge.domain.models.view;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DetailsSubmissionViewModel {

    private String id;
    private String problemName;
    private String userUsername;
    private List<String> code;
    private LocalDateTime createdOn;
    private Integer problemPoints;
    private Integer achievedResult;
}
