package judge.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "problems")
public class Problem extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    private String name;

    @Min(0)
    @NotNull
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer points;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Submission> submissions = new ArrayList<>();
}
