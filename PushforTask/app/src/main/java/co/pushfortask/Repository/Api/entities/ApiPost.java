package co.pushfortask.Repository.Api.entities;

import java.io.Serializable;

import co.pushfortask.Repository.Database.entities.RealmPosts;

/**
 * Created by Marios on 10/06/2017.
 */

public class ApiPost extends ApiBaseClass implements Serializable{

    private String userId;
    private String id;
    private String title;
    private String body;

    public ApiPost(){

    }

    public ApiPost(RealmPosts realmPosts)
    {
        this.userId = realmPosts.getUserId();
        this.id = realmPosts.getId();
        this.title = realmPosts.getTitle();
        this.body = realmPosts.getBody();
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
