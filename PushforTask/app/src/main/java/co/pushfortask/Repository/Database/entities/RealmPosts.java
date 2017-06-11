package co.pushfortask.Repository.Database.entities;

import co.pushfortask.Repository.Api.entities.ApiPost;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Marios on 10/06/2017.
 */
@RealmClass
public class RealmPosts extends RealmObject {

    @PrimaryKey
    @Index
    private String id;
    private String userId;
    private String title;
    private String body;

    public RealmPosts() {

    }

    public RealmPosts(ApiPost apiPost)
    {
        this.userId = apiPost.getUserId();
        this.id = apiPost.getId();
        this.title = apiPost.getTitle();
        this.body = apiPost.getBody();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userid) {
        this.userId = userid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
