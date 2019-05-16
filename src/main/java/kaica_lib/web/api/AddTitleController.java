package kaica_lib.web.api;

import kaica_lib.entities.Title;
import kaica_lib.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RequestMapping(path = "/librarian_home/add_title")
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

        return "redirect:/librarian_home/add_title";
    }

    @PostMapping("/redirect")
    public String redirect() {
        return "redirect:/search_add_copy";
    }

}
