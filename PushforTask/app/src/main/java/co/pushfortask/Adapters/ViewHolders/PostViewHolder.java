package co.pushfortask.Adapters.ViewHolders;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.pushfortask.Activities.CommentsActivity;
import co.pushfortask.Application.Constants;
import co.pushfortask.R;
import co.pushfortask.Repository.Api.entities.ApiPost;

/**
 * Created by Marios on 10/06/2017.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {

    private final String TAG = PostViewHolder.class.getName();

    private View rootView;
    private CardView mContainer;
    private TextView mTitle;
    private ApiPost mPost;


    public PostViewHolder(View view) {
        super(view);
        rootView = view;
        initViews();
        initListeners();
    }

    public void render(ApiPost post) {
        mPost = post;
        String titleWithApperLetter = mPost.getTitle().substring(0,1).toUpperCase() + mPost.getTitle().substring(1);
        mTitle.setText(titleWithApperLetter);
    }

    private void initViews() {
        mContainer = (CardView) rootView.findViewById(R.id.card_view);
        mTitle = (TextView) rootView.findViewById(R.id.title);
    }

    private void initListeners() {
        mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent commentsIntent = new Intent(v.getContext(), CommentsActivity.class);
                commentsIntent.putExtra(Constants.POST_DATA_FLAG,mPost);
                v.getContext().startActivity(commentsIntent);
            }
        });

    }
}
