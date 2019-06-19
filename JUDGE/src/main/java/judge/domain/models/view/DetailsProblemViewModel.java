package judge.domain.models.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DetailsProblemViewModel {

    private String id;
    private String name;
    private Integer points;
    private List<DetailsSubmissionViewModel> submissions;
}
