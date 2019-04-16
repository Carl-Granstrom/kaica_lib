package kaica_lib.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "author_id", updatable = false, nullable = false)
    private Long id;

    @Type(type="uuid-char")
    @Column(nullable=false, unique=true)
    final private UUID uuid = UUID.randomUUID();

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    //TODO This could also be a class, or an enum, but I don't think we want to go any further
    //     down that rabbit hole
    @Basic
    @Column(name = "nationality")
    private String nationality;

    //todo: book does not use @temporal, needed?
    //todo this might not work as intended if the entity is not persisted before it's used? Think!
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    // ********************** Accessor Methods ********************** //

    // ********************** Model Methods ********************** //

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }


    // ********************** Common Methods ********************** //



    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Author)) {
            return false;
        }
        Author author = (Author) obj;
        return uuid != null && uuid.equals(author.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
