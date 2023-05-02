# Help

## Objectifs

L'objectif de cet exercice est d'implémenter de façon incrémentale une application Android qui s'apparente à un Instagram avec des photos de chien.
Ce développement va permettre d'acquérir des bonnes pratiques de code et de découvrir ou de consolider les connaissances en :
- __*Clean Archi*__ : couches presentation, domain et data et injection de dépendance 
- __tests unitaires__ : avec Junit et Mockito
- __gestion des flux de données__ : Coroutine et Flow
- __gestion de la vue__ : vue XML classique et *Jetpack Compose*



# Decoupage de l implementation

L'architecture du projet a été mis en place et le premier écran a été développé. L'objectif est de développer les deux autres écrans en commençant par la couche domain, puis data et enfin présentation.

Avant de commencer les développements il est conseillé de s'impreigner du code (ne pas hésiter à avoir un oeil critique dessus) et de lire ces liens si vous en ressentez le besoin :
- Un Article Medium sur la [Clean Archi Android](https://medium.com/android-dev-hacks/detailed-guide-on-android-clean-architecture-9eab262a9011)
- La doc Android sur les [fondamentaux du testing](https://developer.android.com/training/testing/fundamentals)
- La doc Android sur les [Coroutines](https://developer.android.com/kotlin/coroutines) et les [Flow](https://developer.android.com/kotlin/flow) 

L'application repose sur __*dummyAPI*__ dont la documentation est disponible [ici](https://dummyapi.io/docs). Il faut se créer un compte et générer une app-id comme indiqué dans la page __*Getting Started*__. Cette app-id est à mettre dans le fichier `local.properties` via la ligne suivante : `apiKey="YOUR_API_KEY"`

Avant de se lancer il faut avoir configurer les outils suivant :
- [ ] Faire la mise à jour d'Android Studio si nécessaire à la version Dolphin | 2021.3.1 Patch 1
- [ ] Télécharger postman pour tester les requêtes d'API : ajouter au header la clé "app-id" et en valeur votre app-id. Tester les différents end-points notamment récupérer une liste de posts, un post par id et un user par id.

## Partie 1. Dev de la couche domaine

Le domaine est le coeur de l'application qui contient la logique et les règles métier. Il est donc important que le domaine soit indépendant des autres modules et d'autres bibliothèques liées à l'UI, à Android, ... Mais aussi que toutes les classes (sauf les modèles) et les méthodes publiques soient testées.

Dans cette partie il faut modifier le module `:domain` :
- [ ] Créer les modèles qui vont représenter un post détaillé (écran 2) et un utilisateur (écran 3). Ne pas hésiter à créer plusieurs `data class` ou à modifier les existantes si cela semble judicieux
- [ ] Créer les interface des `Repositories` pour récupérer les données
- [ ] Créer les `UseCases` qui vont permettre de récupérer les données. Ajouter les règles suivantes ou faire attention à :
  - Bien réfléchir à la gestion des dates / des durées : comment les représenter dans le domaine ? 
  - Suivant l'âge de l'utilisateur mettre une image ou une couleur de background différente
  - Avoir une icône différente suivant le genre de la personne
- [ ] Faire les tests unitaires des `UseCases`

## Partie 2. Dev de la couche donnees

La couche de données va permettre de récupérer et persister les données de l'application aux travers de `Repositories` et des `Data Sources` qui peuvent être locales avec un BDD ou distantes avec une API.

Dans cette partie il faut modifier la couche `:api`:
- [ ] Ajouter les `DTO` et les `services` qui permettront de récupérer les infos des posts et des utilisateurs en pensant à l'injection de dépendance
Dans `:data`:
- [ ] Créer les Mapper entre DTO et les modèles de `:domain` et ajouter les TU
- [ ] Implémenter les `Repositories` du `:domain` sans oublier de les injecter et de les tester

## Partie 3. Dev de la couche presentation


Le rôle de l'UI est d'afficher les données de l'application à l'écran et de servir de point principal d'interaction utilisateur. Le pattern le plus utilisé est MVVM (*Model - View - ViewModel*) avec :
- Un modèle spécifique à la couche présentation (ici VO pour *View Object*)
- Une Vue soit :
  - **classique** : avec un fichier XML statique et un Fragment dynamique
  - **compose** : un seul fichier kotlin contenant une vue composable
- Un ViewModel qui va récupérer la données via des UseCases et qui va l'envoyer à la vue.

Trois prérequis avant de commencer le dév :
- [ ] Générer un module Android `:post`. Modifier le build.gradle pour ajouter les dépendances nécessaires et faire dépendre le module `:app` du module créé
- [ ] Faire de même pour `:user`
- [ ] Dans ces deux modules créer un fragment vide.

### A. Gestion de la navigation
Pour cette partie il est nécessaire de lire [cette doc](https://developer.android.com/guide/navigation/navigation-deep-link) et [cette doc](https://developer.android.com/jetpack/compose/navigation#interoperability) pour comprendre la navigation entre une vue basée sur du XML et une vue en `Jetpack Compose`.
- [ ] Dans les modules `:home`, `:post` et `:user` générer des graph de navigation qui vont contenir les fragments et qui vont gérer un deeplink à créer.
- [ ] Dans le module `:app` générer un nouveau fichier de resource qui correspond au graph de navigation général. Modifier le layout `activity_main` pour lier le nouveau navGraph et changer le name en `NavHostFragment`.

### B. Gestion de la vue XML pour post

Faire l'affichage d'un post en suivant l'architecture MVVM. Pour l'UI vous êtes libre. Si vous n'avez pas d'idée, vous pouvez vous inspirer du screenshot. 

### C. Gestion de la vue Jetpack Compose pour user

- [ ] Gérer le theming avec jetpack compose. L'idéal est de rationaliser les couleurs avec celles du thème classique cf [ce tuto](https://proandroiddev.com/how-to-create-a-truly-custom-theme-in-jetpack-compose-55fb4cd6d655) et utiliser la méthode `colorResource()`
- [ ] Créer les VO pertinents et les mapper du modèle de domain vers modèle de présentation. Faire les TU des mappers
- [ ] Créer le ViewModel. Modifier le `UserFragment` pour que dans le `onCreateView` il affiche un `ComposeView`.
- [ ] Créer la vue compose avec trois méthodes composables, par exemple :
  - `@Composable fun UserProfile()` "dynamique" : qui prend en paramètre le viewmodel et différentes lambda et appelle la méthode statique ci-après
  - `@Composable fun UserProfile()` "statique" : qui contient tout l'affichage
  - `@Preview(showSystemUi = true) @Composable fun UserProfilePreview()` : qui va appeler la méthode statique ci-avant et qui va permettre de visualiser une preview de la vue similairement à une vue XML

Ne pas hésiter à factoriser la vue compose en plusieurs fichiers pour plus de clareté.

## Partie 4. Ajouter de nouvelles fonctionnalités

Dans cette partie vous aurez la liberté d'ajouter de nouvelles fonctionnalités parmi les suivantes des plus simples au plus complexes :
- Un bouton de tri qui permet d'ordonner les posts par date, nombre de likes, nombre de commentaires, ...
- Un header contenant une barre de recherche sur la home avec une recherche par description, tag, utilisateur, ...
- Pour pouvoir accéder en offline aux posts déjà chargés, gérer un sauvegarde locale des posts via une BDD avec Room
