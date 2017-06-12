package co.pushfortask.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import co.pushfortask.Adapters.PostRecyclerViewAdapter;
import co.pushfortask.R;
import co.pushfortask.Repository.Api.entities.ApiListPosts;
import co.pushfortask.Repository.Api.entities.ApiPost;
import co.pushfortask.Repository.Observables.ObservableApiPosts;
import co.pushfortask.Repository.RequestStatus;
import co.pushfortask.Repository.Requests;
import co.pushfortask.events.ApplicationReadyEvent;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements ObservableApiPosts.Callbacks, RequestStatus, Requests {

    private static final String TAG = MainActivity.class.getName();
    private RecyclerView recyclerView;
    private PostRecyclerViewAdapter adapter;
    private List<ApiPost> posts;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreateMainActivity");
        setContentView(R.layout.activity_main);
        initViews();
        adapter = new PostRecyclerViewAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        fetchData();
    }

    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.progress_posts);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_posts);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void fetchData() {
        onStartRequestUpdateUI();
        new ObservableApiPosts(this);
    }

    @Override
    public void onCompleteGetPosts(ApiListPosts apiListPosts) {
        Log.e(TAG,"Success "+ apiListPosts.getApiPosts().size());

        adapter.addPosts(new ArrayList<ApiPost>(apiListPosts.getApiPosts()));
        adapter.notifyDataSetChanged();
        onSuccessRequestUpdateUI();
    }


    @Override
    public void onErrorGetPosts(Throwable throwable) {
        Log.d(TAG,"Error");
        onErrorRequestUpdateUI();
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
