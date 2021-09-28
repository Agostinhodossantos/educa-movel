package educa.movel.com.rv;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.Book;
import educa.movel.com.ui.WebActivity;


public class RvBooks extends RecyclerView.Adapter<RvBooks.MyViewHoder> {
    private Context mContext;
    private List<Book> mData;

    public RvBooks(Context mContext, List<Book> mData ) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_books, viewGroup,false);

        MyViewHoder viewHoder = new MyViewHoder(v);

        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHoder holder, final int position) {
        holder.card_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHoder extends RecyclerView.ViewHolder{

        private CardView card_book;
        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            card_book = itemView.findViewById(R.id.card_book);
        }
    }
}

