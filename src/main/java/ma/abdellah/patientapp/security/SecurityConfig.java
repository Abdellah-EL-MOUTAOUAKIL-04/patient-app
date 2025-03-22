package ma.abdellah.patientapp.security;

import lombok.AllArgsConstructor;
import ma.abdellah.patientapp.security.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
    PasswordEncoder passwordEncoder;
    UserDetailServiceImpl userDetailServiceImpl;
    //@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    //@Bean
    //cette fonction permet de gerer l'authentification des utilisateurs en utilisant un utilisateur en memoire(inMemoryAuthentication) il existe d'autre methode tels que jdbc, ldap, etc
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
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
        httpSecurity.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll());
        httpSecurity.rememberMe(Customizer.withDefaults());
        //cela pour permet a spring security de permettre a acceder a bootstrap depuis la page de login
        httpSecurity.authorizeHttpRequests(ar -> ar.requestMatchers("/webjars/**","/h2-console/**").permitAll());
        //en peut remplacer si deux ligne au dessous qui permet de gerer les autorisation en utilisant l'annotation enablemethodesecurity
        //httpSecurity.authorizeHttpRequests(ar->ar.requestMatchers("/user/**").hasRole("USER"));
        //httpSecurity.authorizeHttpRequests(ar->ar.requestMatchers("/admin/**").hasRole("ADMIN"));
        //en utilisant sa en peut acceder a aucune chose dans notre app
        httpSecurity.authorizeHttpRequests(ar -> ar.anyRequest().authenticated());
        httpSecurity.exceptionHandling(exception->exception.accessDeniedPage("/notAuthorized"));
        //il faut ajouter celui la au dessous si vous voulez travaille avec userdetailsService
        httpSecurity.userDetailsService(userDetailServiceImpl);
        return httpSecurity.build();
    }
}
