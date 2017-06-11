package co.pushfortask.Repository.Database;

import android.content.Context;

import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;
import co.pushfortask.Repository.Api.entities.ApiListPosts;
import co.pushfortask.Repository.Database.entities.RealmComment;
import co.pushfortask.Repository.Database.entities.RealmPosts;
import co.pushfortask.Repository.Database.persistors.Persistor;
import co.pushfortask.Repository.Realm.ReactiveX.RealmObservable;
import co.pushfortask.Repository.Realm.RealmHelper;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Marios on 10/06/2017.
 */

public class DatabaseSourceImpl implements DatabaseDataSource {

    private static final String TAG = DatabaseSourceImpl.class.getName();

    private final Context mContext;
    private final Persistor mPersistor;

    public DatabaseSourceImpl(Context context)
    {
        this.mContext = context;
        this.mPersistor = new Persistor();
    }

    @Override
    public Observable<ApiListPosts> getPosts() {
        return RealmObservable.results(mContext, new Func1<Realm, RealmResults<RealmPosts>>() {
            @Override
            public RealmResults<RealmPosts> call(Realm realm) {

                return RealmHelper.getAllPosts(realm);
            }
        }).map(new Func1<RealmResults<RealmPosts>, ApiListPosts>() {
            @Override
            public ApiListPosts call(RealmResults<RealmPosts> realmPosts) {

                return new ApiListPosts(realmPosts);
            }
        });
    }

    @Override
    public Observable<ApiListCommentsForPost> getComments(final String postId) {
        return RealmObservable.results(mContext, new Func1<Realm, RealmResults<RealmComment>>() {
            @Override
            public RealmResults<RealmComment> call(Realm realm) {

                return RealmHelper.getCommentsForPost(realm,postId);
            }
        }).map(new Func1<RealmResults<RealmComment>, ApiListCommentsForPost>() {
            @Override
            public ApiListCommentsForPost call(RealmResults<RealmComment> realmComments) {
                return new ApiListCommentsForPost(realmComments);
            }
        });
    }

    @Override
    public Observable<ApiListPosts> persist(ApiListPosts apiListPosts) {

        try {
            return mPersistor.persist(apiListPosts);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Observable<ApiListCommentsForPost> persist(ApiListCommentsForPost apiListCommentsForPost) {
        try {
            return mPersistor.persist(apiListCommentsForPost);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
