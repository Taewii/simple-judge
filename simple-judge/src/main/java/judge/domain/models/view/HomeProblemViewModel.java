package judge.domain.models.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HomeProblemViewModel {

    private String id;
    private String name;
    private Integer completion;
}
