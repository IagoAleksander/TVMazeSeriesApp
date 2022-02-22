# TVMaze Series App

Application for listing TV series, using the API provided by the TVMaze website.

- MVVM Architecture
- Jetpack Libraries and Architecture Components.

## Libraries

- Android Jetpack
    - View Model
    - Data Binding
    - Navigation Component
    - Paging
    - Material Design
- Koin
- Retrofit
- Gson
- Coroutines
- LiveData
- Glide

## Features

- [x] List all of the series contained in the API used by the paging scheme provided by the API.
- [x] Allow users to search series by name.
- [x] The listing and search views must show at least the name and poster image of the series.
- [x] After clicking on a series, the application should show the details of the series, showing the following
  information:
    - Name
    - Poster
    - Days and time during which the series airs
    - Genres
    - Summary
    - List of episodes separated by season
- [x] After clicking on an episode, the application should show the episode’s information, including:
    - Name
    - Number
    - Season
    - Summary
    - Image, if there is one
- [x] Allow the user to save a series as a favorite.
- [x] Allow the user to delete a series from the favorites list.
- [x] Allow the user to browse their favorite series in alphabetical order, and click on one to see its details.
- [x] Create a people search by listing the name and image of the person.
- [x] After clicking on a person, the application should show the details of that person, such as:
    - Name
    - Image
    - Series they have participated in, with a link to the series details.
- [ ] Allow the user to set a PIN number to secure the application and prevent unauthorized users.
- [ ] For supported phones, the user must be able to choose if they want to enable fingerprint authentication to avoid
  typing the PIN number while opening the app.
