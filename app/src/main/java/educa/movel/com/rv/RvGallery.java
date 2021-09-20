package educa.movel.com.rv;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.Image;


public class RvGallery extends RecyclerView.Adapter<RvGallery.MyViewHoder> {
    private Context mContext;
    private List<Image> mData;

    public RvGallery(Context mContext, List<Image> mData ) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_gallery, viewGroup,false);

        MyViewHoder viewHoder = new MyViewHoder(v);

        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHoder holder, final int position) {

        String img = mData.get(position).getImg();
        if (!img.isEmpty()) {
            Picasso.get().load(mData.get(position).getImg()).into(holder.img);
        }

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

