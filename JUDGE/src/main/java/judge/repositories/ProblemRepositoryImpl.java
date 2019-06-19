package judge.repositories;

import judge.domain.entities.Problem;

import javax.ejb.Stateless;

@Stateless
public class ProblemRepositoryImpl extends BaseCrudRepository<Problem, String> implements ProblemRepository {

    @Override
    public Problem findByIdWithSubmissions(String id) {
        return super.entityManager
                .createQuery("" +
                        "SELECT p FROM Problem p " +
                        "LEFT JOIN FETCH p.submissions " +
                        "WHERE p.id = :id", Problem.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
