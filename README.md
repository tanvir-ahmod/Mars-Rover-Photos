## About

[Nasa Open APIs](https://api.nasa.gov/) provides NASA data, including imagery, eminently accessible to application developer. In this project Mars Rover Photos API is used to load different images of Mars from different Rovers. There are some filtering options to get the data from API. 

Flutter version of this project can be found in the following link:
https://github.com/tanvir-ahmod/mars_rover_image_flutter

## Demo
<img src="https://github.com/tanvir-ahmod/MarsRoverPhotos/blob/master/screenshots/demo.gif" height="400" width="200">

## Screenshots

<img src="https://github.com/tanvir-ahmod/MarsRoverPhotos/blob/master/screenshots/Screenshot_2020-08-15-12-52-41-145_com.example.marsroverPhotos.jpg" height="400" width="200"><img src="https://github.com/tanvir-ahmod/MarsRoverPhotos/blob/master/screenshots/Screenshot_2020-08-15-12-52-48-539_com.example.marsroverPhotos.jpg" height="400" width="200"><img src="https://github.com/tanvir-ahmod/MarsRoverPhotos/blob/master/screenshots/Screenshot_2020-08-15-12-52-52-669_com.example.marsroverPhotos.jpg" height="400" width="200">
<img src="https://github.com/tanvir-ahmod/MarsRoverPhotos/blob/master/screenshots/Screenshot_2020-08-15-12-52-56-246_com.example.marsroverPhotos.jpg" height="400" width="200"><img src="https://github.com/tanvir-ahmod/MarsRoverPhotos/blob/master/screenshots/Screenshot_2020-08-15-12-53-02-279_com.example.marsroverPhotos.jpg" height="400" width="200"><img src="https://github.com/tanvir-ahmod/MarsRoverPhotos/blob/master/screenshots/Screenshot_2020-08-15-12-53-06-383_com.example.marsroverPhotos.jpg" height="400" width="200"><img src="https://github.com/tanvir-ahmod/MarsRoverPhotos/blob/master/screenshots/Screenshot_2020-08-15-12-53-11-694_com.example.marsroverPhotos.jpg" height="400" width="200">
<img src="https://github.com/tanvir-ahmod/MarsRoverPhotos/blob/master/screenshots/Screenshot_2020-08-15-12-53-21-616_com.example.marsroverPhotos.jpg" height="400" width="200">


## Getting Started

```
Works on Android Studio Arctic Fox | 2020.3.1 Canary 2
```

To get started at first clone the project.

```
git clone https://github.com/tanvir-ahmod/MarsRoverPhotos.git
```

### NASA Open API Key

This app uses the [NASA Open API](https://api.nasa.gov/) to get and load pictures of Rovers from Mars. To use the API, you will need to generate API key. See the [NASA Open API](https://api.nasa.gov/) for instructions.

Once you have the key, add the key to the `Constants` file (`~/utills/Constants`)

```
 const val API_KEY = "DEMO_KEY" // Replace with API keys
```

The app is still usable with default API key


## Built With 🛠

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [ViewPager2](https://developer.android.com/jetpack/androidx/releases/viewpager2) - Display Views or Fragments in a swipeable format.
- [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
  - [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
  - [Hilt-ViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack) - DI for injecting `ViewModel`.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Glide](https://bumptech.github.io/glide/) - A fast and efficient image loading library for Android focused on smooth scrolling.
- [GSON](https://github.com/google/gson) - A Java library that can be used to convert Java Objects into their JSON representation.
- [Android-SpinKit](https://github.com/ybq/Android-SpinKit) - An android loading animation Library.

## License

```
MIT License

Copyright (c) 2020 Tanvir Ahmod

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
