package judge.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "submissions")
public class Submission extends BaseEntity {

    @ElementCollection
    @CollectionTable(
            name = "submission_code",
            joinColumns = @JoinColumn(name = "submission_id"))
    @Column(columnDefinition = "TEXT")
    private List<String> code;

    @Min(0)
    @NotNull
    @Column(name = "achieved_result", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer achievedResult;

    @PastOrPresent
    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "problem_id", referencedColumnName = "id", nullable = false)
    private Problem problem;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
