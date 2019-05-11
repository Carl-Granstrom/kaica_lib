package kaica_lib.entities;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Title class containing title specific information.
 */

@Entity
@Table(name = "title")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "title_id", updatable = false, nullable = false)
    private Long id;

    @Type(type="uuid-char")
    @Column(nullable=false, unique=true)
    final private UUID uuid = UUID.randomUUID();

    //TODO ADD ONETOMANY FOR COPY!

    @Basic
    @Column(name = "title_name")
    private String titleName;

    //todo this might not work as intended if the entity is not persisted before it's used? Think!
    private LocalDate createdAt;

    /**
     * Required Hibernate no-args-constructor.
     */
    public Title() {}


    /**
     * Constructor.
     * TODO lots of things to consider here, will require extensive refactoring as the logic develops
     * TODO might not even be used
     */
    public Title(String titleName) {
        this.titleName = titleName;
    }

    // ********************** Accessor Methods ********************** //

    public String getTitleName() {
        return this.titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }


    // ********************** Model Methods ********************** //

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDate.now();
    }


    // ********************** Common Methods ********************** //

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Title)) {
            return false;
        }
        Title title = (Title) obj;
        return uuid != null && uuid.equals(title.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
