package judge.web.beans.user;

import judge.domain.models.binding.UserLoginBindingModel;
import judge.services.UserService;
import judge.web.beans.BaseBean;
import lombok.NoArgsConstructor;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Model
@NoArgsConstructor
public class UserLoginBean extends BaseBean {

    private UserLoginBindingModel model = new UserLoginBindingModel();

    private UserService userService;

    @Inject
    public UserLoginBean(UserService userService) {
        this.userService = userService;
    }

    public void login() {
        this.userService.login(this.model).ifPresentOrElse(user -> {
            HttpSession session = (HttpSession) super.externalContext.getSession(true);
            session.setAttribute("user-id", user.getId());
            session.setAttribute("username", user.getUsername());
            super.redirect("/");
        }, super.addMessageRunnable("Login failed. Check username or password"));
    }

    public UserLoginBindingModel getModel() {
        return this.model;
    }
}
