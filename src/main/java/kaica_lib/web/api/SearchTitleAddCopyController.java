package kaica_lib.web.api;

import kaica_lib.entities.*;
import kaica_lib.repositories.CopyRepository;
import kaica_lib.repositories.CopyTypeRepository;
import kaica_lib.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RequestMapping(path = "/search_add_copy")
@SessionAttributes({"search_string", "search_result", "selected_title", "titles"})
public class SearchTitleAddCopyController {

    private CopyRepository copyRepository;
    private TitleRepository titleRepository;
    private CopyTypeRepository copyTypeRepository;

    @Autowired
    public SearchTitleAddCopyController(CopyRepository copyRepository, TitleRepository titleRepository) {
        this.copyRepository = copyRepository;
        this.titleRepository = titleRepository;
    }

    @ModelAttribute(name = "search_result")
    public List<Title> getTitles() {
        List<Title> titles = new ArrayList<>();
        return titles;
    }

    @ModelAttribute(name = "selected_title")
    public Title getSelectedTitle() {
        Title title = new Title();
        return title;
    }

    @GetMapping
    public String showSearch(Model model) {
        TitleSearchFormCommand command = new TitleSearchFormCommand();
        model.addAttribute("command", command);

        return "searchTitleForm";
    }

    @PostMapping
    public String showSearchResult(
            @ModelAttribute("command") TitleSearchFormCommand command, Model model) {

        List<Title> titles = titleRepository.findFirst20ByNameContaining(command.getTitleSearchString());
        model.addAttribute("titles", titles);

        return "redirect:/search_add_copy/display_search_results";
    }

    @GetMapping("/display_search_results")
    public String displaySearchResult(@SessionAttribute List<Title> titles,
                                      Model model) {

        model.addAttribute("titles", titles);

        return "searchResult";
    }

    @PostMapping("/display_search_results")
    public String selectTitle(@SessionAttribute("selected_title") Title title,
                              @ModelAttribute("title") Title selectedTitle) {

        title = selectedTitle;
        return "redirect:/search_add_copy/display_search_results/add_selected";
    }

    @GetMapping("/display_search_results/add_selected")
    public String displaySelectedTitle(Model model) {

        return "selectedTitle";
    }

    @PostMapping("/display_search_results/add_selected")
    public String postCopy(@ModelAttribute("copy") Copy copy, @SessionAttribute("selected_title") Title title,
                           Model model) {

        copy.setTitle(title);
        copy.setStatus("available");
        copy.setRetDate(LocalDate.now());

        //TODO STATIC CREATION
        CopyType copyType = new NormalCopyType(copy);

        copy.setCopyType(copyType);
        //TODO add the CopyType objects as well
        copyRepository.save(copy);

        return "redirect:search_add_copy/display_search_results/add_selected";
    }

}
