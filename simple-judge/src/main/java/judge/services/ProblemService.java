package judge.services;

import judge.domain.models.binding.CreateProblemBindingModel;
import judge.domain.models.service.ProblemServiceModel;
import judge.domain.models.view.DetailsProblemViewModel;
import judge.domain.models.view.HomeProblemViewModel;

import java.util.List;
import java.util.Optional;

public interface ProblemService {

    Optional<String> create(CreateProblemBindingModel model);

    DetailsProblemViewModel findDetailsModelById(String id);

    ProblemServiceModel getProblemById(String id);

    String getProblemNameById(String id);

    List<HomeProblemViewModel> findAll(String userId);
}
