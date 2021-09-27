package educa.movel.com.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import educa.movel.com.R;
import educa.movel.com.model.News;

public class InfoFullActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tv_title, tv_description;
    private RoundedImageView imageView;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_full);
        initUI();
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getIntentValue();

    }

    private void getIntentValue() {
        Bundle bundle = getIntent().getExtras();
        News news = (News) bundle.getSerializable("news");

        if (news != null) {
            tv_title.setText(news.getTitle());
            tv_description.setText(news.getDescricao());
            Picasso.get().load(news.getImg()).into(imageView);
        }
    }
    private void initUI() {
        imageView = findViewById(R.id.img_info);
        tv_description = findViewById(R.id.tv_description);
        tv_title = findViewById(R.id.tv_title);
        toolbar = findViewById(R.id.toolbar);
    }
}