package educa.movel.com.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import educa.movel.com.R;

public class VIdeoPlayerActivity extends AppCompatActivity {

    private VideoView videoView;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        videoView = findViewById(R.id.videoView);
        getIntentValues();

        videoView.setVideoURI(Uri.parse(url));
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();
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