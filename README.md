# NIT3213 Final Assignment - Animal Kingdom Explorer

This is a professional Android application developed for the NIT3213 Final Assignment. The app demonstrates proficiency in modern Android development practices, including API integration, dependency injection, and clean architecture.

## 📱 App Overview
The "Animal Kingdom Explorer" allows users to:
1.  **Authenticate**: Secure login screen using a student name and ID.
2.  **Explore**: A beautifully designed Dashboard showing a list of animals.
3.  **Learn**: Detailed information for each animal, including descriptions and visual representations.

## 🛠 Tech Stack
- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependency Injection**: Hilt
- **Navigation**: Jetpack Navigation Component
- **Networking**: Retrofit & OkHttp (configured with a Fake API for stability)
- **UI Components**: ViewBinding, Material Design 3, ConstraintLayout, RecyclerView
- **Testing**: Unit tests for ViewModels using MockK and Turbine

## 🚀 How to Run the Application
1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/asherpa11/-NIT3213Final-
    ```
2.  **Open in Android Studio**:
    - Open Android Studio (Iguana or later).
    - Select **Open** and navigate to the project folder.
3.  **Sync Gradle**:
    - Wait for the Gradle sync to complete. If it doesn't start automatically, go to `File > Sync Project with Gradle Files`.
4.  **Run the App**:
    - Connect an Android device or start an emulator (API Level 26 or higher).
    - Click the **Run** button (green play icon).

## 🧪 Running Unit Tests
Critical components are covered by unit tests. To run them:
1.  Open the `Project` pane.
2.  Navigate to `app/src/test/java/com/example/nit3213final`.
3.  Right-click on the folder and select **Run 'Tests in com.example...'**.

## 📁 Project Structure
- `data/`: Contains models, DTOs, repositories, and the networking layer.
- `di/`: Hilt modules for dependency injection.
- `ui/`: UI components (Fragments, ViewModels, Adapters) organized by feature (login, dashboard, details).
- `util/`: Helper classes and sealed classes for state management.
