package educa.movel.com.rv;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.Image;
import educa.movel.com.model.Video;
import educa.movel.com.ui.ImagesFullScreen;


public class RvVideos extends RecyclerView.Adapter<RvVideos.MyViewHoder> {
    private Context mContext;
    private List<Video> mData;

    public RvVideos(Context mContext, List<Video> mData ) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_video, viewGroup,false);

        MyViewHoder viewHoder = new MyViewHoder(v);

        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHoder holder, final int position) {



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHoder extends RecyclerView.ViewHolder{

        private RoundedImageView img;
        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_gallery);
        }
    }
}

