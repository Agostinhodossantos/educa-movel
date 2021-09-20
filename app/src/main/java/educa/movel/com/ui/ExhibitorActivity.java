package educa.movel.com.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.Image;
import educa.movel.com.rv.RvGallery;

public class ExhibitorActivity extends AppCompatActivity {

    List<Image> imageList = new ArrayList<>();
    private RecyclerView rv_gallery;
    private ImageView img_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitor);

        initUI();
        img_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExhibitorActivity.this, VideoActivity.class);
                intent.putExtra("url", "https://www.youtube.com/watch?v=x-Fs-wAmbRY");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        initUI();
        getImages();
        initGallery();
    }

    private void initGallery() {
        RvGallery adapter = new RvGallery(this , imageList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1 , LinearLayoutManager.HORIZONTAL);
        rv_gallery.setLayoutManager(layoutManager);
        rv_gallery.setAdapter(adapter);
    }
    private List<Image> getImages() {
        imageList.clear();
       imageList.add(new Image("1" , "https://firebasestorage.googleapis.com/v0/b/igepe-6785f.appspot.com/o/exhibitor%2F66b8b93a-f7c7-4601-b9b6-7a901eb91fb1?alt=media&token=e7f57190-eed2-4ec0-86aa-0bf5393e5413"));
       imageList.add(new Image("1" , "https://firebasestorage.googleapis.com/v0/b/igepe-6785f.appspot.com/o/exhibitor%2F66b8b93a-f7c7-4601-b9b6-7a901eb91fb1?alt=media&token=e7f57190-eed2-4ec0-86aa-0bf5393e5413"));
       imageList.add(new Image("1" , "https://firebasestorage.googleapis.com/v0/b/igepe-6785f.appspot.com/o/exhibitor%2F66b8b93a-f7c7-4601-b9b6-7a901eb91fb1?alt=media&token=e7f57190-eed2-4ec0-86aa-0bf5393e5413"));
       imageList.add(new Image("1" , "https://firebasestorage.googleapis.com/v0/b/igepe-6785f.appspot.com/o/exhibitor%2F66b8b93a-f7c7-4601-b9b6-7a901eb91fb1?alt=media&token=e7f57190-eed2-4ec0-86aa-0bf5393e5413"));
       return imageList;
    }

    private void initUI() {
        rv_gallery = findViewById(R.id.rv_gallery);
        img_play = findViewById(R.id.img_play);
    }
}

