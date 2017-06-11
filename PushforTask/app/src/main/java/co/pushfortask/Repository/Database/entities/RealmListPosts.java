package co.pushfortask.Repository.Database.entities;

import co.pushfortask.Repository.Api.entities.ApiListPosts;
import co.pushfortask.Repository.Api.entities.ApiPost;
import io.realm.RealmList;

/**
 * Created by Marios on 10/06/2017.
 */

public class RealmListPosts {

    private RealmList<RealmPosts> realmPosts;

    public RealmListPosts(ApiListPosts apiListPosts)
    {
        realmPosts = new RealmList<>();
        for(ApiPost post : apiListPosts.getApiPosts())
        {
            realmPosts.add(new RealmPosts(post));
        }
    }

    public RealmList<RealmPosts> getRealmPosts() {
        return realmPosts;
    }
}
