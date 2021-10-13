package educa.movel.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import educa.movel.com.R;
import educa.movel.com.utils.CheckField;

public class VideoActivity extends AppCompatActivity {

    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Lifecycle a =  getLifecycle();

        getIntentValues();

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        CheckField field = new CheckField();

        if (!field.isEmpty(url)) {
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = getUrlID(url);
                    Toast.makeText(VideoActivity.this, ""+videoId, Toast.LENGTH_SHORT).show();
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });
        } else {
            Toast.makeText(VideoActivity.this, "Opss: Video indisponível", Toast.LENGTH_SHORT).show();
            finish();
        }
        

    }



    private String getUrlID(String url) {
        int index;
        String id = "";
        if (url.contains("embed")) {
            index = url.indexOf("embed/");
            id =  url.substring(index + 6, url.indexOf("?"));
        } else {
            index = url.indexOf("=");
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