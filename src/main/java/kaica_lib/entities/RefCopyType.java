package kaica_lib.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("REF")
public class RefCopyType extends CopyType {

    /**
     * Required Hibernate no-args-constructor.
     */
    protected RefCopyType() {}

    protected RefCopyType(Copy copy) {
        super.setCopy(copy);
        super.setLoanTimeInWeeks(0);
    }
}
