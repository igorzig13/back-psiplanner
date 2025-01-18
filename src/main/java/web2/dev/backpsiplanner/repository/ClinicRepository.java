package web2.dev.backpsiplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web2.dev.backpsiplanner.model.Clinic;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
