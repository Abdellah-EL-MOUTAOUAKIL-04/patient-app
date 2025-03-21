package ma.abdellah.patientapp.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig{

    private PasswordEncoder passwordEncoder;

    @Bean
    //cette fonction permet de gerer l'authentification des utilisateurs en utilisant un utilisateur en memoire(inMemoryAuthentication) il existe d'autre methode tels que jdbc, ldap, etc
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        //la fonction va recuperer l'objet passwordEncoder qui est un objet de type PasswordEncoder et qui est injecter dans la classe HospitalAppApplication grace a l'annotation @Bean
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ADMIN","USER").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //pour ajouter un formulaire d'authentification
        httpSecurity.formLogin(form -> form.loginPage("/login").permitAll());
        //en utilisant sa en peut acceder a aucune chose dans notre app
        httpSecurity.authorizeHttpRequests(ar -> ar.anyRequest().authenticated());
        return httpSecurity.build();
    }
}
