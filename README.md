# WhetherApp

Thank you for have a look at this repo. Sorry for the delay I was a bit busy with my ongoing projects.

The app can fetch the weather for a particular city (Default one is Auckland).

User is able to quiry cities and select it to display a forecast. App can work offline based on the previous data. Also user can do pull to refresh to fetch a new data. Unit testing is added as an example (If you want more things around it please ask). 

Tech overview:

App is using MVVM pattern. For displaying data I use EpoxyLibrary with Databinding which saves hips of time for me. For async loading I m using coroutines. For storing data locally I use Paper DB (NOSQL). I plugged in Nav components for demo purposes. Dagger is used for DI. Please feel free to start looking and asking questions. 

Some limitations:

-App is not handling nicely all potential error scenarious.  
-My desing is very plain. I really mean it )))).  
-I havent checked all versions on Android and all phone sizes yet. Sorry if you manage to crash it . We can submit an issue and i ll resolve it as soon as possible :) .  
-Glide is having basic setup only.  
-App doesn't have 100% unit tests coverage.  
-App doesn't have models in all layers.

---------- 

Things I could have added (apart from all set of features in the task):

-When user queries a city sometimes API can deliver many cities with the same name but different countries. For a nice UX I could add static map api to fetch an image of the location by lat/long.  
-Hire a designer who could deliver nice UI. :)  
-Add more test coverage  
-Add integration tests for viewmodels  
-Add another developer for whom I could have raised a PR. ;(  
-More things that will be in mind next day after I wrote it.  

Cheers,


