package co.pushfortask.Repository;

import android.util.Log;

import co.pushfortask.Application.Application;
import co.pushfortask.Repository.Api.NetworkDataSourceImpl;
import co.pushfortask.Repository.Api.NetworkDataSourceInterface;
import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;
import co.pushfortask.Repository.Api.entities.ApiListPosts;
import co.pushfortask.Repository.Database.DatabaseDataSource;
import co.pushfortask.Repository.Database.DatabaseSourceImpl;
import co.pushfortask.Utils.NetworkUtilities;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Marios on 10/06/2017.
 */

public class RepositoryImpl implements RepositoryInterface {

    private static final String TAG = RepositoryImpl.class.getName();

    private static RepositoryImpl sRepository;
    private final DatabaseDataSource mDbDataSource;
    private final NetworkDataSourceInterface mNetworkDataSource;


    public static RepositoryImpl getInstance() {

        if (sRepository == null) {
            sRepository = new RepositoryImpl(new DatabaseSourceImpl(Application.getContext()), new NetworkDataSourceImpl());
        }
        return sRepository;
    }

    private RepositoryImpl(DatabaseDataSource databaseSource, NetworkDataSourceInterface networkDataSource) {
        this.mDbDataSource = databaseSource;
        this.mNetworkDataSource = networkDataSource;
    }

    @Override
    public Observable<ApiListPosts> getPosts() {
        Observable<ApiListPosts> apiListPostsObservable = null;
        Observable<ApiListPosts> apiListPostsObservableNet;
        Observable<ApiListPosts> apiListPostsObservableDB;
        try {
            apiListPostsObservableDB = mDbDataSource.getPosts();
            apiListPostsObservableNet = mNetworkDataSource.getListOfPosts();
            // Subscribe to the callback to persist the data from the Network
            apiListPostsObservableNet.subscribe(new Action1<ApiListPosts>() {
                @Override
                public void call(final ApiListPosts apiListPosts) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mDbDataSource.persist(apiListPosts);
                        }
                    }).start();
                }
            });

            //TODO : RACE CONDITION ?

            if (NetworkUtilities.isOnline(Application.getContext())) {
                //TODO: Idealy Merge both observables to get the data from both once comes back.
                apiListPostsObservable = apiListPostsObservableNet;//Observable.merge(apiListPostsObservableDB, apiListPostsObservableNet);
            } else {
                apiListPostsObservable = apiListPostsObservableDB;
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }

        return apiListPostsObservable;
    }

    @Override
    public Observable<ApiListCommentsForPost> getComments(String postId) {
        Observable<ApiListCommentsForPost> apiListCommentsForPostObservable = null;
        Observable<ApiListCommentsForPost> apiListCommentsForPostObservableNet;
        Observable<ApiListCommentsForPost> apiListCommentsForPostObservableDB;
        try {
            apiListCommentsForPostObservableDB = mDbDataSource.getComments(postId);
            apiListCommentsForPostObservableNet = mNetworkDataSource.getListOfCommentsForPost(postId);
            // Subscribe to the callback to persist the data from the Network
            apiListCommentsForPostObservableNet.subscribe(new Action1<ApiListCommentsForPost>() {
                @Override
                public void call(final ApiListCommentsForPost apiListCommentsForPost) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mDbDataSource.persist(apiListCommentsForPost);
                        }
                    }).start();
                }
            });

            //TODO : RACE CONDITION ?

            if (NetworkUtilities.isOnline(Application.getContext())) {
                //TODO: Idealu Merge both observables to get the data from both once comes back.
                apiListCommentsForPostObservable = apiListCommentsForPostObservableNet;
            } else {
                apiListCommentsForPostObservable = apiListCommentsForPostObservableDB;
            }

        } catch (Exception e) {
            Log.e(TAG, "Exception RepositoryImpl " + e.getMessage());
            e.printStackTrace();
        }

        return apiListCommentsForPostObservable;
    }

}
