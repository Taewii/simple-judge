package judge.services;

import judge.domain.models.binding.CreateSubmissionBindingModel;
import judge.domain.models.view.DetailsSubmissionViewModel;

import java.util.Optional;

public interface SubmissionService {

    Optional<String> create(CreateSubmissionBindingModel model, String problemId, String userId);

    DetailsSubmissionViewModel findDetailsViewModelById(String id);
}
