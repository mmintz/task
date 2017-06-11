package co.pushfortask.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.pushfortask.Adapters.ViewHolders.CommentViewHolder;
import co.pushfortask.R;
import co.pushfortask.Repository.Api.entities.ApiComment;

/**
 * Created by Marios on 11/06/2017.
 */

public class CommentsRecyclerViewAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private static final String TAG = PostRecyclerViewAdapter.class.getName();

    private List<ApiComment> mComments = new ArrayList<>();

    public CommentsRecyclerViewAdapter() {

    }

    public void addComments(List<ApiComment> posts) {
        mComments = posts;
        notifyDataSetChanged();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comments_card, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        ApiComment comment = mComments.get(position);
        holder.render(comment);
    }

    @Override
    public int getItemCount() {
        if (mComments.isEmpty()) {
            return 0;
        }
        return mComments.size();
    }
}
