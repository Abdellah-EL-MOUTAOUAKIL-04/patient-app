package ma.abdellah.patientapp.security.repo;

import ma.abdellah.patientapp.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
}
