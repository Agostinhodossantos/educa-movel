package educa.movel.com.rv;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.News;
import educa.movel.com.ui.InfoFullActivity;
import educa.movel.com.utils.CheckField;


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
        if (!new CheckField().isEmpty(mData.get(position).getImg()))  {
            Picasso.get().load(mData.get(position).getImg()).into(holder.img);
        } else {

        }
        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_date.setText(mData.get(position).getDate());
        holder.tv_category.setText(mData.get(position).getCategory());
        holder.ll_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , InfoFullActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("news" , (Serializable) mData.get(position));
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHoder extends RecyclerView.ViewHolder{

        LinearLayout ll_info;
        private TextView tv_title, tv_date, tv_category;
        private RoundedImageView img;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            ll_info = itemView.findViewById(R.id.ll_info);
            tv_category = itemView.findViewById(R.id.tv_category);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_date = itemView.findViewById(R.id.tv_date);
            img = itemView.findViewById(R.id.img);
        }
    }
}

