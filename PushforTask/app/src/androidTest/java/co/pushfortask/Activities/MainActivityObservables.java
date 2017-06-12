package co.pushfortask.Activities;

import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import java.util.List;

import co.pushfortask.Api.Deserializers.GetCommentsDeserializer;
import co.pushfortask.Api.Deserializers.GetPostsDeserializer;
import co.pushfortask.Repository.Api.NetworkDataSourceImpl;
import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;
import co.pushfortask.Repository.Api.entities.ApiListPosts;
import co.pushfortask.Repository.Api.entities.ApiPost;
import co.pushfortask.Repository.Database.DatabaseSourceImpl;
import co.pushfortask.Repository.Observables.ObservableApiPosts;
import co.pushfortask.Repository.RepositoryImpl;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.functions.Func1;
import rx.observers.TestSubscriber;
import rx.plugins.RxJavaHooks;

import rx.schedulers.Schedulers;


/**
 * Created by Marios on 12/06/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainActivityObservables {

    @Mock
    private MainActivity mMockMainView;

    @Mock
    private CommentsActivity mMockCommentsView;

    @Mock
    private ObservableApiPosts mUserResponseCall;

    @Captor
    private ArgumentCaptor<Callback<List<ApiListPosts>>> mArgumentCaptorUserResponse;

    ApiListPosts posts;
    ApiListCommentsForPost commentsFromPost;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Override RxJava schedulers
        RxJavaHooks.setOnIOScheduler(new Func1<Scheduler, Scheduler>() {
            @Override
            public Scheduler call(Scheduler scheduler) {
                return Schedulers.immediate();
            }
        });
        RxJavaHooks.setOnComputationScheduler(new Func1<Scheduler, Scheduler>() {
            @Override
            public Scheduler call(Scheduler scheduler) {
                return Schedulers.immediate();
            }
        });

        RxJavaHooks.setOnNewThreadScheduler(new Func1<Scheduler, Scheduler>() {
            @Override
            public Scheduler call(Scheduler scheduler) {
                return Schedulers.immediate();
            }
        });
    }

    @Test
    public void mockedResponseTest() {

//        ApiListPosts postTest = new ApiListPosts(new ArrayList<ApiPost>());
//        postTest.addPost(new ApiPost());
//        MockWebServer server = new MockWebServer();
//        server.enqueue(new
//
//                MockResponse().
//
//                setBody(new Gson().toJson(postTest)));
//        Gson gsonBuilder = new GsonBuilder()
//                .registerTypeAdapter(ApiListPosts.class, new GetPostsDeserializer())
//                .registerTypeAdapter(ApiListCommentsForPost.class, new GetCommentsDeserializer())
//                .create();
//        RestAdapter retrofit = new RestAdapter.Builder()
//                .setEndpoint("http://jsonplaceholder.typicode.com")
//                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .setConverter(new GsonConverter(gsonBuilder))
//                .build();
//
//        TestSubscriber<ApiListPosts> subscriber = new TestSubscriber<>();
//
//        DatabaseSourceImpl databaseDataSource = new DatabaseSourceImpl(this.mMockMainView.getApplicationContext());
//        NetworkDataSourceImpl networkDataSource = new NetworkDataSourceImpl();
//
//        //new RepositoryImpl(databaseDataSource, networkDataSource).getPosts().subscribe(subscriber);
//
//        subscriber.assertValue(postTest);
//        subscriber.assertNoErrors();
//        subscriber.assertCompleted();
}
    @Test
    public void presentPostFromApiTest() throws Exception {

        mMockMainView.fetchData();
        assert(posts.getApiPosts().isEmpty());
    }

    @Test
    public void presentCommentsFromApiTest() throws Exception {

        mMockCommentsView.fetchData();
        assert(commentsFromPost.getmApiComments().isEmpty());
    }

}