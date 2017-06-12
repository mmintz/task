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
import android.view.View;
import android.widget.ProgressBar;
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
import co.pushfortask.Repository.RequestStatus;
import co.pushfortask.Repository.Requests;
import co.pushfortask.Utils.NetworkUtilities;

/**
 * Created by Marios on 11/06/2017.
 */

public class CommentsActivity extends AppCompatActivity implements ObservableApiComments.Callbacks, RequestStatus, Requests {

    private static final String TAG = CommentsActivity.class.getName();
    private CardView postView;
    private ApiPost mApiPost;
    private TextView mTitle, mDesciption;
    private CommentsRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private ApiListCommentsForPost mApiCommentsForPost;
    private ProgressBar progressBar;
    private TextView commentsNumber;

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
        fetchData();

    }

    private void initViews() {
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_comments);
        postView = (CardView)findViewById(R.id.card_view_comments);
        mTitle = (TextView) findViewById(R.id.title_cardview_comments);
        mDesciption = (TextView)findViewById(R.id.description_cardview_comments);
        progressBar = (ProgressBar)findViewById(R.id.progress_comments);
        commentsNumber = (TextView)findViewById(R.id.comments_number);
    }

    private void renderPostData() {
        String fontPath = "fonts/Roboto-Bold.ttf";
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), fontPath);
        mTitle.setTypeface(tf);
        commentsNumber.setTypeface(tf);
        String titleWithApperLetter = mApiPost.getTitle().substring(0,1).toUpperCase() + mApiPost.getTitle().substring(1);
        mTitle.setText(titleWithApperLetter);
        mDesciption.setText(mApiPost.getBody());
    }

    private void populateData() {
        adapter.addComments(new ArrayList<ApiComment>(mApiCommentsForPost.getmApiComments()));
    }

    private void showCommentsNumber() {
        if(NetworkUtilities.isOnline(this.getApplicationContext())) {
            commentsNumber.setText(getString(R.string.comments_number) + " " + mApiCommentsForPost.getmApiComments().size());
        }
        else{
            commentsNumber.setText(getString(R.string.comments_number) + " " + mApiCommentsForPost.getmApiComments().size()+ " (Offline)");
        }
    }

    @Override
    public void onCompleteGetComments(ApiListCommentsForPost apiListCommentsForPost) {
        Log.d(TAG,"Success "+ apiListCommentsForPost.getmApiComments().size());
        mApiCommentsForPost = apiListCommentsForPost;
        populateData();
        showCommentsNumber();
        onSuccessRequestUpdateUI();
    }

    @Override
    public void onErrorGetComments(Throwable throwable) {
        Log.e(TAG,"Error getting comments");
        onErrorRequestUpdateUI();
    }

    @Override
    public void fetchData() {
        new ObservableApiComments(this,mApiPost.getId());
        onStartRequestUpdateUI();
    }

    @Override
    public void onStartRequestUpdateUI() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessRequestUpdateUI() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onErrorRequestUpdateUI() {
        progressBar.setVisibility(View.GONE);

    }
}
