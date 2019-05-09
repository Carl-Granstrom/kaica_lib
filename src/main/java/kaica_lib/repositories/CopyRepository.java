package kaica_lib.repositories;

import kaica_lib.entities.Author;
import kaica_lib.entities.Copy;
import kaica_lib.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;


public interface CopyRepository extends JpaRepository<Copy, Long> {}
