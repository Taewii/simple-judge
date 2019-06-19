package judge.services;

import judge.domain.entities.User;
import judge.domain.models.binding.UserLoginBindingModel;
import judge.domain.models.binding.UserRegisterBindingModel;
import judge.domain.models.service.UserServiceModel;
import judge.domain.models.view.LoggedInUserViewModel;

import java.util.Optional;

public interface UserService {

    boolean register(UserRegisterBindingModel user);

    Optional<LoggedInUserViewModel> login(UserLoginBindingModel model);

    void update(User user);

    UserServiceModel getUserById(String id);
}
