package co.pushfortask.Repository.Api.entities;

import android.util.Log;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import co.pushfortask.Repository.Database.entities.RealmComment;


/**
 * Created by Marios on 11/06/2017.
 */

public class ApiListCommentsForPost extends ApiBaseClass{

    private List<ApiComment> mApiComments;

    public ApiListCommentsForPost(List<ApiComment> mApiComments) {this.mApiComments = mApiComments;}

    public List<ApiComment> getmApiComments() {
        return mApiComments;
    }

    public ApiListCommentsForPost(AbstractList<RealmComment> realmComments) {
        mApiComments = new ArrayList<>();
        for (RealmComment realmComment : realmComments) {
            mApiComments.add(new ApiComment(realmComment));
        }
    }

    @Override
    public String toString() {
        return "ApiListCommentsForPost{" +
                "mApiComments=" + mApiComments +
                '}';
    }
}
