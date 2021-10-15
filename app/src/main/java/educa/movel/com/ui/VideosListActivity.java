package educa.movel.com.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import educa.movel.com.R;
import educa.movel.com.model.Video;
import educa.movel.com.rv.RvGallery;
import educa.movel.com.rv.RvVideos;
import educa.movel.com.utils.InitFirebase;
import educa.movel.com.utils.Utils;

public class VideosListActivity extends AppCompatActivity implements Utils.AddLifecycleCallbackListener {

    private RecyclerView rv_videos;
    private List<Video> videoList = new ArrayList<>();
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initUI();
        progress.setVisibility(View.VISIBLE);
        setVideoList();

    }


    @Override
    public void addLifeCycleCallBack(YouTubePlayerView youTubePlayerView) {
        getLifecycle().addObserver(youTubePlayerView);
    }


    private void setVideoList() {
        InitFirebase.initFirebase()
                .child("educa_movel")
                .child("videos")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        videoList.clear();
                        for (DataSnapshot objSnapshot: snapshot.getChildren()) {
                            Video video = objSnapshot.getValue(Video.class);
                            videoList.add(video);
                        }
                        progress.setVisibility(View.GONE);
                        RvVideos adapter = new RvVideos(VideosListActivity.this , videoList, getLifecycle());
                        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1 , LinearLayoutManager.VERTICAL);
                        rv_videos.setLayoutManager(layoutManager);
                        rv_videos.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }

    private void setVideo() {

        for (int i = 0; i < 2; i++) {
            Video video = new Video("url","thumbnail", UUID.randomUUID().toString(), "title");

            InitFirebase.initFirebase()
                    .child("educa_movel")
                    .child("videos")
                    .child(video.getUid())
                    .setValue(video);
        }

    }


    private void getVideios() {
        //https://www.youtube.com/watch?v=eNUIvSlEk7E

       videoList.add(new Video("eNUIvSlEk7E", "" ,"", "Titulod do video aqui"));
       videoList.add(new Video("eNUIvSlEk7E", "" ,"", "Titulod do video aqui"));
       videoList.add(new Video("eNUIvSlEk7E", "" ,"", "Titulod do video aqui"));
       videoList.add(new Video("eNUIvSlEk7E", "" ,"", "Titulod do video aqui"));
    }

    private void initUI() {
        rv_videos = findViewById(R.id.rv_videos);
        progress = findViewById(R.id.progress);
    }
}