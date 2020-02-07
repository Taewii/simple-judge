package judge.domain.models.view;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DetailsProblemViewModel {

    private String id;
    private String name;
    private Integer points;
    private List<ProblemSubmissionListViewModel> submissions;
    private Double successRate;
}
