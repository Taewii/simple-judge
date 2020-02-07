package judge.domain.models.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemSubmissionListViewModel {

    private String id;
    private String userUsername;
    private Integer achievedResult;
    private Integer achievedPercentage;
}
