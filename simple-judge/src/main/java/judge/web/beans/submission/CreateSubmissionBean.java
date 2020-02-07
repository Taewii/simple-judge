package judge.web.beans.submission;

import judge.domain.models.binding.CreateSubmissionBindingModel;
import judge.services.SubmissionService;
import judge.web.beans.BaseBean;
import lombok.NoArgsConstructor;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
@NoArgsConstructor
public class CreateSubmissionBean extends BaseBean {

    private CreateSubmissionBindingModel model = new CreateSubmissionBindingModel();

    private SubmissionService submissionService;

    @Inject
    public CreateSubmissionBean(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    public void create() {
        String problemId = super.externalContext.getRequestHeaderMap().get("referer").substring(38);
        String userId = (String) super.externalContext.getSessionMap().get("user-id");

        this.submissionService.create(this.model, problemId, userId)
                .ifPresentOrElse(sub -> super.redirect("/submissions/details/" + sub),
                        () -> super.redirect("/problems/submit/" + problemId));
    }

    public CreateSubmissionBindingModel getModel() {
        return this.model;
    }
}
