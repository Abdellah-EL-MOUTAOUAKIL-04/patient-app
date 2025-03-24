# Patient App
Patient App est une application Spring Boot conçue pour gérer les informations des patients avec des mécanismes d'authentification et d'autorisation sécurisés.

## Technologies utilisées
- Java
- Spring Boot
- Spring Data JPA
- Spring MVC
- Spring Security
- Maven
- H2 Database
- MySQL
- Thymeleaf

## Fonctionnalités
- Authentification et autorisation des utilisateurs
- Contrôle d'accès basé sur les rôles
- Gestion des utilisateurs en mémoire et basée sur JDBC
- Page de Gestions des patients
- Page de connexion personnalisée

## Spring Data JPA
Spring Data JPA est utilisé pour l'accès et la gestion des données. Les annotations suivantes sont couramment utilisées :
- `@Repository` : Indique que la classe est un dépôt et est utilisée pour encapsuler le stockage, la récupération et le comportement de recherche.
- `@Entity` : Spécifie que la classe est une entité et est mappée à une table de base de données.
- `@Id` : Spécifie la clé primaire d'une entité.
- `@GeneratedValue` : Fournit la spécification des stratégies de génération pour les valeurs des clés primaires.
- `@Column` : Spécifie la colonne d'une table de base de données.
- `NotEmpty` : Spécifie qu'une propriété ne doit pas être vide.
- `@OneToMany` : Spécifie une relation un-à-plusieurs entre deux entités.
- `@ManyToOne` : Spécifie une relation plusieurs-à-un entre deux entités.
- `@ManyToMany` : Spécifie une relation plusieurs-à-plusieurs entre deux entités.
- `fetch` : Ce attribut on l'ajout a l'interieur des annotation des relation pour spécifie le mode de chargement des associations. Il existe deux types :
    - `=FetchType.EAGER` : Les associations sont chargées immédiatement.
    - `=FetchType.LAZY` : Les associations sont chargées à la demande, c'est-à-dire lorsqu'elles sont réellement utilisées.
- `@JoinTable` : Spécifie la table d'association pour une relation many-to-many.
- `@JoinColumn` : Spécifie la colonne d'une table de base de données pour être utilisée pour joindre une entité.

## Spring MVC
Spring MVC est utilisé pour gérer les requêtes et réponses HTTP. Les annotations suivantes sont couramment utilisées :
- `Model` : Interface qui définit un conteneur pour les attributs de la vue, permettant de transmettre les données entre le contrôleur et la vue
- `@Controller` : Indique que la classe est un contrôleur Spring MVC.
- `@GetMapping` : Mappe les requêtes HTTP GET à des méthodes spécifiques.
- `@PostMapping` : Mappe les requêtes HTTP POST à des méthodes spécifiques.
- `@PutMapping` : Mappe les requêtes HTTP PUT à des méthodes spécifiques.
- `@DeleteMapping` : Mappe les requêtes HTTP DELETE à des méthodes spécifiques.
- `@RequestParam` : Lie les paramètres de requête aux paramètres de méthode.
- `@PathVariable` : Lie les variables de chemin aux paramètres de méthode.
- `@Valid` : Indique qu'un paramètre de méthode doit être validé en le comparant avec les contraintes déclarées au niveau de l'entité et retourne les résultats de validation dans un objet `BindingResult`, l'objet `BindingResult` va suivre toujours le parametre avec l'annotation `@Valid` dans les parametres de la fonction.
### les d'endpoints de ce projet la :
- **`GET /`** : Redirige vers `/user/index`.
- **`GET /login`** : Affiche la page de connexion.
- **`GET /admin/formPatients`** : Affiche le formulaire pour ajouter un nouveau patient. Accessible uniquement aux administrateurs.
- **`GET /user/index`** : Affiche la liste des patients avec pagination et recherche par mot-clé. Accessible aux utilisateurs authentifiés.
    - **Paramètres** :
        - `page` (int, optionnel) : Numéro de la page (par défaut : 0).
        - `size` (int, optionnel) : Taille de la page (par défaut : 5).
        - `keyword` (String, optionnel) : Mot-clé pour la recherche (par défaut : "").

- **`POST /admin/save`** : Enregistre un nouveau patient ou met à jour un patient existant. Accessible uniquement aux administrateurs.
    - **Paramètres** :
        - `patient` (Patient) : Objet patient à valider et enregistrer.
        - `bindingResult` (BindingResult) : Résultats de la validation.
        - `page` (int, optionnel) : Numéro de la page actuelle (par défaut : 0).
        - `keyword` (String, optionnel) : Mot-clé pour la recherche (par défaut : "").
