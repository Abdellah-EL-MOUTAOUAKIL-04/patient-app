package ma.abdellah.patientapp.repository;

import ma.abdellah.patientapp.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByPrenomContainsIgnoreCaseOrNomContainsIgnoreCase(String prenom, String nom, Pageable pageable);
}
