package educa.movel.com.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.News;
import educa.movel.com.rv.RvNews;
import educa.movel.com.utils.InitFirebase;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView rv_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initUI();
        initRv();
    }


    private void initRv() {
        List<News> newsList = new ArrayList<>();


        InitFirebase
                .initFirebase()
                .child("novidades")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot objSnapshot: snapshot.getChildren()) {
                            News news = objSnapshot.getValue(News.class);
                            newsList.add(news);
                        }
                        RvNews rvNews = new RvNews(NewsActivity.this, newsList);
                        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
                        rv_news.setLayoutManager(layoutManager);
                        rv_news.setAdapter(rvNews);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }

    private void initUI() {
        rv_news = findViewById(R.id.rv_news);
    }

}