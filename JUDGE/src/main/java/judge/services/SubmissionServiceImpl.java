package judge.services;

import judge.domain.entities.Problem;
import judge.domain.entities.Submission;
import judge.domain.entities.User;
import judge.domain.models.binding.CreateSubmissionBindingModel;
import judge.domain.models.service.ProblemServiceModel;
import judge.repositories.SubmissionRepository;
import judge.util.ModelValidator;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Arrays;
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
    public boolean create(CreateSubmissionBindingModel model, String problemId, String userId) {
        if (!ModelValidator.isValid(model)) {
            return false;
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

        this.submissionRepository.save(entity);
        return true;
    }
}
