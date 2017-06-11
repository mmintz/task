package co.pushfortask.Repository.Observables;

import android.util.Log;

import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;
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

public class ObservableApiComments {

    private static final String TAG = ObservableApiComments.class.getName();

    private Subscription mSubscription;

    public interface Callbacks {
        void onCompleteGetComments(ApiListCommentsForPost apiListCommentsForPost);

        void onErrorGetComments(Throwable throwable);
    }

    private final ObservableApiComments.Callbacks mObservableCallbacks;

    public ObservableApiComments(ObservableApiComments.Callbacks callbacks, String postId) {
        this.mObservableCallbacks = callbacks;
        Observable<ApiListCommentsForPost> mObservableApiListComments = RepositoryImpl.getInstance().getComments(postId);
        mSubscription = mObservableApiListComments
                .subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ApiListCommentsForPost>() {
                               @Override
                               public void call(ApiListCommentsForPost apiListCommentsForPost) {
                                   mObservableCallbacks.onCompleteGetComments(apiListCommentsForPost);
                                   mSubscription.unsubscribe();
                               }
                           }
                        , new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mObservableCallbacks.onErrorGetComments(throwable);
                                mSubscription.unsubscribe();
                                Log.e(TAG, "Error getting comments");
                            }
                        }
                );
    }

}