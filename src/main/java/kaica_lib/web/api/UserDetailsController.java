package kaica_lib.web.api;

import kaica_lib.entities.Loan;
import kaica_lib.entities.User;
import kaica_lib.repositories.CopyRepository;
import kaica_lib.repositories.TitleRepository;
import kaica_lib.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@ControllerAdvice
@RequestMapping(path = "/patron_home/view_my_details")
public class UserDetailsController {

    private TitleRepository titleRepository;
    private UserRepository userRepository;
    private CopyRepository copyRepository;

    @Autowired
    UserDetailsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String displayUser(Model model) {

        User newUser = new User("Sven Svennis","Svennegatan 2", new ArrayList<Loan>());
        userRepository.save(newUser);

        User user = userRepository.getOne(1l);

        model.addAttribute("user", user);

        return "displayUser";
    }

    @PostMapping
    public String editDetails(Model model) {

        return "redirect:/patron_home/view_my_details/edit_details";
    }

    @GetMapping(path = "/patron_home/view_my_details/edit_details")
    public String displayEditForm() {

        return "userDetailsForm";
    }

    /**
     *
     
    private User addTestData() {
        Title title = new Title("Spring MVC");
        titleRepository.save(title);

        Copy copy = new Copy(title, "normal");
        copyRepository.save(copy);


        User user = new User("Sven Svennis","Svennegatan 2", new ArrayList<Loan>());

        Loan loan = new Loan(copy, user);

        user.addLoan(loan);

        userRepository.save(user);

        return user;
    }
     */
}
