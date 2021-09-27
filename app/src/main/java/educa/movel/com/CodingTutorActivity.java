package educa.movel.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.CornerFamily;

import java.util.ArrayList;
import java.util.List;

import educa.movel.com.model.TutorVideo;
import educa.movel.com.rv.RvNews;
import educa.movel.com.rv.RvTutor;

public class CodingTutorActivity extends AppCompatActivity {

    private MaterialCardView cardView;
    private RecyclerView rv_tutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding_tutor);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.purple_500));


        initUI();
        initRv();

    }

    private void initRv() {
        List<TutorVideo> videoList = new ArrayList<>();
        videoList.add(new TutorVideo("url", "thumb", "uid", "Android"));
        videoList.add(new TutorVideo("url", "thumb", "uid", "Android"));
        videoList.add(new TutorVideo("url", "thumb", "uid", "Android"));
        videoList.add(new TutorVideo("url", "thumb", "uid", "Android"));
        videoList.add(new TutorVideo("url", "thumb", "uid", "Android"));
        videoList.add(new TutorVideo("url", "thumb", "uid", "Android"));
        RvTutor rvNews = new RvTutor(CodingTutorActivity.this, videoList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        rv_tutor.setLayoutManager(layoutManager);
        rv_tutor.setAdapter(rvNews);
    }

    private void initUI() {
        rv_tutor = findViewById(R.id.rv_tutor);
    }
}