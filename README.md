# Remind-Me
-----------
Remind me is a reminder app for people you have met, you add them to the app database manually and then the app will send a notification to remind you of them.
The app follow the MVVM Arhitecture.

Libraries Used
--------------
* [Architecture][10] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Data Binding][11] - Declaratively bind observable data to UI elements.
  * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
  * [LiveData][13] - Build data objects that notify views when the underlying database changes.
  * [Navigation][14] - Handle everything needed for in-app navigation.
  * [Room][16] - Access your app's SQLite database with in-app objects and compile-time checks.
  * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
* [UI][30] - Details on why and how to use UI Components in your apps - together or separate
  * [Animations & Transitions][31] - Move widgets and transition between screens.
  * [Fragment][34] - A basic unit of composable UI.
* [Alarm Manager][33] = to schedule the notifications for the specified times.
* Third party
  * [Kotlin Coroutines][91] for managing background threads with simplified code and reducing needs for callbacks


Screenshots
-----------

| Splash Screen | Home Screen | 
|    :---:     |     :---:      |  
| <img src="screenshots/splash-screen.png" width="500">   | <img src="screenshots/list_of_people.png" width="500">   |

| Person Details Screen | Person Details Screen In Dark Mode| 
|    :---:     |     :---:      |  
| <img src="screenshots/person_details.png" width="500">   | <img src="screenshots/person_details_d.png" width="500">   |

| Input Form Screen Screen | Empty State Screen| 
|    :---:     |     :---:      |  
| <img src="screenshots/inputForm.png" width="500">   | <img src="screenshots/empty-state.png" width="500">   |

| Notification Screen | Setting Screen| 
|    :---:     |     :---:      |  
| <img src="screenshots/notification.png" width="500">   | <img src="screenshots/settings.png" width="500">   |



[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/data-binding/
[12]: https://developer.android.com/topic/libraries/architecture/lifecycle
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[16]: https://developer.android.com/topic/libraries/architecture/room
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[30]: https://developer.android.com/guide/topics/ui
[31]: https://developer.android.com/training/animation/
[34]: https://developer.android.com/guide/components/fragments
[91]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[33]: https://developer.android.com/training/scheduling/alarms
