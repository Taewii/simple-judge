package judge.web.beans.problem;

import judge.domain.models.view.DetailsProblemViewModel;
import judge.services.ProblemService;
import judge.web.beans.BaseBean;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
@NoArgsConstructor
public class DetailsProblemBean extends BaseBean {

    private DetailsProblemViewModel problem;

    private ProblemService problemService;

    @Inject
    public DetailsProblemBean(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostConstruct
    private void init() {
        String id = super.externalContext.getRequestParameterMap().get("id");
        this.problem = this.problemService.findDetailsModelById(id);
    }

    public DetailsProblemViewModel getProblem() {
        return this.problem;
    }
}
