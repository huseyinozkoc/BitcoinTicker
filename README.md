
## 📒 Table of Contents
- [📒 Table of Contents](#-table-of-contents)
- [📍 Overview](#-overview)
- [🚀 Screenshots](#-Screenshots)
-  [🧩 Modules](#-modules)
- [⚙️ Gradle Dependencies](#-features)
- [📂 Project Structure](#project-structure)
- [🗺 Firebase Console ](#-getting-started)
- [🤝 Contributing](#-contributing)
- [📄 License](#-license)
- [👏 Acknowledgments](#-acknowledgments)

---

## 📍 Overview

Bitcoin Ticker is your easy-to-use crypto app. Sign up with your email and password, and you're in. See live coin prices and their ups and downs instantly. Choose your favorites, get notifications, and stay in the crypto loop. It's your simple ticket to the crypto world.
![icon](https://github.com/huseyinozkoc/BitcoinTicker/assets/48124105/ed7e4e37-9082-4b15-8d73-f2e02a2f5b15)


---
## 🚀Screenshots

| Splash Screen           |  SignIn Screen | SignUp Screen |  Home Screen  |
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
 ![icon](https://github.com/huseyinozkoc/BitcoinTicker/assets/48124105/ed7e4e37-9082-4b15-8d73-f2e02a2f5b15) |  ![icon](https://github.com/huseyinozkoc/BitcoinTicker/assets/48124105/ed7e4e37-9082-4b15-8d73-f2e02a2f5b15)| ![icon](https://github.com/huseyinozkoc/BitcoinTicker/assets/48124105/ed7e4e37-9082-4b15-8d73-f2e02a2f5b15)| ![icon](https://github.com/huseyinozkoc/BitcoinTicker/assets/48124105/ed7e4e37-9082-4b15-8d73-f2e02a2f5b15) |

 | Detail Screen          |  Favorite Screen | Search Screen |  |
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
 ![icon](https://github.com/huseyinozkoc/BitcoinTicker/assets/48124105/ed7e4e37-9082-4b15-8d73-f2e02a2f5b15) |  ![icon](https://github.com/huseyinozkoc/BitcoinTicker/assets/48124105/ed7e4e37-9082-4b15-8d73-f2e02a2f5b15)| ![icon](https://github.com/huseyinozkoc/BitcoinTicker/assets/48124105/ed7e4e37-9082-4b15-8d73-f2e02a2f5b15)| ![icon](https://github.com/huseyinozkoc/BitcoinTicker/assets/48124105/ed7e4e37-9082-4b15-8d73-f2e02a2f5b15) |

**All Screen Of The Bitcoin Ticker Application**


---

## 🧩 Modules
![clean_art](https://github.com/huseyinozkoc/BitcoinTicker/assets/48124105/4c9180c6-77bb-4330-8835-94056f6d5994)

<details closed><summary>Common</summary>
  
| File                | Summary                                                                                                                                                                      |
| ------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Constants.kt        | Contains constant values used throughout the application, such as API endpoints, keys, and other configuration settings.                                                    |
| ViewBindingDelegate.kt | Provides a utility class for simplifying the process of using ViewBinding in Android activities and fragments.                                                        |
| Extensions.kt       | Contains extension functions that add additional functionality to existing Android classes, making them easier to work with.                                                  |
| DataBindingAdapter.kt | A utility class for data binding in Android, providing methods for binding data to views.                                |
| NetworkCallResource.kt | Defines a sealed class for representing the result of a network call, including success, error, and loading states.                                                        |
</details>

<details closed><summary>Data</summary>

| File           | Summary                                                                                                                                                               |
| -------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Mappers.kt     | Contains mapping functions that transform data between different data models or entities within the application.                                                                                                                                                                                                                   |
| Models.kt      | Defines the data models or entities that represent structured data within the application. These models may correspond to database tables, API responses, or other data sources.                                                                                                                                                   |
| Repository.kt  | Implementation of data repository classes responsible for handling data access and manipulation. This may include data retrieval from local databases (e.g., Room) or remote APIs (e.g., Retrofit).                                                                                                                                                   |
| Source          | This directory contains submodules for handling data sources, such as local and remote sources. Local sources may include database-related classes (e.g., Room database), while remote sources may include API-related classes (e.g., Retrofit).                                                                                                                                                   |
</details>

<details closed><summary>Dependency Injection</summary>

| File                | Summary                                                                                                                                                                      |
| ------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| FirebaseModule.kt   | Dependency injection module for integrating Firebase services into the application. This module may provide Firebase-related dependencies like Firebase authentication, Firestore, etc.                                                    |
| RoomModule.kt       | Dependency injection module for setting up and providing Room database-related dependencies and components.                                                        |
| RetrofitModule.kt   | Dependency injection module for configuring Retrofit and providing API-related dependencies.                                                |
| RepositoryModule.kt | Dependency injection module for providing data repository dependencies, such as data sources and mappers.                                                                                                                                                                                                                   |
| Utils               | This directory contains utility classes or modules that support dependency injection, such as Dagger or Hilt modules.                                                                                                                                                                                                                   |
</details>

<details closed><summary>Utils</summary>

| File                              | Summary                                                                                                                                                                      |
| --------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| WorkManagerImplementation.kt      | Implementation of WorkManager for handling background tasks and scheduling jobs within the application.                                                                                                                                                                                                                   |
| NotificationUtils.kt               | Utility functions and classes for managing and displaying notifications in the Android app.                                                                                                                                                                                                                   |
</details>

<details closed><summary>Domain</summary>

| File               | Summary                                                                                                                                                               |
| ------------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| UseCases.kt       | Defines use cases that encapsulate the application's business logic and represent user interactions.                                                                                                                                                                                                                   |
| DataSourceInterfaces.kt | Interfaces that define the contract for data sources, allowing for abstraction and separation between data retrieval and domain logic.                                                                                                                                                   |
| RepositoryInterfaces.kt  | Interfaces that define the contract for repositories, providing a clear separation between the domain layer and the data layer.                                                                                                                                                   |
| UIModels.kt       | Contains UI-specific models that represent the data to be displayed in the user interface.                                                                                                                                                                                                                   |
</details>

<details closed><summary>Presentation</summary>

| File       | Summary                                                                                                                                                               |
| ---------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Views      | This directory contains the user interface components (e.g., activities, fragments, views) responsible for presenting data and interacting with the user.                                                                                                                                                                                                                   |
| ViewModels | Contains ViewModel classes that manage the presentation logic and data binding between the domain and UI layers.                                                                                                                                                                                                                   |
</details>



---


## ⚙️ Gradle Dependencies
![gradle](https://github.com/huseyinozkoc/BitcoinTicker/assets/48124105/8c1f3ead-20fa-4759-8e74-d7352fbfd2f3)


| Library                                 | Description                                                                                                                                      |
|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| **core-ktx:1.9.0**                     | Kotlin extension functions for Android core libraries, simplifying Android development.                                                      |
| **appcompat:1.5.1**                    | Backward compatibility for newer Android features on older versions of Android.                                                               |
| **material:1.9.0**                     | Material design components for creating high-quality Android apps.                                                                             |
| **constraintlayout:2.1.4**             | Layout manager for creating complex UIs with a flat view hierarchy.                                                                         |
| **junit:4.13.2**                       | Testing framework for Java and Android applications, enabling unit testing.                                                                    |
| **junit-ext:1.1.3**                    | Provides JUnit 4-based test runners and rules for Android testing.                                                                             |
| **espresso-core:3.4.0**               | Testing framework for writing UI tests with a fluent API.                                                                                     |
| **navigation-fragment-ktx:2.5.3**      | Navigation Component for navigating between destinations in your app.                                                                        |
| **navigation-ui-ktx:2.5.3**           | UI components for Navigation Component, simplifying navigation implementation.                                                                |
| **lottie:3.4.0**                       | Library for rendering animations and vector graphics natively.                                                                                 |
| **firebase-auth-ktx**                  | Authentication library for secure sign-ins in Firebase.                                                                                        |
| **firebase-firestore-ktx**             | NoSQL cloud database for building web, mobile, and server applications.                                                                        |
| **firebase-analytics:21.3.0**          | Provides insights into app usage and user engagement.                                                                                          |
| **firebase-crashlytics:18.4.0**        | Tool for crash reporting and real-time analytics, monitoring app stability.                                                                   |
| **firebase-perf-ktx:20.4.0**           | Helps understand and improve app performance.                                                                                                  |
| **dagger:hilt-android:2.44**           | Dependency injection library simplifying and automating dependency injection in Android.                                                      |
| **hilt-work:1.0.0**                    | Integration with WorkManager for background tasks.                                                                                              |
| **work-runtime-ktx:2.7.1**            | Manages background tasks, making it easy to run tasks at specific times or intervals.                                                         |
| **kotlinx-coroutines-android:1.6.4**   | Simplifies asynchronous programming and concurrency on Android.                                                                          |
| **kotlinx-coroutines-play-services:1.6.4** | Coroutines extension for Google Play services, simplifying asynchronous tasks.                                                      |
| **lifecycle-viewmodel-ktx:2.5.1**      | Manages UI-related data in a lifecycle-aware manner.                                                                                          |
| **activity-ktx:1.6.1**                | Provides Kotlin extensions for working with activities, making them easier to manage.                                                         |
| **retrofit:2.9.0**                    | Type-safe HTTP client for Android and Java, simplifying network requests.                                                                      |
| **gson:2.9.1**                        | Library for serializing and deserializing JSON objects, commonly used for parsing JSON in Android apps.                                      |
| **converter-gson:2.9.0**              | Retrofit converter for converting JSON responses to Java/Kotlin objects.                                                                       |
| **room-runtime:2.4.3**                | Abstraction layer over SQLite, simplifying database operations in Android apps.                                                                |
| **room-ktx:2.4.3**                    | Kotlin-specific functionalities for Room Database.                                                                                              |
| **ssp-android:1.1.0**                 | Utility library for defining consistent and scalable spacing and dimensions in Android layouts.                                                |
| **landscapist-glide:2.0.0**           | Kotlin-first image loading library for Android, simplifying image loading and caching.                                                        |

---
## 📂 Project Structure
![project strure](https://github.com/huseyinozkoc/BitcoinTicker/assets/48124105/f177f96e-15ed-492f-b31d-af046f630916)


---
## 🗺  Firebase Console

---
