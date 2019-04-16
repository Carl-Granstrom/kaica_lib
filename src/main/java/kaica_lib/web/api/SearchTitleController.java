package kaica_lib.web.api;

import kaica_lib.entities.Title;
import kaica_lib.repositories.TitleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/search_title",
        produces = "application/json")
@CrossOrigin(origins="*")
public class SearchTitleController {
    private TitleRepository resultSet;

    //todo This is not a working search function, needs adapting
    @GetMapping("/search_title")
    public Iterable<Title> searchResultSet() {
        PageRequest page = PageRequest.of(0, 20, Sort.by("author").ascending());
        return resultSet.findAll(page).getContent();
    }

    /**
     * Handles get-requests to search by Title id.
     * @param id the Long identifier of the Title
     * @return the Title-object if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Title> searchById(@PathVariable("id") Long id) {
        Optional<Title> optTitle = resultSet.findById(id);
        if (((Optional) optTitle).isPresent()) {
            return new ResponseEntity<>(optTitle.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
