package judge.services;

import judge.domain.entities.User;
import judge.domain.models.binding.UserLoginBindingModel;
import judge.domain.models.binding.UserRegisterBindingModel;
import judge.domain.models.service.UserServiceModel;
import judge.domain.models.view.LoggedInUserViewModel;
import judge.repositories.UserRepository;
import judge.util.ModelValidator;
import judge.util.PasswordHash;
import org.modelmapper.ModelMapper;

import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean register(UserRegisterBindingModel user) {
        String violationsMessage = ModelValidator.validateModel(user);
        if (violationsMessage != null || !user.getPassword().equals(user.getConfirmPassword())) {
            return false;
        }

        try {
            user.setPassword(PasswordHash.createHash(user.getPassword()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        User entity = this.mapper.map(user, User.class);

        try {
            this.userRepository.save(entity);
        } catch (EJBTransactionRolledbackException e) {
            return false;
        }

        return true;
    }

    @Override
    public Optional<LoggedInUserViewModel> login(UserLoginBindingModel model) {
        String violationsMessage = ModelValidator.validateModel(model);
        if (violationsMessage != null) {
            throw new IllegalArgumentException(violationsMessage);
        }

        return this.userRepository.findByAttributeAndValue("username", model.getUsername())
                .stream()
                .filter(user -> {
                    boolean valid = false;
                    try {
                        valid = PasswordHash.validatePassword(model.getPassword(), user.getPassword());
                    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                        e.printStackTrace();
                    }

                    return valid;
                })
                .map(user -> this.mapper.map(user, LoggedInUserViewModel.class))
                .findFirst();
    }

    @Override
    public void update(User user) {
        this.userRepository.update(user);
    }

    @Override
    public UserServiceModel getUserById(String id) {
        return this.mapper.map(this.userRepository.findOne(id), UserServiceModel.class);
    }
}
