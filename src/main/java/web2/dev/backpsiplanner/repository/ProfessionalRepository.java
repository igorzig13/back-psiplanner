package web2.dev.backpsiplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web2.dev.backpsiplanner.model.Professional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
}
