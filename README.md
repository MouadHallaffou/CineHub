# 🎬 CinéHub - Système de Gestion de Catalogue de Films

![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![Spring](https://img.shields.io/badge/Spring-6.2.10-green.svg)
![Hibernate](https://img.shields.io/badge/Hibernate-6.6.4-blue.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

## 📋 Table des Matières

- [Description](#-description)
- [Fonctionnalités](#-fonctionnalités)
- [Architecture](#-architecture)
- [Technologies](#-technologies)
- [Modèle de Données](#-modèle-de-données)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Utilisation](#-utilisation)
- [API Endpoints](#-api-endpoints)
- [Tests](#-tests)
- [Règles de Gestion](#-règles-de-gestion)
- [Contribuer](#-contribuer)

## 📖 Description

**CinéHub** est une application de gestion de catalogue cinématographique développée en Java avec Spring Framework. Elle permet de gérer de manière centralisée les films, les réalisateurs et les catégories, tout en respectant les bonnes pratiques de programmation orientée objet et l'architecture MVC en couches.

L'application offre une solution complète pour :
- 🎥 Gérer un catalogue de films avec toutes leurs métadonnées
- 🎬 Administrer les informations des réalisateurs
- 📁 Organiser les films par catégories
- 🔍 Rechercher et filtrer efficacement le contenu

## ✨ Fonctionnalités

### 🎥 Gestion des Films
- ➕ Ajouter un film au catalogue
- ✏️ Modifier les informations d'un film existant
- 🗑️ Supprimer un film du catalogue
- 📋 Consulter la liste complète des films
- 🔍 Rechercher des films par titre, année ou catégorie
- 📄 Consulter les détails complets d'un film (avec réalisateur et catégorie)
- 🎯 Filtrer les films par année de sortie ou note minimale

### 🎬 Gestion des Réalisateurs
- ➕ Ajouter un nouveau réalisateur
- ✏️ Modifier les informations d'un réalisateur
- 🗑️ Supprimer un réalisateur (si aucun film associé)
- 📋 Consulter la liste des réalisateurs
- 🎞️ Consulter la filmographie complète d'un réalisateur
- 🔍 Rechercher un réalisateur par nom

### 📁 Gestion des Catégories
- ➕ Ajouter une nouvelle catégorie de films
- ✏️ Modifier une catégorie existante
- 🗑️ Supprimer une catégorie (si aucun film associé)
- 📋 Consulter la liste des catégories
- 🎬 Consulter tous les films d'une catégorie donnée

## 🏗️ Architecture

L'application suit une **architecture MVC multi-couches** :

```
┌─────────────────────────────────────┐
│         Controller Layer            │  ← REST API Endpoints
├─────────────────────────────────────┤
│          Service Layer              │  ← Business Logic
├─────────────────────────────────────┤
│        Repository Layer             │  ← Data Access (JPA)
├─────────────────────────────────────┤
│         Database (MySQL)            │  ← Persistence
└─────────────────────────────────────┘
```

### Couches de l'Application

- **Controller** : Gestion des requêtes HTTP et des réponses REST
- **Service** : Logique métier et règles de gestion
- **Repository** : Accès aux données via Spring Data JPA
- **Model** : Entités JPA (Film, Director, Category)
- **DTO** : Objets de transfert de données
- **Mapper** : Conversion entre entités et DTOs (MapStruct)
- **Exception** : Gestion centralisée des erreurs
- **Config** : Configuration Spring (XML, Java, Annotations)

## 🛠️ Technologies

### Backend
- **Java 17+**
- **Spring Framework 6.2.10**
  - Spring Core (IoC/DI)
  - Spring Data JPA 3.4.3
  - Spring MVC (REST)
  - Spring Context
- **Hibernate 6.6.4** (ORM)
- **MySQL 9.4.0** (Base de données)
- **MapStruct 1.5.5** (Mapping)
- **Bean Validation** (Jakarta Validation API)
- **Jackson** (Sérialisation JSON)

### Outils de Développement
- **Maven** (Gestion de dépendances)
- **Docker** (Conteneurisation)
- **JUnit 4.13.2** (Tests unitaires)

## 📦 Dépendances Maven et Leurs Rôles

### 🌱 Spring Framework

| Dépendance | Version | Rôle |
|------------|---------|------|
| **spring-context** | 6.2.10 | 🏗️ Conteneur IoC/DI - Gestion des beans et injection de dépendances |
| **spring-webmvc** | 6.2.10 | 🌐 Framework MVC - Controllers REST et gestion HTTP |
| **spring-tx** | 6.2.10 | 💳 Gestion déclarative des transactions (@Transactional) |
| **spring-orm** | 6.2.10 | 🔗 Intégration ORM - Bridge entre Spring et Hibernate |
| **spring-data-jpa** | 3.4.3 | 🗂️ Repositories automatiques - Simplifie l'accès aux données |

### 🗄️ Persistence et Base de Données

| Dépendance | Version | Rôle |
|------------|---------|------|
| **hibernate-core** | 6.6.4.Final | 🔄 ORM - Mapping objet-relationnel, gestion des entités JPA |
| **mysql-connector-j** | 9.4.0 | 🔌 Driver JDBC MySQL - Connexion à la base de données |

### 🌐 Jakarta EE (anciennement Java EE)

| Dépendance | Version | Rôle |
|------------|---------|------|
| **jakarta.servlet-api** | 6.0.0 | 🌍 API Servlet - Gestion des requêtes HTTP (scope: provided) |
| **jakarta.validation-api** | 3.0.2 | ✅ API de validation - Annotations de validation (@NotNull, @Size, etc.) |
| **hibernate-validator** | 8.0.1.Final | 🛡️ Implémentation de Bean Validation - Validation automatique des DTOs |
| **expressly** | 5.0.0 | 📝 Expression Language - Requis par Hibernate Validator |

### 🔄 Mapping et Sérialisation

| Dépendance | Version | Rôle |
|------------|---------|------|
| **mapstruct** | 1.5.5.Final | 🗺️ Mapping Entity ↔ DTO - Génération automatique de code à la compilation |
| **mapstruct-processor** | 1.5.5.Final | ⚙️ Processeur d'annotations MapStruct (scope: provided) |
| **jackson-databind** | 2.17.0 | 📄 Sérialisation/Désérialisation JSON - Conversion objets Java ↔ JSON |

### 🛠️ Outils de Développement

| Dépendance | Version | Rôle |
|------------|---------|------|
| **lombok** | 1.18.30 | ✨ Réduction du boilerplate - @Data, @Getter, @Setter, @Builder, etc. |
| **junit** | 4.13.2 | 🧪 Framework de tests unitaires (scope: test) |

### 🔧 Configuration Maven

#### Plugins Importants

**1. Maven Compiler Plugin** (3.11.0)
```xml
<annotationProcessorPaths>
    <path><!-- Lombok --></path>
    <path><!-- MapStruct --></path>
</annotationProcessorPaths>
```
- 🎯 **Rôle** : Compile le code Java 17 et traite les annotations de Lombok et MapStruct

**2. Maven WAR Plugin** (3.4.0)
```xml
<failOnMissingWebXml>false</failOnMissingWebXml>
```
- 📦 **Rôle** : Package l'application en fichier WAR sans nécessiter web.xml (grâce à WebAppInitializer)

### 🎯 Pourquoi Ces Technologies ?

| Technologie | Justification |
|-------------|---------------|
| **Spring Data JPA** | ⚡ Réduit 70% du code DAO grâce aux repositories automatiques |
| **MapStruct** | 🚀 Mapping compile-time (vs reflection) = performances optimales |
| **Lombok** | ✂️ Élimine ~40% du code boilerplate (getters/setters/constructeurs) |
| **Hibernate 6.6.4** | 🆕 Support Jakarta EE, performances améliorées |
| **Jackson** | 📊 Standard de facto pour JSON en Java |
| **Bean Validation** | 🛡️ Validation déclarative et centralisée |

## 🗄️ Modèle de Données

### Entité Film
```java
- idFilm (Long) - Identifiant unique
- title (String) - Titre du film
- releaseYear (Integer) - Année de sortie
- duration (Integer) - Durée en minutes
- synopsis (String) - Résumé du film
- rating (Double) - Note sur 10
- director (ManyToOne) - Réalisateur
- category (ManyToOne) - Catégorie
```

### Entité Director (Réalisateur)
```java
- idDirector (Long) - Identifiant unique
- firstName (String) - Prénom
- lastName (String) - Nom
- nationality (String) - Nationalité
- birthDate (LocalDate) - Date de naissance
- biography (String) - Biographie
- films (OneToMany) - Liste des films réalisés
```

### Entité Category (Catégorie)
```java
- idCategory (Long) - Identifiant unique
- name (String) - Nom de la catégorie
- description (String) - Description
- films (OneToMany) - Liste des films
```

### Relations
- Un film appartient à **un seul réalisateur** (ManyToOne)
- Un réalisateur peut avoir **plusieurs films** (OneToMany)
- Un film appartient à **une seule catégorie** (ManyToOne)
- Une catégorie peut contenir **plusieurs films** (OneToMany)

## 📥 Installation

### Prérequis
- Java JDK 17 ou supérieur
- Maven 3.6+
- MySQL 8.0+ ou Docker
- Git
- Docker
- Tomcat 10+
- Docker
- Tomcat 10+

### Étapes d'Installation

1. **Cloner le repository**
```bash
git clone https://github.com/MouadHallaffou/CineHub.git
cd CineHub
```

2. **Démarrer MySQL avec Docker**
```bash
docker-compose up -d
```

3. **Configurer la base de données**
   
   Créer une base de données MySQL :
```sql
CREATE DATABASE cinehub;
```

4. **Compiler le projet**
```bash
mvn clean install
```

5. **Déployer l'application**
```bash
mvn tomcat7:run
```
   
   Ou déployer le fichier `target/demo.war` sur votre serveur d'applications.

## ⚙️ Configuration

### Configuration Java (Zero XML Configuration)

L'application utilise une **configuration Java complète** sans fichier `web.xml`, basée sur les annotations Spring :

#### 1. **AppConfig.java** - Configuration Principale

La classe `AppConfig` centralise toute la configuration de l'application :

```java
@Configuration                    // Marque comme classe de configuration Spring
@EnableWebMvc                     // Active Spring MVC
@ComponentScan(basePackages = "com.cinehub")  // Scan automatique des composants
@EnableJpaRepositories(basePackages = "com.cinehub.repository")  // Active Spring Data JPA
@EnableTransactionManagement      // Active la gestion des transactions
public class AppConfig implements WebMvcConfigurer {
    // Configuration des beans...
}
```

**Rôles et responsabilités** :
- ✅ **DataSource** : Configuration de la connexion MySQL
- ✅ **EntityManagerFactory** : Configuration JPA/Hibernate
- ✅ **TransactionManager** : Gestion des transactions
- ✅ **MessageConverters** : Conversion JSON avec Jackson
- ✅ **Hibernate Properties** : Dialecte, DDL auto, logs SQL

#### 2. **WebAppInitializer.java** - Initialisation Servlet 3.0+

Remplace le fichier `web.xml` traditionnel grâce à Servlet 3.0+ :

```java
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};  // Configuration racine
    }
    
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;  // Pas de config servlet séparée
    }
    
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};  // Mappage du DispatcherServlet
    }
}
```

**Avantages** :
- 🚀 Zero XML - Configuration 100% Java
- 🔧 Type-safe et refactorable
- 📦 Déploiement simplifié
- ⚡ Chargement dynamique au démarrage du conteneur

### Configuration de la Base de Données

Configuration dans `AppConfig.java` :

```java
@Bean
public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/cinehubDB?useSSL=false&serverTimezone=UTC");
    dataSource.setUsername("root");
    dataSource.setPassword("");
    return dataSource;
}
```

### Properties Hibernate

```java
private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
    properties.put("hibernate.show_sql", "false");
    properties.put("hibernate.format_sql", "true");
    properties.put("hibernate.hbm2ddl.auto", "update");
    return properties;
}
```

### Scopes des Beans
- **Singleton** : Services, Repositories (défaut)
- **Prototype** : DTOs, objets temporaires

## 🚀 Utilisation

### Démarrage de l'Application

Une fois déployée, l'application est accessible à :
```
http://localhost:8080/demo
```

### Exemples de Requêtes HTTP

Des fichiers `.http` sont disponibles dans le dossier `api/` :
- `api/film.http` - Requêtes pour les films
- `api/director.http` - Requêtes pour les réalisateurs
- `api/category.http` - Requêtes pour les catégories

## 🌐 API Endpoints

### Films

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/films` | Liste tous les films |
| GET | `/api/films/{id}` | Détails d'un film |
| POST | `/api/films` | Créer un film |
| PUT | `/api/films/{id}` | Modifier un film |
| DELETE | `/api/films/{id}` | Supprimer un film |
| GET | `/api/films/search?title={title}` | Rechercher par titre |
| GET | `/api/films/year/{year}` | Filtrer par année |
| GET | `/api/films/rating/{minRating}` | Filtrer par note |

### Réalisateurs

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/directors` | Liste tous les réalisateurs |
| GET | `/api/directors/{id}` | Détails d'un réalisateur |
| POST | `/api/directors` | Créer un réalisateur |
| PUT | `/api/directors/{id}` | Modifier un réalisateur |
| DELETE | `/api/directors/{id}` | Supprimer un réalisateur |
| GET | `/api/directors/{id}/films` | Filmographie |
| GET | `/api/directors/search?name={name}` | Rechercher par nom |

### Catégories

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/categories` | Liste toutes les catégories |
| GET | `/api/categories/{id}` | Détails d'une catégorie |
| POST | `/api/categories` | Créer une catégorie |
| PUT | `/api/categories/{id}` | Modifier une catégorie |
| DELETE | `/api/categories/{id}` | Supprimer une catégorie |
| GET | `/api/categories/{id}/films` | Films de la catégorie |

## 🧪 Tests

### Tests Unitaires

Les tests sont implémentés avec **JUnit 5** et **Mockito** :

```bash
mvn test
```

### Couverture des Tests

Chaque service est testé pour :
- ✅ Création d'une entité (succès)
- ✅ Récupération par ID (succès et échec)
- ✅ Mise à jour (succès et échec)
- ✅ Suppression (succès et avec contraintes)
- ✅ Méthodes de recherche personnalisées

### Exemples de Tests
- `FilmServiceTest.java`
- `DirectorServiceTest.java`
- `CategoryServiceTest.java`

## 📏 Règles de Gestion

### Contraintes Métier

1. **Films**
   - ✅ Un film appartient à un seul réalisateur
   - ✅ Un film appartient à une seule catégorie
   - ✅ La note doit être comprise entre 0 et 10
   - ✅ L'année de sortie ne peut pas être dans le futur
   - ✅ La durée doit être supérieure à 0

2. **Réalisateurs**
   - ✅ La suppression n'est possible que s'il n'a aucun film associé
   - ✅ Un réalisateur peut avoir plusieurs films

3. **Catégories**
   - ✅ La suppression n'est possible que si elle ne contient aucun film
   - ✅ Une catégorie peut contenir plusieurs films

### Gestion des Exceptions

- `FilmNotFoundException` - Film introuvable
- `DirectorNotFoundException` - Réalisateur introuvable
- `CategoryNotFoundException` - Catégorie introuvable
- `DirectorHasFilmsException` - Réalisateur avec films associés
- `CategoryHasFilmsException` - Catégorie avec films associés
- `InvalidRatingException` - Note invalide
- `InvalidYearException` - Année invalide

## 🤝 Contribuer

Les contributions sont les bienvenues ! Veuillez suivre ces étapes :

1. Fork le projet
2. Créer une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. Commit vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request

### Standards de Code
- Respecter les conventions Java
- Documenter le code avec Javadoc
- Écrire des tests unitaires
- Suivre l'architecture en couches

## 📄 License

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## 👥 Auteurs

- **Votre Nom** - *Développement initial* - [YourGithub](https://github.com/MouadHallaffou)

## 🙏 Remerciements

- Spring Framework Community
- Hibernate Team
- Tous les contributeurs du projet

## 📞 Contact

Pour toute question ou suggestion :
- Email: mouadhallaffou@gmail.com
- GitHub Issues: [https://github.com/MouadHallaffou/CineHub](https://github.com/MouadHallaffou/CineHub)

---

⭐ **N'oubliez pas de donner une étoile si ce projet vous a aidé !**

