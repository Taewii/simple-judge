package judge.repositories;

import judge.domain.entities.Submission;

import javax.ejb.Stateless;

@Stateless
public class SubmissionRepositoryImpl extends BaseCrudRepository<Submission, String> implements SubmissionRepository {
}
