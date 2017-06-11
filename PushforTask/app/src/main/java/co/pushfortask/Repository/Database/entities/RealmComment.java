package co.pushfortask.Repository.Database.entities;

import co.pushfortask.Repository.Api.entities.ApiComment;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Marios on 10/06/2017.
 */
@RealmClass
public class RealmComment extends RealmObject {
    @PrimaryKey
    @Index
    private String postId;
    private String id;
    private String name;
    private String email;
    private String body;

    public RealmComment(){

    }

    public RealmComment(ApiComment apiComment)
    {
        this.postId = apiComment.getPostId();
        this.id = apiComment.getId();
        this.name = apiComment.getName();
        this.email = apiComment.getEmail();
        this.body = apiComment.getBody();
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
