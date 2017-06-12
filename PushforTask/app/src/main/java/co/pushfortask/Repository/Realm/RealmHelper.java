package co.pushfortask.Repository.Realm;

import co.pushfortask.Api.PlaceHolderApiKeys;
import co.pushfortask.Application.Application;
import co.pushfortask.Repository.Database.entities.RealmComment;
import co.pushfortask.Repository.Database.entities.RealmPosts;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Marios on 10/06/2017.
 */

public class RealmHelper {

    private static final String TAG = RealmHelper.class.getName();

    public static Realm getInstance() {
        try{
            Realm.getInstance(Application.getContext());
            return Realm.getInstance(Application.getContext());
        }catch (Exception e)
        {
            return null;
        }

    }

    public static void deleteRealmFile() {

        Realm.deleteRealm(new RealmConfiguration.Builder(Application.getContext()).build());
    }

    public static RealmResults<RealmPosts> getAllPosts(final Realm realm)
    {
        return realm.where(RealmPosts.class).findAll();
    }

    public static RealmResults<RealmComment> getCommentsForPost(final Realm realm, String postId)
    {
        return realm.where(RealmComment.class).equalTo(PlaceHolderApiKeys.CommentForPost.POST_ID, postId).findAll();
    }
}
