package co.pushfortask.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import co.pushfortask.Adapters.CommentsRecyclerViewAdapter;
import co.pushfortask.Adapters.PostRecyclerViewAdapter;
import co.pushfortask.Application.Constants;
import co.pushfortask.R;
import co.pushfortask.Repository.Api.entities.ApiComment;
import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;
import co.pushfortask.Repository.Api.entities.ApiPost;
import co.pushfortask.Repository.Observables.ObservableApiComments;

/**
 * Created by Marios on 11/06/2017.
 */

public class CommentsActivity extends AppCompatActivity implements ObservableApiComments.Callbacks {

    private static final String TAG = CommentsActivity.class.getName();
    private CardView postView;
    private ApiPost mApiPost;
    private TextView mTitle, mDesciption;
    private CommentsRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private ApiListCommentsForPost mApiCommentsForPost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        mApiPost = (ApiPost) getIntent().getSerializableExtra(Constants.POST_DATA_FLAG);
        initViews();
        renderPostData();
        adapter = new CommentsRecyclerViewAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        fetchComments();

    }

    private void initViews() {
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_comments);
        postView = (CardView)findViewById(R.id.card_view_comments);
        mTitle = (TextView) findViewById(R.id.title_cardview_comments);
        mDesciption = (TextView)findViewById(R.id.description_cardview_comments);
    }

    private void renderPostData() {
        String fontPath = "fonts/Roboto-Bold.ttf";
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), fontPath);
        mTitle.setTypeface(tf);
        String titleWithApperLetter = mApiPost.getTitle().substring(0,1).toUpperCase() + mApiPost.getTitle().substring(1);
        mTitle.setText(titleWithApperLetter);
        mDesciption.setText(mApiPost.getBody());
    }

    private void fetchComments() {
        new ObservableApiComments(this,mApiPost.getId());
    }

    private void populateData() {
        adapter.addComments(new ArrayList<ApiComment>(mApiCommentsForPost.getmApiComments()));
    }

    @Override
    public void onCompleteGetComments(ApiListCommentsForPost apiListCommentsForPost) {
        Log.d(TAG,"Success "+ apiListCommentsForPost.getmApiComments().size());
        mApiCommentsForPost = apiListCommentsForPost;
        populateData();
    }

    @Override
    public void onErrorGetComments(Throwable throwable) {
        Log.e(TAG,"Error getting comments");

    }
}
