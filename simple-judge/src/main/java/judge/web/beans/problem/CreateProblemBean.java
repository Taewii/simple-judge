package judge.web.beans.problem;

import judge.domain.models.binding.CreateProblemBindingModel;
import judge.services.ProblemService;
import judge.web.beans.BaseBean;
import lombok.NoArgsConstructor;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
@NoArgsConstructor
public class CreateProblemBean extends BaseBean {

    private CreateProblemBindingModel model = new CreateProblemBindingModel();

    private ProblemService problemService;

    @Inject
    public CreateProblemBean(ProblemService problemService) {
        this.problemService = problemService;
    }

    public void create() {
        problemService.create(model)
                .ifPresentOrElse(super::addMessage, () -> super.redirect("/"));
    }

    public CreateProblemBindingModel getModel() {
        return this.model;
    }
}
