package judge.repositories;

import judge.domain.entities.Identifiable;

import java.util.List;

public interface CrudRepository<Entity extends Identifiable<Id>, Id> {

    Entity findOne(Id id);

    List<Entity> findAll();

    <T> List<Entity> findByAttributeAndValue(String attributeName, T value);

    void save(Entity entity);

    Entity update(Entity entity);

    void delete(Entity entity);

    void deleteById(Id entityId);
}
