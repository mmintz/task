package co.pushfortask.Repository.Observables;

import android.util.Log;

import co.pushfortask.Repository.Api.entities.ApiListPosts;
import co.pushfortask.Repository.RepositoryImpl;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Marios on 10/06/2017.
 */

public class ObservableApiPosts {

    private static final String TAG = ObservableApiPosts.class.getName();

    private Subscription mSubscription;

    public interface Callbacks {
        void onCompleteGetPosts(ApiListPosts apiListPosts);

        void onErrorGetPosts(Throwable throwable);
    }

    private final Callbacks mObservableCallbacks;

    public ObservableApiPosts(Callbacks callbacks)
    {
        this.mObservableCallbacks = callbacks;
        Observable<ApiListPosts> mObservableApiListPosts = RepositoryImpl.getInstance().getPosts();
        mSubscription = mObservableApiListPosts
                .subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ApiListPosts>() {
                               @Override
                               public void call(ApiListPosts apiListPosts) {
                                   mObservableCallbacks.onCompleteGetPosts(apiListPosts);
                                   mSubscription.unsubscribe();
                               }
                           }
                        , new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mObservableCallbacks.onErrorGetPosts(throwable);
                                mSubscription.unsubscribe();
                                Log.e(TAG,"Error getting posts");
                            }
                        }
                        );
    }


}
