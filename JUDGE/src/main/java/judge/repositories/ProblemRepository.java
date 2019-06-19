package judge.repositories;

import judge.domain.entities.Problem;


public interface ProblemRepository extends CrudRepository<Problem, String> {

    Problem findByIdWithSubmissions(String id);
}
