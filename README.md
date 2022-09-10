# Football Facts

Football Facts is a simple android app that lists Football clubs and Football leagues in all countries.

The purpose of this project is to show a skeleton structure of android app that features the following

* Clean architecture with use cases
* Domain driven design
* Kotlin flows and coroutines
* Compose
* MVVM
* Dagger Hilt
* Room
* Retrofit

The app structure is separated into 4 different layers: UI,Domain,Network and data.

The UI is the presentation layer within the app , It is responsible to handle the UI state of the app, this is where MVVM architecture takes place. The ViewModel communicate with use cases to get the required data and then map to display models that show in the UI using compose

The domain layer is where the business requirements and rules take place. I used Domain driven design to build the main entities of the app and to encapsulate all the business code related to specific entities within its class.

The network layer is where all the remote APIs take place. Retrofit is used to execute all the api calls within the app

The data layer is where all the local data is stored , Room is used for the local sql database.

There is a repository layer where it combine between retrieving data from network layer or data layer depending on business requirements

