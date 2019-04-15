package kaica_lib.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Title superclass containing title specific information.
 */

@Entity
@Table(name = "Title")
public class Title {

    @Id
    @Type(type="uuid-char")
    @Column(nullable=false, unique=true)
    protected UUID uuid = UUID.randomUUID();

    @Basic
    @Column(name = "title")
    public String title;

    @Basic
    @Column(name = "status")
    public String status;

    //todo placeholder, many-many relationship
    @Basic
    @Column(name = "author")
    public String author;

    @Temporal(TemporalType.DATE)
    private Date retDate;

    //todo: book does not use @temporal, needed?
    @Temporal(TemporalType.DATE)
    private Date createdAt;



    // ********************** Accessor Methods ********************** //

    // ********************** Model Methods ********************** //

    //todo placeholder with strings, enum?
    //todo possibly throw exceptions down here instead of if/else?
    //todo think about where we want to handle return dates
    public boolean loan() {
        if (this.status.equals("available")) {
            this.status = "loaned";
            return true;
        } else {
            return false;
        }

    }

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
