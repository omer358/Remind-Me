# Remind-Me
-----------
Remind me is a reminder app for people you have met, you add them to the app database manually and then the app will send a notification to remind you of them.

Screenshots
-----------

![splash screen support](screenshots/splash-screen.png "splash screen")
![](screenshots/listOfPeople.png "a list of people")
![](screenshots/listOfPeopleD.png "a list of people in dark theme")
![person details](screenshots/personDetails.png "Details for a specific person you added")
![person details](screenshots/personDetailsD.png "Details for a specific person you added in dark theme")
![input form](screenshots/inputForm.png "add person form")
![empty state](screenshots/empty-state.png "empty state")
![notifications](screenshots/notification.png)
![settings](screenshots/settings.png "settings screen person you added")
![settings](screenshots/settingsD.png "settings screen in dark theme")

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
