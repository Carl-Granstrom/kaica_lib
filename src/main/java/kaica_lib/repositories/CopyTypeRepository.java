package kaica_lib.repositories;

import kaica_lib.entities.CopyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyTypeRepository extends JpaRepository<CopyType, Long> {}