package co.pushfortask.Repository.Database.persistors;

import android.util.Log;

import co.pushfortask.Application.Application;
import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;
import co.pushfortask.Repository.Api.entities.ApiListPosts;
import co.pushfortask.Repository.Database.entities.RealmComment;
import co.pushfortask.Repository.Database.entities.RealmListComments;
import co.pushfortask.Repository.Database.entities.RealmListPosts;
import co.pushfortask.Repository.Database.entities.RealmPosts;
import co.pushfortask.Repository.Realm.ReactiveX.RealmObservable;
import co.pushfortask.Repository.Realm.RealmHelper;
import io.realm.Realm;
import io.realm.RealmList;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Marios on 10/06/2017.
 */

public class Persistor<T> implements PersistorIntefrace {

    private static final String TAG = Persistor.class.getName();


    @Override
    public Observable persist(Object data) throws Exception {

        if (data == null)
            throw new IllegalArgumentException("Can not persist null data");
        Observable<T> observable = null;

        if (data instanceof ApiListPosts) {
            observable = (Observable<T>) persistPosts((ApiListPosts) data);
        }else if (data instanceof ApiListCommentsForPost){
            observable = (Observable<T>) persistComments((ApiListCommentsForPost) data);
        }else {
            Log.e(TAG, "Error: Unknown class = " + data.getClass());
        }
        return observable;
    }

    private Observable<ApiListPosts> persistPosts(final ApiListPosts apiListPosts)
    {
        Log.d(TAG,"persistPosts = "+ apiListPosts);
        final RealmListPosts realmPosts = new RealmListPosts(apiListPosts);
        Realm realm = RealmHelper.getInstance();
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(realmPosts.getRealmPosts());
                }
            });
        }catch (Exception exc)
        {
            Log.e(TAG,exc.getMessage());
            exc.printStackTrace();
        }finally {
            if (realm != null)
            {
                realm.close();
            }
        }

        return RealmObservable.list(Application.getContext(), new Func1<Realm, RealmList<RealmPosts>>() {
                    @Override
                    public RealmList<RealmPosts> call(Realm realm) {
                        return (RealmList<RealmPosts>) realm.copyToRealm(realmPosts.getRealmPosts());
                    }
                }
        ).
                map(new Func1<RealmList<RealmPosts>, ApiListPosts>() {
                        @Override
                        public ApiListPosts call(RealmList<RealmPosts> realmPosts) {
                            // map to UI object
                            return new ApiListPosts(realmPosts);
                        }
                    }
                );

    }

    private Observable<ApiListCommentsForPost> persistComments(final ApiListCommentsForPost apiListCommentsForPost)
    {
        Log.d(TAG,"persistComments = "+ apiListCommentsForPost.getmApiComments().size());
        final RealmListComments realmListComments = new RealmListComments(apiListCommentsForPost);
        Realm realm = RealmHelper.getInstance();
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(realmListComments.getRealmComments());
                }
            });
        }catch (Exception exc)
        {
            Log.e(TAG,exc.getMessage());
            exc.printStackTrace();
        }finally {
            if (realm != null)
            {
                realm.close();
            }
        }

        return RealmObservable.list(Application.getContext(), new Func1<Realm, RealmList<RealmComment>>() {
                    @Override
                    public RealmList<RealmComment> call(Realm realm) {
                        return (RealmList<RealmComment>) realm.copyToRealm(realmListComments.getRealmComments());
                    }
                }
        ).
                map(new Func1<RealmList<RealmComment>, ApiListCommentsForPost>() {
                        @Override
                        public ApiListCommentsForPost call(RealmList<RealmComment> realmComments) {
                            // map to UI object
                            return new ApiListCommentsForPost(realmComments);
                        }
                    }
                );

    }
}
