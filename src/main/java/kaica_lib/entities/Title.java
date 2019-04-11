package kaica_lib.entities;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
}
