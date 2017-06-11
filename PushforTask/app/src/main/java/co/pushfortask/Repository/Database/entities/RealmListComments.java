package co.pushfortask.Repository.Database.entities;

import co.pushfortask.Repository.Api.entities.ApiComment;
import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;
import io.realm.RealmList;

/**
 * Created by Marios on 11/06/2017.
 */

public class RealmListComments {

    private RealmList<RealmComment> realmComments;

    public RealmListComments(ApiListCommentsForPost apiListCommentsForPost)
    {
        realmComments = new RealmList<>();
        for(ApiComment comment : apiListCommentsForPost.getmApiComments())
        {
            realmComments.add(new RealmComment(comment));
        }
    }

    public RealmList<RealmComment> getRealmComments() {
        return realmComments;
    }
}
