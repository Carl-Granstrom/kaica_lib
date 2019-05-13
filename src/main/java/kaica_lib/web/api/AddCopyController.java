package kaica_lib.web.api;

import kaica_lib.entities.Copy;
import kaica_lib.entities.Title;
import kaica_lib.repositories.CopyRepository;
import kaica_lib.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@ControllerAdvice
@RequestMapping(path = "/add_copy")
public class AddCopyController {

    private CopyRepository copyRepository;
    private TitleRepository titleRepository;

    @Autowired
    public AddCopyController(CopyRepository copyRepository, TitleRepository titleRepository) {
        this.copyRepository = copyRepository;
        this.titleRepository = titleRepository;
    }

    @ModelAttribute("title")
    public Title getTitle() {
        //TODO replace with proper text search
        Title title = null;

        return title;
    }

    @GetMapping
    public String addCopy(Model model) {
        model.addAttribute("copy", new Copy());
        //todo here we fill with the search later
        model.addAttribute("title", new Title());

        return "addCopyForm";
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public String postCopy( @ModelAttribute("copy") Copy copy, @ModelAttribute("title") Title title) {
        title = titleRepository.getOne(1l);
        //TODO needs to search by title, or id or smthn. Logic could become somewhat complex,
        // using placeholder method of using known, static, ID for now
        copy.setTitle(title);
        copy.setStatus("available");
        //TODO PLACEHOLDER, move out of copy?
        copy.setRetDate(LocalDate.now());
        //TODO add the CopyType objects as well
        copyRepository.save(copy);

        return "redirect:/add_copy";
    }

}
