package kaica_lib.repositories;

import kaica_lib.entities.Author;
import kaica_lib.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;


public interface TitleRepository extends PagingAndSortingRepository<Title, Long> {

    /**
     * Searches for the substring in the title field and returns the first 20 hits.
     *
     * @param name      the substring entered in the searchfield
     * @return          a list of the 20 first hits
     */
    public List<Title> findFirst20ByNameContaining(String name);

    /**
     * todo READ MORE! DECIDE
     * exactly how the repositories/services need to cooperate to achieve this is uncertain
     * my current idea is to have an author repository implement a similar search method
     * and a SearchService-class which can in some way coordinate the results.
     *
     * it would be really interesting to read more about how to do smart searching on multiple
     * attributes.
     */
    public Title findTitleByAuthor(Author author);

    // ********************** JpaRepository methods ********************** //

    // public Title findById(Long id);
    // public void save(Title title)

}
