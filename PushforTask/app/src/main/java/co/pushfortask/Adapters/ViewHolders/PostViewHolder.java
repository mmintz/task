package co.pushfortask.Adapters.ViewHolders;

import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.pushfortask.R;
import co.pushfortask.Repository.Api.entities.ApiPost;

/**
 * Created by Marios on 10/06/2017.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {

    private final String TAG = PostViewHolder.class.getName();

    private View rootView;
    private CardView mContainer;
    private TextView mTitle, mDesciption;
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
        mDesciption.setText(mPost.getBody());
    }

    private void initViews() {
        String fontPath = "fonts/Roboto-Bold.ttf";
        Typeface tf = Typeface.createFromAsset(rootView.getContext().getAssets(), fontPath);

        mContainer = (CardView) rootView.findViewById(R.id.card_view);
        mTitle = (TextView) rootView.findViewById(R.id.title);
        mTitle.setTypeface(tf);
        mDesciption = (TextView) rootView.findViewById(R.id.description);
    }

    private void initListeners() {
        mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent videoPlayer = new Intent(v.getContext(), VideoPlayerActivity.class);
//                //TODO: FLAGS
//                videoPlayer.putExtra("videoUrl", mMovie.getUrl());
//                v.getContext().startActivity(videoPlayer);
            }
        });

    }
}
