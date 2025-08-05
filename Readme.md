# 📰 NewNews Android App

An Android news application built using Kotlin that fetches and displays news articles with clean architecture and modern Android development practices. Users can browse, save, and view news articles with an interactive and responsive UI.

---

## 📱 Features

### ✅ Splash Screen
- Displays a splash screen when launching the app.

---

### 🏠 Home Screen
- Shows a list of the latest news articles.
- Each news card includes:
    - 🖼️ Image
    - 📰 Title
    - 🗓️ Date
    - 🧾 Short description
    - ⭐ Favorite bookmark button (Add/Remove from favorites)
- Tap on a news card navigates to the **News Details** screen.
- Includes a **Bottom App Bar** with:
    - Home
    - Favorites
    - Profile
- Snackbar messages on add/remove favorites with custom colors.

---

### 📄 News Details Screen
- Displays full details of the selected article:
    - 🖼️ Image
    - 📰 Title
    - 📃 Full description/content
    - 🗓️ Date
    - ✍️ Author
- Ability to favorite/unfavorite the article from this screen as well.

---

### ⭐ Favorite Listing Screen
- Displays only the articles marked as favorites.
- Same layout and behavior as the Home Screen.
- Clicking a card navigates to the **News Details** screen.
- Supports:
    - Adding/removing articles from favorites.
    - Showing corresponding Snackbar messages for each action.

---

### 👤 Profile Screen
- Displays placeholder user information:
    - 👤 User image
    - 🙍 Name
    - 📧 Email
- Includes navigation options like:
    - Profile
    - Notifications
    - Language
    - Settings
- Includes a **Logout button** (no logic implemented).

---

