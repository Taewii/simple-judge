package judge.repositories;

import judge.domain.entities.Identifiable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseCrudRepository<Entity extends Identifiable<Id>, Id> implements CrudRepository<Entity, Id> {

    @PersistenceContext(unitName = "judge")
    protected EntityManager entityManager;
    private Class<Entity> entityClass;

    protected BaseCrudRepository() {
        this.entityClass = initEntityClass();
    }

    @Override
    public Entity findOne(Id id) {
        return this.entityManager.find(this.entityClass, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Entity> findAll() {
        return this.entityManager
                .createQuery("FROM " + this.entityClass.getName())
                .getResultList();
    }

    @Override
    public void save(Entity entity) {
        this.entityManager.persist(entity);
    }

    @Override
    public Entity update(Entity entity) {
        return this.entityManager.merge(entity);
    }

    @Override
    public void delete(Entity entity) {
        this.entityManager.remove(entity);
    }

    @Override
    public void deleteById(Id entityId) {
        Entity entity = this.findOne(entityId);
        this.delete(entity);
    }

    @Override
    public <T> List<Entity> findByAttributeAndValue(String attributeName, T value) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entity> cQuery = builder.createQuery(this.entityClass);
        Root<Entity> root = cQuery.from(this.entityClass);
        cQuery.select(root).where(builder.equal(root.get(attributeName), value));
        TypedQuery<Entity> query = entityManager.createQuery(cQuery);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    private Class<Entity> initEntityClass() {
        return (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}