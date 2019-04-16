package kaica_lib.entities;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Title superclass containing title specific information.
 */

@Entity
@Table(name = "title")
public class Title {

    @Id
    @GeneratedValue
    @Column(name = "title_id", updatable = false, nullable = false)
    private Long id;

    @Type(type="uuid-char")
    @Column(nullable=false, unique=true)
    final private UUID uuid = UUID.randomUUID();

    //todo read up on hibernate ISBN validator
    //todo placeholder, replace with some kind of ISBN-type?
    //todo think about ISBN10/13, how to?
    @NaturalId
    @Column(name = "ISBN")
    private String isbn;

    @Basic
    @Column(name = "title_name")
    private String titleName;

    @Basic
    @Column(name = "status")
    private String status;

    //todo placeholder, many-many relationship
    @Basic
    @Column(name = "author")
    private String author;

    //todo Store somewhere else?
    @Temporal(TemporalType.DATE)
    private Date retDate;

    //todo: book does not use @temporal, needed?
    //todo this might not work as intended if the entity is not persisted before it's used? Think!
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    /**
     * Required Hibernate no-args-constructor.
     */
    public Title() {}


    /**
     * Constructor.
     * TODO lots of things to consider here, will require extensive refactoring as the logic develops
     */
    public Title(String isbn, String titleName, String author) {
        this.isbn = isbn;
        this.titleName = titleName;
        this.status = "available";
        this.author = author;
        //todo placeholder, add logic
        Date today = new Date();
        this.retDate = today;
    }

    // ********************** Accessor Methods ********************** //

    public String getIsbn() {
        return this.isbn;
    }

    public String getTitleName() {
        return this.titleName;
    }

    public String getStatus() {
        return this.status;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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
