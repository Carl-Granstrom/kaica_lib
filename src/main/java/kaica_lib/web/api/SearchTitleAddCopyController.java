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
@SessionAttributes({"titles", "sTitle"})
public class SearchTitleAddCopyController {

    private CopyRepository copyRepository;
    private TitleRepository titleRepository;
    private CopyTypeRepository copyTypeRepository;
    //TODO a bit of a hack, use RedirectAttributes instead
    private Long selId;

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

    @ModelAttribute(name = "sTitle")
    public TitleWrapper setUpTitle() {
        return new TitleWrapper();
    }

    @ModelAttribute(name = "tmpId")
    public Long getId() {
        //todo sentinel, placeholder?
        return 0l;
    }

    @ModelAttribute(name = "wrapper")
    public TitleSearchListWrapper getWrapper() {
        TitleSearchListWrapper wrapper = new TitleSearchListWrapper();
        return wrapper;
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
    public String selectTitle(
            @ModelAttribute("sTitle") TitleWrapper sTitle,
            Model model) {
        System.out.println("in searchResult postmapping: ");
        System.out.println(sTitle.toString());

        System.out.println(sTitle.getTitle().getId());
        selId = sTitle.getTitle().getId();
        Title tmp = titleRepository.getOne(sTitle.getTitle().getId());
        Long tmpId = tmp.getId();
        model.addAttribute("tmpTitleId", tmpId);

        return "redirect:/search_add_copy/display_search_results/add_selected";
    }

    @GetMapping("/display_search_results/add_selected")
    public String displaySelectedTitle(Model model) {

        return "selectedTitle";
    }

    @PostMapping("/display_search_results/add_selected")
    public String postCopy(@ModelAttribute("copy") Copy copy, @ModelAttribute("tmpTitle") Title tmpTitle,
                           Model model) {

        System.out.println("in add_selected postMapping: ");
        System.out.println(tmpTitle.getName());
        copy.setTitle(titleRepository.getOne(selId));
        copy.setStatus("available");
        copy.setRetDate(LocalDate.now());

        //TODO STATIC CREATION
        CopyType copyType = new NormalCopyType(copy);

        copy.setCopyType(copyType);
        //TODO add the CopyType objects as well
        copyRepository.save(copy);

        return "redirect:/search_add_copy/display_search_results/add_selected";
    }

    public Long getSelId() {
        return selId;
    }

    public void setSelId(Long selId) {
        this.selId = selId;
    }
}
