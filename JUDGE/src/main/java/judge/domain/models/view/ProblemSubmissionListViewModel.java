package judge.domain.models.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProblemSubmissionListViewModel {

    private String id;
    private String userUsername;
    private Integer achievedResult;
    private Integer achievedPercentage;
}
