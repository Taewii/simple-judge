package judge.web.beans.submission;

import judge.domain.models.view.DetailsSubmissionViewModel;
import judge.services.SubmissionService;
import judge.web.beans.BaseBean;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
@NoArgsConstructor
public class DetailsSubmissionBean extends BaseBean {

    private DetailsSubmissionViewModel submission;

    private SubmissionService submissionService;

    @Inject
    public DetailsSubmissionBean(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostConstruct
    private void init() {
        String id = super.externalContext.getRequestParameterMap().get("id");
        this.submission = this.submissionService.findDetailsViewModelById(id);
    }

    public DetailsSubmissionViewModel getSubmission() {
        return this.submission;
    }
}
