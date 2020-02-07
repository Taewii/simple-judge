package judge.repositories;

import judge.domain.entities.Problem;

import java.util.List;


public interface ProblemRepository extends CrudRepository<Problem, String> {

    Problem findByIdWithSubmissions(String id);

    List<Problem> findAllWithUserSubmissions();
}
