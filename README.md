# WhetherApp

Thank you for have a look at this repo. Sorry for the delay I was a bit busy with my ongoing projects.

The app can fetch weather for a particular city (Default one is Auckland).

Currently I m still adding some bits. Soon we would be able to quiry cities and select it to display a forecast. App can work offline based on the previous data. Also user can do pull to refresh to fetch a new data. Unit testing will added as well. 
The final finall final version will be available on Monday. Ah sorry , there is not such thing as final final as we can enhance the app indefinitely :). 

Tech overview:

App is using MVVM pattern. For displaying data I use EpoxyLibrary with Databinding which saves hips of time for me. For async loading I m using coroutines. For storing data locally I use Paper DB (NOSQL). I plugged in Nav components for demo purposes. Dagger is used for DI. Please feel free to start looking and asking questions. 

Some limitations:

-App is not handling nicely all potential error scenarious.  
-My desing is very plain. I really mean it )))).  
-I havent checked all versions on Android and all phone sizes yet. Sorry if you manage to crash it . We can submit an issue and i ll resolve it as soon as possible :) .  
-Glide is having basic setup only.  

---------- 

Please be aware I m still updating my app and this README file can change over the weekends.

Cheers,


