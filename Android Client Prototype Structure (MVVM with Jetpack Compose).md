# Android Client Prototype Structure (MVVM with Jetpack Compose)

android_client_prototype/
├── app/
│   ├── build.gradle      # App-level Gradle build script (dependencies, config)
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml # App manifest (permissions, components)
│           ├── java/com/example/diarydepresiku/ # Main source code root
│           │   ├── MainActivity.kt     # Main entry point Activity
│           │   ├── DiaryDepresikuApp.kt # Application class (for Hilt setup, etc.)
│           │   ├── data/               # Data layer (Repository, Network, Local)
│           │   │   ├── local/          # Local data source (Room DB - DAOs, Entities)
│           │   │   │   └── AppDatabase.kt # Room database definition (Placeholder)
│           │   │   │   └── DiaryEntryDao.kt # DAO for diary entries (Placeholder)
│           │   │   │   └── UserPreferences.kt # For storing token, user info (DataStore)
│           │   │   ├── network/        # Remote data source (Retrofit/Ktor)
│           │   │   │   └── ApiService.kt   # Retrofit API interface definition
│           │   │   │   └── AuthRequest.kt  # Data class for login/register request body
│           │   │   │   └── AuthResponse.kt # Data class for login/register response body
│           │   │   │   └── DiaryEntryRequest.kt # Data class for creating diary entry
│           │   │   │   └── DiaryEntryResponse.kt # Data class for diary entry response
│           │   │   └── repository/     # Repository pattern implementation
│           │   │       └── AuthRepository.kt # Handles auth logic (login, register)
│           │   │       └── DiaryRepository.kt # Handles diary entry logic
│           │   ├── di/                 # Dependency Injection (Hilt modules)
│           │   │   └── AppModule.kt    # Hilt module for providing dependencies (Retrofit, DB, Repo)
│           │   ├── navigation/         # Jetpack Navigation Compose setup
│           │   │   └── AppNavigation.kt # Defines navigation graph and routes
│           │   │   └── Screen.kt       # Sealed class defining navigation routes
│           │   ├── ui/                 # UI layer (Compose UI, ViewModels)
│           │   │   ├── screens/        # Composable screen functions
│           │   │   │   └── LoginScreen.kt
│           │   │   │   └── RegisterScreen.kt
│           │   │   │   └── DiaryListScreen.kt
│           │   │   │   └── AddDiaryEntryScreen.kt
│           │   │   │   └── DashboardScreen.kt # Main screen after login
│           │   │   ├── theme/          # App theme (colors, typography, shapes)
│           │   │   │   └── Color.kt
│           │   │   │   └── Theme.kt
│           │   │   │   └── Type.kt
│           │   │   └── viewmodel/      # ViewModels for each screen/feature
│           │   │       └── AuthViewModel.kt
│           │   │       └── DiaryViewModel.kt
│           │   └── util/               # Utility classes/functions
│           │       └── Resource.kt     # Wrapper class for handling loading/success/error states
│           │       └── Constants.kt    # App constants (e.g., Base URL)
│           └── res/                # Resources (layouts, drawables, values)
│               ├── drawable/         # Drawable resources (icons, images)
│               ├── values/           # Value resources (strings, colors, styles)
│               │   └── strings.xml
│               │   └── colors.xml
│               └── ...
├── build.gradle          # Project-level Gradle build script
├── settings.gradle       # Gradle settings
└── ...                   # Other project files (gradle wrapper, etc.)

