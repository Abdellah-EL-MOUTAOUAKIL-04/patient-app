package ma.abdellah.patientapp.web;

import jakarta.validation.Valid;
import ma.abdellah.patientapp.entities.Patient;
import ma.abdellah.patientapp.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PatientController {
    private PatientRepository patientRepository;
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    
    @GetMapping("/index")
    public String index(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
    @RequestParam(name = "size", defaultValue = "5") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> pagePatients = patientRepository.findByPrenomContainsIgnoreCaseOrNomContainsIgnoreCase(keyword,keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/deletePatient")
    public String deletePatient(@RequestParam(name = "id") Long id,int page,String keyword) {
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/editPatient")
    public String editPatients(Model model, Long id ,String keyword, int page) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient", patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }

    @PostMapping("/save")
    public String savePatient(Model model, @Valid Patient patient, BindingResult bindingResult, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
}
