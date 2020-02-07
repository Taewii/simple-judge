package judge.domain.models.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreateProblemBindingModel {

    @NotBlank
    private String name;

    @Min(0)
    @NotNull
    private Integer points;
}
