package educa.movel.com.rv;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.News;


public class RvNews extends RecyclerView.Adapter<RvNews.MyViewHoder> {
    private Context mContext;
    private List<News> mData;

    public RvNews(Context mContext, List<News> mData ) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.info_card, viewGroup,false);

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


        public MyViewHoder(@NonNull View itemView) {
            super(itemView);

        }
    }
}

