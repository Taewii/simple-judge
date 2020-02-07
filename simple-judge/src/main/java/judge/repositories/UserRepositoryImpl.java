package judge.repositories;

import judge.domain.entities.User;

import javax.ejb.Stateless;

@Stateless
public class UserRepositoryImpl extends BaseCrudRepository<User, String> implements UserRepository {
}
