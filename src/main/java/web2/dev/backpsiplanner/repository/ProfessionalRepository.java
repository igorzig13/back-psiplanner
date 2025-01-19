package web2.dev.backpsiplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web2.dev.backpsiplanner.model.Professional;

import java.util.List;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    List<Professional> findByClinicIsNull();

    @Query("SELECT p FROM Professional p " +
            "JOIN p.person person " +
            "WHERE p.clinic IS NULL " +
            "AND (LOWER(person.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(person.lastName) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<Professional> findByClinicIsNullFilteredByName(String name);

    List<Professional> findByClinicIsNullAndLocationContainingIgnoreCase(String location);

    List<Professional> findProfessionalsByClinicId(Long clinicId);
}
