package kaica_lib.web.api;

import kaica_lib.entities.Title;
import kaica_lib.entities.TitleSearchFormCommand;
import kaica_lib.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/patron_home/patron_search")
public class PatronSearchController {

    TitleRepository titleRepository;
    List<Title> titles = new ArrayList<>();

    @Autowired
    public PatronSearchController(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @ModelAttribute(name = "titles")
    public List<Title> getTitles() {
        List<Title> titles = new ArrayList<>();
        return titles;
    }

    @GetMapping
    public String showSearch(Model model) {
        TitleSearchFormCommand command = new TitleSearchFormCommand();

        model.addAttribute("command", command);

        return "patronSearchTitleForm";
    }

    @PostMapping
    public String showSearchResult(
            @ModelAttribute("command") TitleSearchFormCommand command, Model model) {

        titles.clear();

        for (Title t : titleRepository.findFirst20ByNameContaining(command.getTitleSearchString())) {

            if (!(titles.contains(t))) {
                titles.add(t);
            }
        }

        model.addAttribute("titles", titles);

        return "redirect:/patron_home/patron_search/display_search_results";
    }

    @GetMapping("/display_search_results")
    public String displaySearchResult(Model model) {

        model.addAttribute("titles", titles);

        return "patronSearchResult";
    }
}
