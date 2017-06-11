package co.pushfortask.Repository.Api.entities;

import co.pushfortask.Repository.Database.entities.RealmComment;

/**
 * Created by Marios on 10/06/2017.
 */

public class ApiComment extends ApiBaseClass {

    private String postId;
    private String id;
    private String name;
    private String email;
    private String body;

    public ApiComment(RealmComment realmComment)
    {
        this.postId = realmComment.getPostId();
        this.id = realmComment.getId();
        this.name = realmComment.getName();
        this.email = realmComment.getEmail();
        this.body = realmComment.getBody();
    }

    public String getPostId() {
        return postId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
