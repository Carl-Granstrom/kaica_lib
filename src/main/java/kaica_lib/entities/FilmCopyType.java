package kaica_lib.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FILM")
public class FilmCopyType extends CopyType {

    /**
     * Required Hibernate no-args-constructor.
     */
    protected FilmCopyType() {}

    protected FilmCopyType(Copy copy) {
        super.setCopy(copy);
        //TODO fetch value from DB instead
        super.setLoanTimeInWeeks(1);
    }
}
