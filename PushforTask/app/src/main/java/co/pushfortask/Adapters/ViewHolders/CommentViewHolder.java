package co.pushfortask.Adapters.ViewHolders;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.pushfortask.Activities.CommentsActivity;
import co.pushfortask.Application.Constants;
import co.pushfortask.R;
import co.pushfortask.Repository.Api.entities.ApiComment;
import co.pushfortask.Repository.Api.entities.ApiPost;

/**
 * Created by Marios on 11/06/2017.
 */

public class CommentViewHolder extends RecyclerView.ViewHolder {

    private final String TAG = PostViewHolder.class.getName();

    private View rootView;
    private TextView mName;
    private TextView mEmail;
    private TextView mBody;
    private ApiComment mComment;


    public CommentViewHolder(View view) {
        super(view);
        rootView = view;
        initViews();
    }

    public void render(ApiComment comment) {
        mComment = comment;
        mEmail.setText(comment.getEmail());
        String titleWithApperLetter = mComment.getName().substring(0,1).toUpperCase() + mComment.getName().substring(1);
        mName.setText(titleWithApperLetter);
        mBody.setText(comment.getBody());
    }

    private void initViews() {
        mEmail = (TextView) rootView.findViewById(R.id.email);
        mName = (TextView) rootView.findViewById(R.id.name);
        mBody = (TextView) rootView.findViewById(R.id.body);
    }
}