- **`GET /admin/deletePatient`** : Supprime un patient par son ID. Accessible uniquement aux administrateurs.
    - **Paramètres** :
        - `id` (Long) : ID du patient à supprimer.
        - `keyword` (String) : Mot-clé pour la recherche.
        - `page` (int) : Numéro de la page actuelle.

- **`GET /admin/editPatient`** : Affiche le formulaire pour éditer un patient existant. Accessible uniquement aux administrateurs.
    - **Paramètres** :
        - `id` (Long) : ID du patient à éditer.
        - `keyword` (String) : Mot-clé pour la recherche.
        - `page` (int) : Numéro de la page actuelle.

## Thymeleaf
Thymeleaf est utilisé pour créer des templates HTML dynamiques et valider les formulaires.

### Namespaces dans Thymeleaf

Thymeleaf utilise des espaces de noms XML pour définir les attributs et éléments qu'il utilise. Voici quelques espaces de noms courants et leurs rôles :

- `xmlns:th="http://www.thymeleaf.org"` : C'est l'espace de noms principal pour Thymeleaf. Il fournit des attributs pour la liaison de données, l'itération, les conditionnels, et plus encore.
- `xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"` : Utilisé pour les fonctionnalités de mise en page (layout) avec le dialecte de mise en page Thymeleaf.

### Attributs Thymeleaf courants

- `th:text` : Définit le contenu textuel d'un élément.
- `th:if` : Inclut ou exclut conditionnellement un élément.
- `th:each` : Itère sur une collection.
- `th:href` : Définit l'attribut `href` d'une balise d'ancrage.
- `th:src` : Définit l'attribut `src` d'une balise d'image.
- `th:action` : Définit l'attribut `action` d'un formulaire.
- `th:value` : Définit la valeur d'un champ de saisie.
- `th:object` : Lie un formulaire à un objet.
- `th:field` : Lie un champ de formulaire à une propriété de l'objet lié.
- `layout:fragment` : Utilisé pour définir des fragments de mise en page réutilisables.

### Exemple d'espaces de noms Thymeleaf dans un projet

Dans votre projet, vous pourriez avoir un modèle Thymeleaf comme celui-ci :

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <title>Patient App</title>
</head>
<body>
    <h1 th:text="'Bienvenue dans l\'application Patient'"></h1>
    <form th:action="@{/admin/save}" th:object="${patient}" method="post">
        <input type="text" th:field="*{nom}" placeholder="Nom"/>
        <input type="text" th:field="*{prenom}" placeholder="Prénom"/>
        <input type="date" th:field="*{dateNaissance}" placeholder="Date de Naissance"/>
        <input type="number" th:field="*{score}" placeholder="Score"/>
        <input type="checkbox" th:field="*{malade}"/> Malade
        <button type="submit">Enregistrer</button>
    </form>
</body>
</html>
```

### Appeler une fonction de contrôleur depuis une vue Thymeleaf

Pour appeler une fonction de contrôleur depuis une vue Thymeleaf, vous utilisez l'attribut `th:action` pour les formulaires ou `th:href` pour les liens. Voici un exemple :

#### Méthode du contrôleur

```java
@GetMapping("/admin/formPatients")
public String formPatients(Model model) {
    model.addAttribute("patient", new Patient());
    return "formPatients";
}
```

#### Vue Thymeleaf

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <title>Formulaire Patients</title>
</head>
<body>
    <form th:action="@{/admin/save}" th:object="${patient}" method="post">
        <input type="text" th:field="*{nom}" placeholder="Nom"/>
        <input type="text" th:field="*{prenom}" placeholder="Prénom"/>
        <input type="date" th:field="*{dateNaissance}" placeholder="Date de Naissance"/>
        <input type="number" th:field="*{score}" placeholder="Score"/>
        <input type="checkbox" th:field="*{malade}"/> Malade
        <button type="submit">Enregistrer</button>
    </form>
</body>
</html>
```

Dans cet exemple, le formulaire soumet des données à l'endpoint `/admin/save`, qui est géré par une méthode du contrôleur.


### Modèle de mise en page (`template1.html`)

Ce fichier définit la structure générale de vos pages, y compris une barre de navigation et une section pour le contenu.

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <meta charset="UTF-8">
    <title>Titre</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    //contenu ici
</nav>
<section layout:fragment="content1">
</section>
</body>
</html>
```

### Modèle de contenu (`formPatient.html`)

Ce fichier utilise la mise en page définie dans `template1.html` et remplit le fragment `content1` avec un contenu spécifique.

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" layout:decorate="template1">
<head>
    <meta charset="UTF-8">
    <title>Formulaire Patients</title>
</head>
<body>
<div layout:fragment="content1">
    //contenu ici
</div>
</body>
</html>
```

