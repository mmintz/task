package co.pushfortask.Repository.Api;

import co.pushfortask.Api.PlaceHolderApiClient;
import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;
import co.pushfortask.Repository.Api.entities.ApiListPosts;
import rx.Observable;

/**
 * Created by Marios on 10/06/2017.
 */

public class NetworkDataSourceImpl implements NetworkDataSourceInterface {

    private static final String TAG = NetworkDataSourceImpl.class.getName();


    @Override
    public Observable<ApiListPosts> getListOfPosts() {
        return PlaceHolderApiClient.getsPlaceHolderClient().getPosts();
    }

    @Override
    public Observable<ApiListCommentsForPost> getListOfCommentsForPost(String postId) {
        return PlaceHolderApiClient.getsPlaceHolderClient().getComments(postId);
    }
}
