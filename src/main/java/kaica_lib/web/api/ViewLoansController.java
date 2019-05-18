package kaica_lib.web.api;

import kaica_lib.entities.Loan;
import kaica_lib.entities.LoanWrapper;
import kaica_lib.entities.User;
import kaica_lib.repositories.CopyRepository;
import kaica_lib.repositories.LoanRepository;
import kaica_lib.repositories.TitleRepository;
import kaica_lib.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/patron_home/view_loans")
@SessionAttributes("loanWrapper")
public class ViewLoansController {

    private TitleRepository titleRepository;
    private UserRepository userRepository;
    private CopyRepository copyRepository;
    private LoanRepository loanRepository;
    private Long selId;

    @Autowired
    ViewLoansController(TitleRepository titleRepository,
                        UserRepository userRepository,
                        CopyRepository copyRepository, LoanRepository loanRepository) {
        this.titleRepository = titleRepository;
        this.userRepository = userRepository;
        this.copyRepository = copyRepository;
        this.loanRepository = loanRepository;
    }

    @ModelAttribute(name = "loans")
    public List<Loan> getLoans() {
        //TODO get current user instead of user with id 2
        User user = userRepository.getOne(2l);

        List<Loan> loans = user.getLoans();

        return loans;
    }

    @ModelAttribute(name = "loanWrapper")
    public LoanWrapper makeForm() {
        LoanWrapper loan = new LoanWrapper();

        return loan;
    }

    @GetMapping
    public String viewLoans(@ModelAttribute("loans") List<Loan> loans, Model model) {

        model.addAttribute("loans", loans);

        return "loanView";
    }

    @PostMapping(path = "/cancel")
    public String cancelLoan(@ModelAttribute LoanWrapper loanWrapper) {

        /**
         * TODO this code doesn't work, but is never called since i disabled renew loan
         * TODO besides this code was originally written for loan deletion
         * the problem is that the wrapper object contains a null reference to a loan, and I can't manage to populate
         * the loanWrapper.loan field properly with the selected title from "loanView"
         */
        selId = loanWrapper.getLoan().getId();
        loanRepository.delete(loanRepository.getOne(selId));

        return "redirect:/patron_home/view_loans";
    }

}
