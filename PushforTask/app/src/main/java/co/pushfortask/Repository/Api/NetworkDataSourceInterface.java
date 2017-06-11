package co.pushfortask.Repository.Api;

import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;
import co.pushfortask.Repository.Api.entities.ApiListPosts;
import co.pushfortask.Repository.Observables.ObservableApiComments;
import co.pushfortask.Repository.Observables.ObservableApiPosts;
import rx.Observable;

/**
 * Created by Marios on 10/06/2017.
 */

public interface NetworkDataSourceInterface {

    Observable<ApiListPosts> getListOfPosts();

    Observable<ApiListCommentsForPost> getListOfCommentsForPost(String postId);
}
