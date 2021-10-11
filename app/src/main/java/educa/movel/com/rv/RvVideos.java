package educa.movel.com.rv;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.Image;
import educa.movel.com.model.Video;
import educa.movel.com.ui.ImagesFullScreen;
import educa.movel.com.ui.VideosListActivity;


public class RvVideos extends RecyclerView.Adapter<RvVideos.MyViewHoder> {
    private Context mContext;
    private List<Video> mData;
    private Lifecycle lifecycle;

    public RvVideos(Context mContext, List<Video> mData, Lifecycle lifecycle) {
        this.mContext = mContext;
        this.mData = mData;
        this.lifecycle = lifecycle;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_video, viewGroup,false);

        MyViewHoder viewHoder = new MyViewHoder(v);

        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHoder holder, final int position) {
        holder.tv_title.setText(mData.get(position).getTitle());
        YouTubePlayerView playerView = holder.playerView;


        lifecycle.addObserver(playerView);
        playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onVideoId(@NonNull YouTubePlayer youTubePlayer, @NonNull String videoId) {
                super.onVideoId(youTubePlayer, mData.get(position).getUrl());
            }
        });

//        ((VideosListActivity) mContext).getLifecycle().addObserver(playerView);
//        playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                youTubePlayer.loadVideo(mData.get(position).getUrl(), 0);
//                Toast.makeText(mContext, "heuu"+mData.get(position).toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHoder extends RecyclerView.ViewHolder{

        private TextView tv_title;
        private YouTubePlayerView playerView;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            playerView = itemView.findViewById(R.id.youtube_player_view);

        }
    }
}