Dans cet exemple, `formPatient.html` utilise la mise en page définie dans `template1.html` et remplit le fragment `content1` avec le contenu du formulaire.

## Sécurité avec Spring Security
Spring Security est configuré pour gérer l'authentification et l'autorisation.
-`EnableGlobalMethodSecurity` : Cette annotation est utilisée pour activer la sécurité basée sur les annotations.
    -`@PreAuthorize` : Cette annotation est utilisée pour définir des autorisations basées sur des expressions.
    -`@Secured` : Cette annotation est utilisée pour définir des autorisations basées sur des rôles.
    -`@RolesAllowed` : Cette annotation est utilisée pour définir des autorisations basées sur des rôles.

### Configuration de la chaîne de filtres de sécurité

La méthode `securityFilterChain` configure la chaîne de filtres de sécurité pour l'application. Voici une explication des différentes parties de cette configuration :

- `httpSecurity.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll());`
    - Ajoute un formulaire d'authentification personnalisé accessible à l'URL `/login`.
    - Redirige l'utilisateur vers la page d'accueil (`/`) après une connexion réussie.
    - Permet à tous les utilisateurs d'accéder à la page de connexion.

- `httpSecurity.rememberMe(Customizer.withDefaults());`
    - Active la fonctionnalité "Se souvenir de moi" avec les paramètres par défaut.

- `httpSecurity.authorizeHttpRequests(ar -> ar.requestMatchers("/webjars/**","/h2-console/**").permitAll());`
    - Permet l'accès public aux ressources statiques (comme Bootstrap) et à la console H2.

- `httpSecurity.authorizeHttpRequests(ar -> ar.anyRequest().authenticated());`
    - Exige que toutes les autres requêtes soient authentifiées.

- `httpSecurity.exceptionHandling(exception -> exception.accessDeniedPage("/notAuthorized"));`
    - Redirige les utilisateurs vers la page `/notAuthorized` en cas de tentative d'accès à une ressource sans les autorisations nécessaires.

- `httpSecurity.userDetailsService(userDetailServiceImpl);`
    - Utilise le service `UserDetailServiceImpl` pour charger les détails des utilisateurs.

Cette configuration permet de gérer l'authentification et l'autorisation des utilisateurs dans l'application.

### Authentification en mémoire
L'authentification en mémoire est utilisée pour gérer les utilisateurs directement dans le code sans base de données. Voici un exemple de configuration :

```java
@Bean
public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    return new InMemoryUserDetailsManager(
        User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
        User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ADMIN").build()
    );
}
```

### Authentification JDBC
L'authentification JDBC utilise une base de données pour stocker les informations des utilisateurs. Voici un exemple de configuration avec une source de données :

```java
@Bean
public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);
}
```

### Service UserDetails
Le service `UserDetailsService` est utilisé pour charger les détails des utilisateurs à partir d'une source de données personnalisée. Voici un exemple d'implémentation :

```java
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(appUser.getUsername(), appUser.getPassword(), appUser.getRoles());
    }
}
```

## Configuration de la base de données
Mettez à jour la configuration de la base de données dans `application.properties` :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase?createDatabaseIfNotExist=true
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.defer-datasource-iitialization=true
spring.sql.init.mode=always
spring.jpa.hibernate.ddl-auto=update
#celui la qui au dessus permet de creer la base de donnee si elle n'existe pas et de mettre a jour les tables si il y a des modifications dans les entites  (update)
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
spring.h2.console.enabled=true
```

## Installation et exécution
1. Clonez le dépôt :
    ```sh
    git clone https://github.com/Abdellah-EL-MOUTAOUAKIL-04/patient-app.git
    cd patient-app
    ```

2. Construisez le projet avec Maven :
    ```sh
    mvn clean install
    ```

3. Exécutez l'application :
    ```sh
    mvn spring-boot:run
    ```

## Utilisation
- Accédez à l'application à `http://localhost:8080`
- Connectez-vous avec les utilisateurs par défaut :
    - Nom d'utilisateur : `user1`, Mot de passe : `1234`
    - Nom d'utilisateur : `user2`, Mot de passe : `1234`
    - Nom d'utilisateur : `admin`, Mot de passe : `1234`

## Captures d'écran

### Page de connexion
![Page de connexion](/images/auth-page.png)

### Page d'accueil
![Page d'accueil](/images/home-page.png)

### Formulaire de modification patients
![Formulaire de modification patients](/images/editPatient-page.png)

### Formulaire d'ajout de patients
![Formulaire d'ajout de patients](/images/addPatient-page.png)