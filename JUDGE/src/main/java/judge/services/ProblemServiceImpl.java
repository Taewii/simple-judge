package judge.services;

import judge.domain.entities.Problem;
import judge.domain.models.binding.CreateProblemBindingModel;
import judge.domain.models.service.ProblemServiceModel;
import judge.domain.models.view.DetailsProblemViewModel;
import judge.domain.models.view.HomeProblemViewModel;
import judge.repositories.ProblemRepository;
import judge.util.ModelValidator;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final ModelMapper mapper;

    @Inject
    public ProblemServiceImpl(ProblemRepository problemRepository, ModelMapper mapper) {
        this.problemRepository = problemRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean create(CreateProblemBindingModel model) {
        if (!ModelValidator.isValid(model)) {
            return false;
        }

        this.problemRepository.save(this.mapper.map(model, Problem.class));
        return true;
    }

    @Override
    public DetailsProblemViewModel findByIdWithSubmissions(String id) {
        return this.mapper.map(this.problemRepository.findByIdWithSubmissions(id), DetailsProblemViewModel.class);
    }

    @Override
    public ProblemServiceModel getProblemById(String id) {
        return this.mapper.map(this.problemRepository.findOne(id), ProblemServiceModel.class);
    }

    @Override
    public String getProblemNameById(String id) {
        return this.problemRepository.findOne(id).getName();
    }

    @Override
    public List<HomeProblemViewModel> findAll() {
        return this.problemRepository
                .findAll()
                .stream()
                .map(problem -> this.mapper.map(problem, HomeProblemViewModel.class))
                .collect(Collectors.toList());
    }
}
