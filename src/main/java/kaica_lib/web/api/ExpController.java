package kaica_lib.web.api;

import kaica_lib.entities.Copy;
import kaica_lib.entities.Title;
import kaica_lib.repositories.CopyRepository;
import kaica_lib.repositories.TitleRepository;
import kaica_lib_system.AddTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@ControllerAdvice
@RequestMapping(path = "/search_add")
public class ExpController {

    private CopyRepository copyRepository;
    private TitleRepository titleRepository;

    @Autowired
    public ExpController(CopyRepository copyRepository, TitleRepository titleRepository) {
        this.copyRepository = copyRepository;
        this.titleRepository = titleRepository;
    }

    @GetMapping("/search_add")
    public ResponseEntity<Title> titleByTitleName(@PathVariable("titleName") String titleName) {
        Optional<Title> optTitle = titleRepository.findFirst20ByTitleNameContaining(titleName);
        if (optTitle.isPresent()) {
            return new ResponseEntity<>(optTitle.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("titles", titleRepository.findAll());
        return "redirect:/";
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

        return "redirect:/search_add";
    }

}
