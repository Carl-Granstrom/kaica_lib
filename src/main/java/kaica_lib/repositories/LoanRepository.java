package kaica_lib.repositories;

import kaica_lib.entities.Loan;
import kaica_lib.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    public Optional<Loan> findLoansByUser(User user);
}
