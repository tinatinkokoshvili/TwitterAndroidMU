package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    Context context;
    List<Tweet> tweets;

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }


    // For each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    // Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data at position
        Tweet tweet = tweets.get(position);
        // Bind the tweet with view holder
        holder.bind(tweet);
    }


    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }



    //Define a Viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        ImageView ivMedia;
        TextView tvTime;
        ImageView ivRetweet;
        ImageView ivRetweetStroke;
        ImageView ivHeartStroke;
        ImageView ivHeart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            ivMedia = itemView.findViewById(R.id.ivMedia);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivRetweet = itemView.findViewById(R.id.ivRetweet);
            ivRetweetStroke = itemView.findViewById(R.id.ivRetweetStroke);
            ivHeartStroke = itemView.findViewById(R.id.ivHeartStroke);
            ivHeart = itemView.findViewById(R.id.ivHeart);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            tvTime.setText(tweet.getRelativeTimeAgo(tweet.createdAt));
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
            // Check visibility and load the images
            if (tweet.mediaUrl != null) {
                ivMedia.setVisibility(View.VISIBLE);
                Glide.with(context).load(tweet.mediaUrl).into(ivMedia);
            } else {
                ivMedia.setVisibility(View.GONE);
            }
         //   Log.i("retweet", ivRetweet.toString());
            ivRetweetStroke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("retweet", "Retweet Clicked");
                    ivRetweetStroke.setVisibility(View.GONE);
                    ivRetweet.setVisibility(View.VISIBLE);
                }
            });
            ivHeartStroke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("retweet", "Retweet Clicked");
                    ivHeartStroke.setVisibility(View.GONE);
                    ivHeart.setVisibility(View.VISIBLE);
                }
            });
//            Glide.with(context).load(R.drawable.ic_vector_retweet).into(ivRetweet);

        }
    }
}
