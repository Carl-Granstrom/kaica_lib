package kaica_lib.repositories;

import kaica_lib.entities.Loan;
import kaica_lib.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    public List<Loan> FindLoansByUser(User user);
}
