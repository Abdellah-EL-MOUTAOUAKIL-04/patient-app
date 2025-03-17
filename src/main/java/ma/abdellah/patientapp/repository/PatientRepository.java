package ma.abdellah.patientapp.repository;

import ma.abdellah.patientapp.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
