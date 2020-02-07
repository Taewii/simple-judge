package judge.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateSubmissionBindingModel {

    @NotNull
    private String code;
}
