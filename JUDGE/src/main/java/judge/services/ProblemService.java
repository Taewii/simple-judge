package judge.services;

import judge.domain.models.binding.CreateProblemBindingModel;
import judge.domain.models.service.ProblemServiceModel;
import judge.domain.models.view.DetailsProblemViewModel;
import judge.domain.models.view.HomeProblemViewModel;

import java.util.List;

public interface ProblemService {

    boolean create(CreateProblemBindingModel model);

    DetailsProblemViewModel findByIdWithSubmissions(String id);

    ProblemServiceModel getProblemById(String id);

    String getProblemNameById(String id);

    List<HomeProblemViewModel> findAll();
}
