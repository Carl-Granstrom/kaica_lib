package kaica_lib.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.InvalidObjectException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long id;

    @Type(type="uuid-char")
    @Column(nullable=false, unique=true)
    final private UUID uuid = UUID.randomUUID();

    @Basic
    @Column(name = "user_name")
    private String name;

    @Basic
    @Column(name = "user_adress")
    private String adress;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("returnDate DESC")
    private List<Loan> loans;

    private LocalDate createdAt;

    /**
     * Required Hibernate no-args-constructor.
     */
    public User() {}

    public User(String name, String adress, ArrayList<Loan> loans) {
        this.name = name;
        this.adress = adress;
        this.loans = loans;
    }

    // ********************** Accessor Methods ********************** //

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() { return this.id; }

    public void setId(Long id) { this.id = id; }

    public String getAdress() { return adress; }

    public void setAdress(String adress) { this.adress = adress; }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    // ********************** Model Methods ********************** //

    public void addLoan(Loan loan) { this.loans.add(loan); }

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDate.now();
    }

    void makeLoan(Copy copy) throws InvalidObjectException {
        if (copy.getLoanTimeInWeeks() == 0) {
            throw new InvalidObjectException("Magazines and reference literature can not be loaned");
        }
        this.loans.add(new Loan(copy, this));
    }


    // ********************** Common Methods ********************** //

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return uuid != null && uuid.equals(user.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