---
## Resources
[🔗 APK](https://drive.google.com/file/d/1DRR4vEPSCGnbYZxxoEDVS6O1_bcp4XbH/view?usp=sharing)
[🔗 Video](https://drive.google.com/file/d/14bOH8JBSSJiM9hwwtGoltZJQl-tUaMHG/view?usp=sharing)
---


## 🗂️ Directory Structure

```
Directory structure:
└── galal-20-newnews/
    ├── build.gradle.kts
    ├── gradle.properties
    ├── gradlew
    ├── gradlew.bat
    ├── settings.gradle.kts
    ├── app/
    │   ├── Readme.md
    │   ├── build.gradle.kts
    │   ├── proguard-rules.pro
    │   ├── .gitignore
    │   └── src/
    │       ├── androidTest/
    │       │   └── java/
    │       │       └── com/
    │       │           └── galal/
    │       │               └── newnews/
    │       │                   └── ExampleInstrumentedTest.kt
    │       ├── main/
    │       │   ├── AndroidManifest.xml
    │       │   ├── java/
    │       │   │   └── com/
    │       │   │       └── galal/
    │       │   │           └── newnews/
    │       │   │               ├── MainActivity.kt
    │       │   │               ├── NewNewsApplication.kt
    │       │   │               ├── data/
    │       │   │               │   ├── local/
    │       │   │               │   │   ├── AppDatabase.kt
    │       │   │               │   │   ├── SavedArticleDao.kt
    │       │   │               │   │   └── SavedArticleEntity.kt
    │       │   │               │   ├── remote/
    │       │   │               │   │   └── ApiService.kt
    │       │   │               │   └── repoImpl/
    │       │   │               │       ├── NewsRepoImpl.kt
    │       │   │               │       └── SavedArticlesRepoImpl.kt
    │       │   │               ├── di/
    │       │   │               │   ├── NetworkModule.kt
    │       │   │               │   ├── RepoModule.kt
    │       │   │               │   └── UseCaseModule.kt
    │       │   │               ├── domain/
    │       │   │               │   ├── entities/
    │       │   │               │   │   └── NewsResponse.kt
    │       │   │               │   ├── repo/
    │       │   │               │   │   ├── NewsRepo.kt
    │       │   │               │   │   └── SavedArticlesRepo.kt
    │       │   │               │   └── useCase/
    │       │   │               │       ├── ImplUseCase/
    │       │   │               │       │   ├── DeleteArticleUseCase.kt
    │       │   │               │       │   ├── GetAllSavedArticlesUseCase.kt
    │       │   │               │       │   ├── NewsUseCase.kt
    │       │   │               │       │   ├── SaveArticleUseCase.kt
    │       │   │               │       │   └── SavedArticlesUseCases.kt
    │       │   │               │       └── IUseCases/
    │       │   │               │           ├── IDeleteArticle.kt
    │       │   │               │           ├── IGetAllSavedArticles.kt
    │       │   │               │           ├── INewsUseCase.kt
    │       │   │               │           └── ISaveArticle.kt
    │       │   │               ├── presentation/
    │       │   │               │   ├── Details/
    │       │   │               │   │   └── View/
    │       │   │               │   │       └── DetailsFragment.kt
    │       │   │               │   ├── Favorite/
    │       │   │               │   │   ├── View/
    │       │   │               │   │   │   └── FavoriteFragment.kt
    │       │   │               │   │   └── ViewModel/
    │       │   │               │   │       └── FavoriteViewModel.kt
    │       │   │               │   ├── Home/
    │       │   │               │   │   ├── Adapter/
    │       │   │               │   │   │   └── NewAdapter.kt
    │       │   │               │   │   ├── View/
    │       │   │               │   │   │   └── HomeFragment.kt
    │       │   │               │   │   └── ViewModel/
    │       │   │               │   │       ├── HomeViewModel.kt
    │       │   │               │   │       └── States/
    │       │   │               │   ├── Profile/
    │       │   │               │   │   └── View/
    │       │   │               │   │       └── ProfileFragment.kt
    │       │   │               │   └── Splash/
    │       │   │               │       └── View/
    │       │   │               │           └── SplashActivity.kt
    │       │   │               └── utils/
    │       │   │                   ├── Constants.kt
    │       │   │                   ├── networkListener.kt
    │       │   │                   └── ShareFunctions.kt
    │       │   └── res/
    │       │       ├── anim/
    │       │       │   ├── fade_in.xml
    │       │       │   ├── fade_out.xml
    │       │       │   ├── rotate.xml
    │       │       │   └── scale_in_animation.xml
    │       │       ├── drawable/
    │       │       │   ├── bg_circle_border.xml
    │       │       │   ├── bottom_nav_color.xml
    │       │       │   ├── ic_launcher_background.xml
    │       │       │   ├── ic_launcher_foreground.xml
    │       │       │   ├── nightlight.xml
    │       │       │   └── wb_sunny.xml
    │       │       ├── font/
    │       │       │   ├── poppins_black.ttf
    │       │       │   ├── poppins_blackitalic.ttf
    │       │       │   ├── poppins_bold.ttf
    │       │       │   ├── poppins_bolditalic.ttf
    │       │       │   ├── poppins_extrabold.ttf
    │       │       │   ├── poppins_extrabolditalic.ttf
    │       │       │   ├── poppins_extralight.ttf
    │       │       │   ├── poppins_extralightitalic.ttf
    │       │       │   ├── poppins_italic.ttf
    │       │       │   ├── poppins_light.ttf
    │       │       │   ├── poppins_lightitalic.ttf
    │       │       │   ├── poppins_medium.ttf
    │       │       │   ├── poppins_mediumitalic.ttf
    │       │       │   ├── poppins_regular.ttf
    │       │       │   ├── poppins_semibold.ttf
    │       │       │   ├── poppins_semibolditalic.ttf
    │       │       │   ├── poppins_thin.ttf
    │       │       │   ├── poppins_thinitalic.ttf
    │       │       │   ├── readex_pro_bold.ttf
    │       │       │   ├── readex_pro_extra_light.ttf
    │       │       │   ├── readex_pro_light.ttf
    │       │       │   ├── readex_pro_medium.ttf
    │       │       │   ├── readex_pro_regular.ttf
    │       │       │   ├── readex_pro_semi_bold.ttf
    │       │       │   ├── tajawal_black.ttf
    │       │       │   ├── tajawal_bold.ttf
    │       │       │   ├── tajawal_extra_bold.ttf
    │       │       │   ├── tajawal_extra_light.ttf
    │       │       │   ├── tajawal_light.ttf
    │       │       │   ├── tajawal_medium.ttf
    │       │       │   └── tajawal_regular.ttf
    │       │       ├── layout/
    │       │       │   ├── activity_main.xml
    │       │       │   ├── activity_splash.xml
    │       │       │   ├── fragment_details.xml
    │       │       │   ├── fragment_favorite.xml
    │       │       │   ├── fragment_home.xml
    │       │       │   ├── fragment_profile.xml
    │       │       │   └── news_item.xml
    │       │       ├── menu/
    │       │       │   └── bottom_nav.xml
    │       │       ├── mipmap-anydpi-v26/
    │       │       │   ├── ic_launcher.xml
    │       │       │   └── ic_launcher_round.xml
    │       │       ├── mipmap-hdpi/
    │       │       │   ├── ic_launcher.webp
    │       │       │   ├── ic_launcher_foreground.webp
    │       │       │   └── ic_launcher_round.webp
    │       │       ├── mipmap-mdpi/
    │       │       │   ├── ic_launcher.webp
    │       │       │   ├── ic_launcher_foreground.webp
    │       │       │   └── ic_launcher_round.webp
    │       │       ├── mipmap-xhdpi/
    │       │       │   ├── ic_launcher.webp
    │       │       │   ├── ic_launcher_foreground.webp
    │       │       │   └── ic_launcher_round.webp
    │       │       ├── mipmap-xxhdpi/
    │       │       │   ├── ic_launcher.webp
    │       │       │   ├── ic_launcher_foreground.webp
    │       │       │   └── ic_launcher_round.webp
    │       │       ├── mipmap-xxxhdpi/
    │       │       │   ├── ic_launcher.webp
    │       │       │   ├── ic_launcher_foreground.webp
    │       │       │   └── ic_launcher_round.webp
    │       │       ├── navigation/
    │       │       │   └── nav_graph.xml
    │       │       ├── raw/
    │       │       │   ├── empty.json
    │       │       │   ├── loading.json
    │       │       │   └── lost2.json
    │       │       ├── values/
    │       │       │   ├── colors.xml
    │       │       │   ├── ic_launcher_background.xml
    │       │       │   ├── strings.xml
    │       │       │   └── themes.xml
    │       │       ├── values-night/
    │       │       │   └── themes.xml
    │       │       └── xml/
    │       │           ├── backup_rules.xml
    │       │           └── data_extraction_rules.xml
    │       └── test/
    │           └── java/
    │               └── com/
    │                   └── galal/
    │                       └── newnews/
    │                           └── ExampleUnitTest.kt
    └── gradle/
        ├── libs.versions.toml
        └── wrapper/
            └── gradle-wrapper.properties

```

---

## 🛠️ Built With

- **Kotlin**
- **Clean Architecture**
- **MVVM**
- **Jetpack Components**
- **Room Database**
- **Retrofit + Coroutines**
- **Glide for image loading**
- **Material Design**
- **Dagger-Hilt** for Dependency Injection
- **smooth and structured navigation between screens.**
- **Lottie Animation**
---

---
## 🛠️ Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/Galal-20/NewNews.git
   ```
---


## 📌 Note

- All data shown in the app is fetched from a remote API (https://newsapi.org/).
- 100 API requests per day.

---

## 🌐 HTTP status codes

- 200 - OK. The request was executed successfully.
- 400 - Bad Request. The request was unacceptable, often due to a missing or misconfigured parameter.
- 401 - Unauthorized. Your API key was missing from the request, or wasn't correct.
- 429 - Too Many Requests. You made too many requests within a window of time and have been rate limited. Back off for a while.
- 500 - Server Error. Something went wrong on our side.

---

## 🔗 👨‍💻 Developer

- Developed by **EN. Galal Ahmed**  
- [🔗 GitHub Profile](https://github.com/galal-20)

---

