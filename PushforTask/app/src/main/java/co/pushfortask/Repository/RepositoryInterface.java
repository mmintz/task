package co.pushfortask.Repository;

import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;
import co.pushfortask.Repository.Api.entities.ApiListPosts;
import rx.Observable;

/**
 * Created by Marios on 10/06/2017.
 */

public interface RepositoryInterface {

    Observable<ApiListPosts> getPosts();

    Observable<ApiListCommentsForPost> getComments(String postId);
}
