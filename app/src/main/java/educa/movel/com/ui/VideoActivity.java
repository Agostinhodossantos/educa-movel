package educa.movel.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import educa.movel.com.R;

public class VideoActivity extends AppCompatActivity {

    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        getIntentValues();

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        if (!url.isEmpty()) {
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = getUrlID(url);
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });
        } else {
            Toast.makeText(VideoActivity.this, "Opss: Video indisponível", Toast.LENGTH_SHORT).show();
            finish();
        }
        

    }

    private String getUrlID(String url) {
        int index = url.indexOf("=");
        String id = "";
        if (index > 0) {
           id =  url.substring(index + 1, url.length());
        }

        return id;
    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if(getIntent()!=null && getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            if(!bundle.getString("url").equals(null)) {
                url = intent.getExtras().getString("url");
            }else {
                Toast.makeText(this, "Ocoreu uma falha", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}