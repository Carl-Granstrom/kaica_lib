package kaica_lib.web.api;

import kaica_lib.entities.Title;
import kaica_lib.repositories.TitleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(path = "/add_title")
@CrossOrigin(origins="*")
public class AddTitleController {

    private TitleRepository titleRepository;

    //todo This needs a lot of work, currently doesn't work. Confused. Read more.
    @GetMapping
    public String showAddTitleForm(Model model) {
        String isbn = "1";
        String title = "The Book";
        String status = "available";
        String author = "The Author";
        Date retDate = new Date();
        model.addAttribute("title", new Title(isbn, title, status, author, retDate));
        return "title";
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Title postTitle(@RequestBody Title title) {
        return titleRepository.save(title);
    }

}
