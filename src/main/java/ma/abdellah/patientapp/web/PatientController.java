package ma.abdellah.patientapp.web;

import ma.abdellah.patientapp.entities.Patient;
import ma.abdellah.patientapp.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PatientController {
    private PatientRepository patientRepository;
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    
    @GetMapping("/index")
    public String index(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("listPatients", patients);
        return "patients";
    }
}
