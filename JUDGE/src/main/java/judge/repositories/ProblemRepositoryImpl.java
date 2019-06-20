package judge.repositories;

import judge.domain.entities.Problem;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ProblemRepositoryImpl extends BaseCrudRepository<Problem, String> implements ProblemRepository {

    @Override
    public Problem findByIdWithSubmissions(String id) {
        return super.entityManager
                .createQuery("" +
                        "SELECT p FROM Problem p " +
                        "LEFT JOIN FETCH p.submissions s " +
                        "WHERE p.id = :id " +
                        "ORDER BY s.createdOn DESC", Problem.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Problem> findAllWithUserSubmissions() {
        return super.entityManager
                .createQuery("" +
                        "SELECT DISTINCT(p) FROM Problem p " +
                        "LEFT JOIN FETCH p.submissions", Problem.class)
                .getResultList();
    }
}
