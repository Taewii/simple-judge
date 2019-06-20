package judge.services;

import judge.domain.entities.Problem;
import judge.domain.entities.Submission;
import judge.domain.entities.User;
import judge.domain.models.binding.CreateSubmissionBindingModel;
import judge.domain.models.service.ProblemServiceModel;
import judge.domain.models.view.DetailsSubmissionViewModel;
import judge.repositories.SubmissionRepository;
import judge.util.JavaScriptCodeExecutor;
import judge.util.ModelValidator;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.script.ScriptException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserService userService;
    private final ProblemService problemService;
    private final ModelMapper mapper;

    @Inject
    public SubmissionServiceImpl(SubmissionRepository submissionRepository,
                                 UserService userService,
                                 ProblemService problemService,
                                 ModelMapper mapper) {
        this.submissionRepository = submissionRepository;
        this.userService = userService;
        this.problemService = problemService;
        this.mapper = mapper;
    }


    @Override
    public Optional<String> create(CreateSubmissionBindingModel model, String problemId, String userId) {
        if (!ModelValidator.isValid(model)) {
            return Optional.empty();
        }

        Random random = new Random();
        ProblemServiceModel problemById = this.problemService.getProblemById(problemId);

        Submission entity = this.mapper.map(model, Submission.class);
        entity.setUser(this.mapper.map(this.userService.getUserById(userId), User.class));
        entity.setProblem(this.mapper.map(problemById, Problem.class));
        entity.setCreatedOn(LocalDateTime.now());
        entity.setCode((Arrays.stream(model.getCode()
                .split("\r\n"))
                .filter(n -> !n.isBlank()))
                .collect(Collectors.toList()));
        entity.setAchievedResult(random.ints(1, 0, problemById.getPoints()).findFirst().orElse(0));

        try {
            Object functionResult = JavaScriptCodeExecutor.executeScript(model.getCode());
            if (functionResult.equals(entity.getProblem().getPoints())) {
                entity.setAchievedResult(entity.getProblem().getPoints());
            }
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
            return Optional.empty();

        }

        Submission submission = this.submissionRepository.update(entity);
        return Optional.of(submission.getId());
    }

    @Override
    public DetailsSubmissionViewModel findDetailsViewModelById(String id) {
        return this.mapper.map(this.submissionRepository.findOne(id), DetailsSubmissionViewModel.class);
    }
}
