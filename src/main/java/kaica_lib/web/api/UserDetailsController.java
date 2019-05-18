package kaica_lib.web.api;

import kaica_lib.entities.Loan;
import kaica_lib.entities.User;
import kaica_lib.repositories.CopyRepository;
import kaica_lib.repositories.TitleRepository;
import kaica_lib.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        //TODO PLACEHOLDER! Right now this is causing a bug that recreates user Sven Svennis again after
        // details have been changed for him.
        User newUser = new User("Sven Svennis","Svennegatan 2", new ArrayList<Loan>());
        userRepository.save(newUser);

        //TODO this is where we need to fetch the current user instead!
        User user = userRepository.getOne(1l);

        model.addAttribute("user", user);

        return "displayUser";
    }

    @PostMapping
    public String editDetails(Model model) {

        return "redirect:/patron_home/view_my_details/edit_details";
    }

    @GetMapping(path = "/edit_details")
    public String displayEditForm(@ModelAttribute User user) {

        return "userDetailsForm";
    }

    @PutMapping(path = "/edit_details")
    public String editUserDetails(@ModelAttribute("user") User user, Model model) {

        //TODO this is where we need to fetch the current user instead!
        User currUser = userRepository.getOne(1l);

        if (!(user.getId() == null)) {
            currUser.setId(user.getId());
        }
        if (!(user.getName() == null)) {
            currUser.setName(user.getName());
        }
        if (!(user.getAddress() == null)) {
            currUser.setAddress(user.getAddress());
        }

        userRepository.save(currUser);

        model.addAttribute("user", user);

        return "redirect:/patron_home/view_my_details";
    }
}
