package educa.movel.com.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.Video;
import educa.movel.com.rv.RvGallery;
import educa.movel.com.rv.RvVideos;

public class VideosListActivity extends AppCompatActivity {

    private RecyclerView rv_videos;
    private List<Video> videoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initUI();
        getVideios();
        setVideoList();
    }

    private void setVideoList() {
        RvVideos adapter = new RvVideos(this , videoList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2 , LinearLayoutManager.VERTICAL);
        rv_videos.setLayoutManager(layoutManager);
        rv_videos.setAdapter(adapter);
    }

    private void getVideios() {
       videoList.add(new Video("", "" ,""));
       videoList.add(new Video("", "" ,""));
       videoList.add(new Video("", "" ,""));
       videoList.add(new Video("", "" ,""));
       videoList.add(new Video("", "" ,""));
       videoList.add(new Video("", "" ,""));
    }

    private void initUI() {
        rv_videos = findViewById(R.id.rv_videos);
    }
}