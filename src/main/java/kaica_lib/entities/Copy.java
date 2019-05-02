package kaica_lib.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "copy")
public class Copy {

    @Id
    @GeneratedValue
    @Column(name = "copy_id", updatable = false, nullable = false)
    private Long id;

    @Type(type="uuid-char")
    @Column(nullable=false, unique=true)
    final private UUID uuid = UUID.randomUUID();

    @Basic
    @Column(name = "status")
    private String status;

    @OneToOne
    private Title title;

    //TODO store on loan only?
    @Column(name = "copy_return_date")
    private LocalDate retDate;

    @Basic
    @Column(name = "copy_created_at")
    private LocalDate createdAt;

    /**
     * Required Hibernate no-args-constructor.
     */
    public Copy() {}


    /**
     * Constructor.
     * TODO lots of things to consider here, will require extensive refactoring as the logic develops
     * TODO might not even be used
     */
    public Copy(Title title) {
        this.title = title;
        this.status = "available";
        //todo placeholder, add logic
        this.retDate = LocalDate.now();
    }

    // ********************** Accessor Methods ********************** //

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //TODO think about where retDate logic goes
    //TODO PLACEHOLDER, PASS TO CopyType-object
    /**
     * @return the date that was set to the return date
     */
    public LocalDate setRetDate(LocalDate retDate) {

        this.retDate = retDate;

        return retDate;
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
        if(!(obj instanceof Copy)) {
            return false;
        }
        Copy copy = (Copy) obj;
        return uuid != null && uuid.equals(copy.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
