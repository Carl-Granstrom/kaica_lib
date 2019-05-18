package kaica_lib.entities;

public class LoanWrapper {

    private Loan loan;

    public LoanWrapper() {}

    public LoanWrapper(Loan loan) { this.loan = loan; }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
