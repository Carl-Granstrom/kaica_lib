package kaica_lib.web.api;

import kaica_lib.entities.Title;
import kaica_lib.repositories.TitleRepository;
import kaica_lib_system.AddTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping(path = "/add_title")
@SessionAttributes("title")
public class AddTitleController {

    private TitleRepository titleRepository;

    @Autowired
    public AddTitleController(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @GetMapping
    public String addTitle(Model model) {
        model.addAttribute("title", new Title());
        return "addTitleForm";
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("titles", titleRepository.findAll());
        return "redirect:/";
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public String postTitle( @ModelAttribute Title title) {
        //TODO Return date logic goes here
        //title.setRetDate(LocalDate.now().plusMonths(2));
        //title.setStatus("available");
        titleRepository.save(title);

        return "redirect:/add_title";
    }

}
