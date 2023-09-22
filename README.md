# HomeWorkMVVM

<!-- @format -->
HomeWorkMVVM is an Android practical demo task for implementing a complete login function.
 
<img src="https://github.com/sanjay-mi/HomeWorkMVVM/blob/master/screenshot/login.png" height="25%" width="25%" >
 

## Used Language, Architecture & Framework

```
 ● Kotlin
 ● MVVM design pattern and Jetpack
 ● Room database
 ● Clean code architecture
 ● Unit tests for the ViewModel layer
 ● ViewBinding
 ● Koin dependency injection framework
 ● Coroutines
```

## 🛠️ Require environment variables

```Grovy
 ● Gradle version 8.0
 ● Gradle plugin version 8.1.1
 ● Compile SDK version API 34
 ● JAVA version 17
```

## 🚀 To run on an Android device

  ```
   Open Android Studio,
  
   Clone the repo,
   https://github.com/sanjay-mi/HomeWorkMVVM.git

   Build the project,

   Connect the Android device and enable USB debugging,
    
   Hit "Run". Done!
  ``` 

## 🛠️ Tech Stack

| Library          | About                                         | Version  |
| ---------------- | ----------------------------------------------| -------- |
| Room             | Database                                      | v2.5.2   |
| Koin             | Dependency injection framework                | v2.2.2   |
| Retrofit2        | Rest API call library                         | v2.9.0   |
| Okhttp3          | HTTP client                                   | v4.10.0  |
| Gson             | Convert Java objects into JSON and vice versa | v2.10    |
| Timber           | Logger                                        | v5.0.1   |
| Mockito          | Unit testing                                  | v4.7.0   |
| JUnit            | Unit testing                                  | v4.13.2  |


## 🗂️ Folder Structure

The project structure follows a modular approach to organize codebase in a scalable manner. Here's an overview of the key folders:

```
├── app
│ ├── manifests
│ ├── java
│ │ ├── homeworkmvvm
│ │ │ ├── data
│ │ │ │ ├── local
│ │ │ │ │ ├── login
│ │ │ │ ├── remote
│ │ │ │ │ ├── base
│ │ │ │ │ ├── login
│ │ │ │ │ │ ├── request
│ │ │ │ │ │ ├── response
│ │ │ ├── domain
│ │ │ ├── exts
│ │ │ ├── UI
│ │ │ │ ├── base
│ │ │ │ ├── login
│ ├── res
│ └── ...
```

  - **app**: contains the main source code of the application.
  - **res**: stores drawable, layout, dimens, colors, string resource files.
  - **manifests**: Android manifests file.
  - **java**: source code and test cases code.
  - **homeworkmvvm**: project package name.
  - **data**: contains local and remote data. 
  - **domain**: DI modules and base use case files.
  - **exts**: common extension functions.
  - **UI**: module wise activity, fragment & viewModel files.
