package judge.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserLoginBindingModel {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
