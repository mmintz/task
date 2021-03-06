# task
Posts and Comments Android App

This repository contains the Java Code of an Android Application for a simple task. Fetching posts from an API populate them and then fetch comments corresponding to a particular post.

The Application works online and offline as well.

In Particular third party frameworks were used to achieve this and a clear infrastructure was implemented.



Retrofit: the HTTP Rest Client is used to perform the API requests. I have chosen Retrofit because of its RxJava adapter that makes it easier to convert an HTTP response into an Observable.
Gson: the JSON deserializer to transform the HTTP response into java models.
Junit: Java unit test framework. 
Mockito: besides its mocking feature, this framework will isused to verify interactions (i.e.: method calls) of classes.
RxAndroid - RxJava and Observables: There are many options provided by Android for executing long task, heavy load in the background and then omitting the results on the main thread. However those methods can introduce memory leaks and many others. With RxJava we can create tasks which can have as many subscribers and using RxAndroid a Scheduler for the Main Android thread is provided as well as the ability to create Schedulers that run on any given Android Handler class.
Realm : Realm is the best alternative to use instead of SQLite. The differences are many. Most importantly it is easy to use. Secondly you are working on native objects not copies of objects that you have to write back then which improves the speed as shown in many benchmarks. In this task Realm was really helpful to map and Store Objects such as Posts or Comments from the Actual objects and then easy to retrieve them when offline.
Most of the time on this task was spend on planning the architecture of the project and implementing the infrastructure in order to be easily maintainable and scalable. However the Unit Testing that i have implemented is limited due to the time limitation of the task even though it was not difficult. The UI is simplistic since UI design was not the goal of the task.

However while working on the task and implementing the tests i realized that I should have used the Model View Presenter model.
The MVP Architecture decouples the view from anything else that has to do with it. In my opinion the view (Activity in our case) should have no interaction with databases, network calls etc. That can make testing also even simple and easier and the logic of the Application more clear.
Along with Google’s Mockito and Reactive Extensions unit test become very simple to write and therefore catch bugs earlier. 

Improvements

1)The app could have a listener for network changes to provide a better user experience by showing an indication to the user.(For example if you download the app for the first time and then go offline and launch the app there is no indication why no posts are shown. The user needs to exit the app and enter again having netowork in that case (rare case))
2)There is no paging. Having 100 posts is ok but if there where 1 million posts the app should support paging. Prefetching more posts while scrolling on the view. However in order to implement that we need the server to support it first.
3)Better UI
4)Going from offline to online the app should fetch the posts from the network and merge them with the database in anycase there are new posts. (Currently we persist all the posts we get from the network without checking any changes)
5)The libraries included in the project should be under network mount accessible by any developer and should be imported under libs folder using gradle scripts. They should not be pushed into the git repository (that is the reason my repo is quite big)