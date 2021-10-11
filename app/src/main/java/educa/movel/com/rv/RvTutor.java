package educa.movel.com.rv;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.TutorVideo;
import educa.movel.com.ui.VideoActivity;


public class RvTutor extends RecyclerView.Adapter<RvTutor.MyViewHoder> {
    private Context mContext;
    private List<TutorVideo> mData;

    public RvTutor(Context mContext, List<TutorVideo> mData ) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_coding_tutor, viewGroup,false);

        MyViewHoder viewHoder = new MyViewHoder(v);

        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHoder holder, final int position) {
        holder.card_tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoActivity.class);
                intent.putExtra("url", mData.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });

        holder.tv_coding_title.setText(mData.get(position).getTitle());
        holder.tv_subject.setText(mData.get(position).getTag());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHoder extends RecyclerView.ViewHolder{

        private CardView card_tutor;
        private TextView tv_coding_title;
        private TextView tv_subject;
        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            card_tutor = itemView.findViewById(R.id.card_tutor);
            tv_coding_title = itemView.findViewById(R.id.tv_coding_title);
            tv_subject = itemView.findViewById(R.id.tv_subject);
        }
    }
}

