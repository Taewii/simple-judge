package judge.web.beans.submission;

import judge.services.ProblemService;
import judge.web.beans.BaseBean;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
@NoArgsConstructor
@Getter
public class CreateSubmissionViewBean extends BaseBean {

    private String problemName;

    @Inject
    public CreateSubmissionViewBean(ProblemService problemService) {
        String problemId = super.externalContext.getRequestParameterMap().get("id");
        problemName = problemService.getProblemNameById(problemId);
    }
}
