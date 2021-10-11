package educa.movel.com.rv;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_author.setText(mData.get(position).getSubject());

        holder.card_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mData.get(position).getUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mContext.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHoder extends RecyclerView.ViewHolder{

        private CardView card_book;
        private TextView tv_title;
        private TextView tv_author;
        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            card_book = itemView.findViewById(R.id.card_book);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_author = itemView.findViewById(R.id.textView);
        }
    }
}

