package judge.domain.entities;

public interface Identifiable<Id> {

    Id getId();

    void setId(Id id);
}
