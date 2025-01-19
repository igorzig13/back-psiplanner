package web2.dev.backpsiplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web2.dev.backpsiplanner.model.Clinic;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    List<Clinic> findByProfessionalsIsNotEmpty();

    List<Clinic> findByProfessionalsIsNotEmptyAndNameContainingIgnoreCase(String name);

    List<Clinic> findByProfessionalsIsNotEmptyAndLocationContainingIgnoreCase(String location);

}
