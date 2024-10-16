# NoteApp Architecture Sample

This is a sample Android application that demonstrates best practices for building a note-taking application using modern Android architecture components. The project showcases the use of MVVM (Model-View-ViewModel) architecture with Jetpack components.

## Summary

The NoteApp Architecture Sample serves as a reference implementation for creating a simple note-taking application. It emphasizes separation of concerns, clean architecture, and the use of Android Jetpack libraries for a responsive and user-friendly experience.

## Features

- Add, edit, and delete notes.
- View a list of saved notes.
- Use Room for local database management.
- Implement ViewModel and LiveData for data handling.
- Simple and intuitive UI.

## Technologies Used

- Kotlin
- Android SDK
- Room for local database
- ViewModel and LiveData
- Coroutines for asynchronous operations
- Jetpack Navigation for seamless navigation between screens

## Branches and Architectures

This project showcases various architectures in Android development. Each branch represents a different architectural pattern implemented in the app.

- **MVVM**: Implements Model-View-ViewModel architecture.
  - Branch: `clean-mvvm`
- **MVI**: Implements Model-View-Intent architecture.
  - Branch: `clean-mvi`
- **MVVM Plus**: A variation of MVVM with additional enhancements.
  - Branch: `clean-mvvm+`
- **Clean Architectures**: All of these branch follow clean architecture.

Each branch contains its own structure and corresponding files to demonstrate the architecture in practice. 

## How It Works

1. **Architecture**: The application follows the MVVM architecture pattern, separating the UI, data, and business logic.
2. **Room Database**: The app uses Room as a local database to store notes. Data is managed through the DAO (Data Access Object) pattern.
3. **ViewModel**: The ViewModel holds UI-related data and survives configuration changes, allowing the UI to observe changes seamlessly.
4. **UI Implementation**: The UI is built using XML layouts, and data is displayed through RecyclerView.

## Contributing

Contributions are welcome! If you have suggestions for improvements, please open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
