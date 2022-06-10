package com.codepath.apps.restclienttemplate;

import android.content.Context;
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
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfileImage;
        ImageView ivFixedProfileImage;
        TextView tvBody;
        TextView tvName;
        TextView tvScreenName;
        ImageView ivMedia;
        TextView tvTime;
        ImageView ivRetweet;
        ImageView ivRetweetStroke;
        TextView tvRetweet;
        ImageView ivHeartStroke;
        ImageView ivHeart;
        TextView tvHeart;
        TextView tvReplyCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            ivFixedProfileImage = itemView.findViewById(R.id.ivFixedProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvName = itemView.findViewById(R.id.tvName);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            ivMedia = itemView.findViewById(R.id.ivMedia);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivRetweet = itemView.findViewById(R.id.ivRetweet);
            ivRetweetStroke = itemView.findViewById(R.id.ivRetweetStroke);
            ivHeartStroke = itemView.findViewById(R.id.ivHeartStroke);
            ivHeart = itemView.findViewById(R.id.ivHeart);
            tvRetweet = itemView.findViewById(R.id.tvRetweet);
            tvHeart = itemView.findViewById(R.id.tvHeart);
            tvReplyCount = itemView.findViewById(R.id.tvReplyCount);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvName.setText(tweet.user.name);
            tvScreenName.setText(tweet.user.screenName);
            tvTime.setText(tweet.getRelativeTimeAgo(tweet.createdAt));
            tvReplyCount.setText("42");
            //Check if user has profile image
            if (tweet.user.profileImageUrl == null) {
                ivFixedProfileImage.setVisibility(View.VISIBLE);
            } else {
                Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
            }
            // Check visibility and load the images
            if (tweet.mediaUrl != null) {
                ivMedia.setVisibility(View.VISIBLE);
                Glide.with(context).load(tweet.mediaUrl).into(ivMedia);
            } else {
                ivMedia.setVisibility(View.GONE);
            }

            ivRetweetStroke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("retweet", "Retweet Clicked");
                    ivRetweetStroke.setVisibility(View.GONE);
                    ivRetweet.setVisibility(View.VISIBLE);
                    tweet.retweet_count++;
                    tvRetweet.setText(Integer.toString(tweet.retweet_count));
                }
            });
            tvRetweet.setText(Integer.toString(tweet.retweet_count));
            ivHeartStroke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("retweet", "Retweet Clicked");
                    ivHeartStroke.setVisibility(View.GONE);
                    ivHeart.setVisibility(View.VISIBLE);
                    tweet.favorite_count++;
                    tvHeart.setText(Integer.toString(tweet.favorite_count));
                }
            });
            tvHeart.setText(Integer.toString(tweet.favorite_count));
        }
    }
}
