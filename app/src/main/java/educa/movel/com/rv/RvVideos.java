package educa.movel.com.rv;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
import educa.movel.com.ui.VIdeoPlayerActivity;
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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VIdeoPlayerActivity.class);
                intent.putExtra("url", mData.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VIdeoPlayerActivity.class);
                intent.putExtra("url", mData.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });
        String img = mData.get(position).getThumbnail();
        if (img != null && !img.equals("")) {
            Picasso.get().load(img).into(holder.img_background);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHoder extends RecyclerView.ViewHolder{

        private TextView tv_title;
        private CardView cardView;
        private ImageView img_background;
        private ImageButton imageButton;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            cardView = itemView.findViewById(R.id.video_card);
            img_background = itemView.findViewById(R.id.img_background);
            imageButton = itemView.findViewById(R.id.btn_play);
        }
    }
}

