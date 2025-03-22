package ma.abdellah.patientapp;

import ma.abdellah.patientapp.entities.Patient;
import ma.abdellah.patientapp.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.time.LocalDate;

@SpringBootApplication
public class PatientAppApplication {

    PatientRepository patientRepository;
    PatientAppApplication(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PatientAppApplication.class, args);
    }
    //@Bean
    public CommandLineRunner start() {
        return args -> {
            patientRepository.save(new Patient(null,"abdellah","el moutaouakil", LocalDate.of(2004,2,26),80,false));
            patientRepository.save(new Patient(null,"ahmed","el moutaouakil", LocalDate.of(1999,4,3),90,false));
            patientRepository.save(new Patient(null,"zahra","el moutaouakil", LocalDate.of(2002,1,21),70,false));
            patientRepository.save(new Patient(null,"salah","el moutaouakil", LocalDate.of(2000,9,8),30,false));
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        return args -> {
            UserDetails user = jdbcUserDetailsManager.loadUserByUsername("user11");
            if(user==null){
                jdbcUserDetailsManager.createUser(User.withUsername("user11").password(passwordEncoder().encode("1234")).roles("USER").build());
                jdbcUserDetailsManager.createUser(User.withUsername("user22").password(passwordEncoder().encode("1234")).roles("USER").build());
                jdbcUserDetailsManager.createUser(User.withUsername("admin2").password(passwordEncoder().encode("1234")).roles("USER","ADMIN").build());
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
