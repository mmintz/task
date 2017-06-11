package co.pushfortask.Api;

import java.util.HashMap;

import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;
import co.pushfortask.Repository.Api.entities.ApiListPosts;
import co.pushfortask.Repository.Api.entities.ApiPost;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by Marios on 10/06/2017.
 */

public interface PlaceHolderApiIntefrace {


    @Headers({"Content-Type: application/json",
            "Accept: Content-Type:application/json"
    })
    @GET("/posts")
    Observable<ApiListPosts> getPosts();

    @Headers({"Content-Type: application/json",
            "Accept: Content-Type:application/json"
    })
    @GET("/comments")
    Observable<ApiListCommentsForPost> getComments(@Query("postId") String postId);

}
