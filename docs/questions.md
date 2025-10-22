QUESTIONS POUR CRÉER UNE DOCUMENTATION — SPRING CORE / SPRING DATA / SPRING MVC (sans Spring Boot)

SPRING CORE — Inversion de Contrôle & Injection de Dépendances

1. Qu’est-ce que Spring Core et à quoi sert-il dans une application Java ?

2. Que signifie le principe d’Inversion de Contrôle (IoC) ?

3. Quelle est la différence entre IoC et Injection de Dépendances (DI) ?

4. Qu’est-ce qu’un bean dans Spring ?

5. Quel est le rôle du conteneur IoC ?

6. Quelle est la différence entre ApplicationContext et BeanFactory ?

7. Quelles sont les trois approches de configuration dans Spring (XML, annotations, Java Config) ?

8. À quoi servent les annotations suivantes :

9. - @Configuration

10. - @ComponentScan

11. - @Bean

12. - @Component, @Service, @Repository, @Controller

13. - @Autowired, @Qualifier

14. Comment Spring détecte et crée automatiquement les composants ?

15. Quelles sont les étapes du cycle de vie d’un bean ?

16. Quelle est la différence entre les scopes de bean (singleton, prototype, etc.) ?

17. Pourquoi la configuration manuelle (avant Spring Boot) est-elle importante à comprendre ?

SPRING DATA JPA — Persistance & Transactions

18. Qu’est-ce que Spring Data JPA et quel problème résout-il ?

19. Quelle est la différence entre JPA et Hibernate ?

20. Qu’est-ce qu’une entité JPA ?

21. À quoi sert le DataSource ?

22. Que fait l’EntityManager ?

23. Quelle est la responsabilité du TransactionManager ?

24. À quoi sert l’annotation @EnableJpaRepositories ?

25. Qu’est-ce qu’un repository Spring Data ?

26. Quelles sont les méthodes génériques fournies par JpaRepository ?

27. Comment gérer les transactions avec Spring (annotation, propagation, rollback, etc.) ?

28. Pourquoi définir manuellement la connexion à la base de données avant Spring Boot ?

29. Que doit contenir une configuration de persistance complète (DataSource, EntityManagerFactory, TransactionManager) ?

30. Qu’est-ce que la validation de contrainte dans le modèle (ex. email unique, longueur, etc.) ?

31. Quelle est la différence entre une suppression logique (soft delete) et une suppression physique ?

SPRING MVC — Contrôleurs & Couche Web

32. Que signifie MVC (Model-View-Controller) et quel est son objectif dans Spring ?

33. Quel est le rôle du DispatcherServlet dans Spring MVC ?

34. Quelle est la différence entre un Controller et un RestController ?

35. Quelle est la fonction des annotations suivantes :

36. - @RequestMapping

37. - @GetMapping, @PostMapping, @PutMapping, @DeleteMapping

38. - @Valid

39. - @RequestBody et @PathVariable

40. Comment le DispatcherServlet traite-t-il une requête HTTP du début à la fin ?

41. Qu’est-ce que la classe de configuration Web (WebConfig) et à quoi sert-elle ?

42. Pourquoi faut-il initialiser le DispatcherServlet manuellement avant Spring Boot ?

43. Qu’est-ce qu’un WebAppInitializer et pourquoi remplace-t-il web.xml ?

44. Quelles sont les étapes de traitement d’une requête REST dans Spring MVC ?

45. Comment se fait la sérialisation et désérialisation des objets JSON ?

46. À quoi sert un @RestControllerAdvice ?

47. Quelles sont les bonnes pratiques pour organiser les packages d’un projet MVC ?