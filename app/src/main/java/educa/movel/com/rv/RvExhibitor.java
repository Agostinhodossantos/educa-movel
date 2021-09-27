package educa.movel.com.rv;


import android.annotation.SuppressLint;
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
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import educa.movel.com.R;
import educa.movel.com.model.Exhibitor;
import educa.movel.com.ui.ExhibitorActivity;
import educa.movel.com.utils.Utils;


public class RvExhibitor extends RecyclerView.Adapter<RvExhibitor.MyViewHoder> {
    private Context mContext;
    private List<Exhibitor> mData;

    public RvExhibitor(Context mContext, List<Exhibitor> mData ) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_exhibitor, viewGroup,false);

        MyViewHoder viewHoder = new MyViewHoder(v);

        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHoder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvName.setText(Utils.getCutStr(mData.get(position).getInstitution_name(), 50));
        String img1 = mData.get(position).getImg1();
        String img2 = mData.get(position).getImg2();

        if (!img1.isEmpty()) {
            Picasso.get().load(mData.get(position).getImg1()).into(holder.imgProfile);
            Picasso.get().load(mData.get(position).getImg1()).into(holder.imgBackground);
        } else {
            Picasso.get().load(Utils.emptyImage).into(holder.imgProfile);
            Picasso.get().load(Utils.emptyImage).into(holder.imgBackground);
        }

        holder.card_exhibitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ExhibitorActivity.class);
                intent.putExtra("uid", mData.get(position).getUid());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHoder extends RecyclerView.ViewHolder{

        private CardView card_exhibitor;
        private TextView tvName, tvCategory;
        private RoundedImageView imgBackground;
        private CircleImageView imgProfile;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            card_exhibitor = itemView.findViewById(R.id.card_exhibitor);
            tvName = itemView.findViewById(R.id.tv_title);
           // tvCategory = itemView.findViewById(R.id.tv_category);
            imgBackground = itemView.findViewById(R.id.img_background);
            imgProfile = itemView.findViewById(R.id.img_profile);

        }
    }
}

