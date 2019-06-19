package judge.web.beans;

import judge.domain.models.view.HomeProblemViewModel;
import judge.services.ProblemService;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
@NoArgsConstructor
public class HomeBean {

    private List<HomeProblemViewModel> problems;

    private ProblemService problemService;

    @Inject
    public HomeBean(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostConstruct
    private void init() {
        this.problems = this.problemService.findAll();
    }

    public List<HomeProblemViewModel> getProblems() {
        return this.problems;
    }
}
