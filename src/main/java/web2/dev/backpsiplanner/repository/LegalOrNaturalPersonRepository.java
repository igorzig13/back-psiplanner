package web2.dev.backpsiplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web2.dev.backpsiplanner.model.LegalOrNaturalPerson;

@Repository
public interface LegalOrNaturalPersonRepository extends JpaRepository<LegalOrNaturalPerson, Long> {
}
