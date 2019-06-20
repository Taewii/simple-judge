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

    // TODO: 20.6.2019 г. this class should be redundant, but I don't know how to load the problem name
    //  from the CreateSubmissionBean and also handle the post request from the form
    @Inject
    public CreateSubmissionViewBean(ProblemService problemService) {
        String problemId = super.externalContext.getRequestParameterMap().get("id");
        this.problemName = problemService.getProblemNameById(problemId);
    }
}
