package kaica_lib.web.api;

import kaica_lib.entities.Copy;
import kaica_lib.entities.Title;
import kaica_lib.repositories.CopyRepository;
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

    @GetMapping
    public String addCopy(Model model) {
        model.addAttribute("copy", new Copy());
        model.addAttribute("title", titleRepository.getOne(1l));

        return "addCopyForm";
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("copies", copyRepository.findAll());
        return "redirect:/";
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public String postCopy( @ModelAttribute("copy") Copy copy, @ModelAttribute("title") Title title) {
        title = titleRepository.getOne(1l);
        //TODO needs to search by title, or id or smthn. Logic could become somewhat complex,
        // using placeholder method of using known, static, ID for now
        copy.setTitle(title);
        //TODO add the CopyType objects as well
        copyRepository.save(copy);

        return "redirect:/add_copy";
    }

}
