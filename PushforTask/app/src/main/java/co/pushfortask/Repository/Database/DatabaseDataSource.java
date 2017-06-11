package co.pushfortask.Repository.Database;

import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;
import co.pushfortask.Repository.Api.entities.ApiListPosts;
import rx.Observable;

/**
 * Created by Marios on 10/06/2017.
 */

public interface DatabaseDataSource {

    Observable<ApiListPosts> getPosts();
    Observable<ApiListCommentsForPost> getComments(String postId);
    // Persistors
    Observable<ApiListPosts> persist(ApiListPosts apiListPosts);
    Observable<ApiListCommentsForPost> persist(ApiListCommentsForPost apiListCommentsForPost);
}
