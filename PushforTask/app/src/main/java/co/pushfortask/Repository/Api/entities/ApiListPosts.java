package co.pushfortask.Repository.Api.entities;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import co.pushfortask.Repository.Database.entities.RealmPosts;

/**
 * Created by Marios on 10/06/2017.
 */

public class ApiListPosts extends ApiBaseClass{

    private List<ApiPost> mApiPosts;

    public ApiListPosts(List<ApiPost> apiPosts) {this.mApiPosts = apiPosts;}

    public List<ApiPost> getApiPosts() {
        return mApiPosts;
    }

    public ApiListPosts(AbstractList<RealmPosts> realmPosts) {

        mApiPosts = new ArrayList<>();
        for (RealmPosts realmPost : realmPosts) {
            mApiPosts.add(new ApiPost(realmPost));
        }
    }

    @Override
    public String toString() {
        return "ApiListPosts{" +
                "mApiPosts=" + mApiPosts +
                '}';
    }
}
