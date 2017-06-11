package co.pushfortask.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.pushfortask.Adapters.ViewHolders.PostViewHolder;
import co.pushfortask.R;
import co.pushfortask.Repository.Api.entities.ApiPost;

/**
 * Created by Marios on 10/06/2017.
 */

public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private static final String TAG = PostRecyclerViewAdapter.class.getName();

    private List<ApiPost> mPosts = new ArrayList<>();

    public PostRecyclerViewAdapter() {

    }

    public void addPosts(List<ApiPost> posts) {
        mPosts = posts;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.posts_card, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        ApiPost post = mPosts.get(position);
        holder.render(post);
    }

    @Override
    public int getItemCount() {
        if (mPosts.isEmpty()) {
            return 0;
        }
        return mPosts.size();
    }
}
