package ma.abdellah.patientapp;

import ma.abdellah.patientapp.entities.Patient;
import ma.abdellah.patientapp.repository.PatientRepository;
import ma.abdellah.patientapp.security.service.AccountService;
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
            patientRepository.save(new Patient(null,"yasser","el moutaouakil", LocalDate.of(2002,1,21),70,false));
            patientRepository.save(new Patient(null,"salah","el moutaouakil", LocalDate.of(2000,9,8),30,false));
        };
    }

    //@Bean
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

    //@Bean
    public CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1","1234","user1@gmail.com","1234");
            accountService.addNewUser("user2","1234","user2@gmail.com","1234");
            accountService.addNewUser("admin","1234","user3@gmail.com","1234");
            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("admin","USER");
            accountService.addRoleToUser("admin","ADMIN");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
