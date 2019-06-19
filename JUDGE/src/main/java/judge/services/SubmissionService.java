package judge.services;

import judge.domain.models.binding.CreateSubmissionBindingModel;

public interface SubmissionService {

    boolean create(CreateSubmissionBindingModel model, String problemId, String userId);
}
