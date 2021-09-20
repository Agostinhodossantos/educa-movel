package educa.movel.com.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.bottom.BottomSheetCourses;
import educa.movel.com.model.Image;
import educa.movel.com.rv.RvGallery;

public class ExhibitorActivity extends AppCompatActivity {

    List<Image> imageList = new ArrayList<>();
    private RecyclerView rv_gallery;
    private ImageView img_play;
    private Button btn_courses,btn_galery;
    private TextView btn_expand, tv_description;
    private ExpandableRelativeLayout expandableRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitor);

        initUI();
        expandableRelativeLayout.collapse();

        btn_galery.setOnClickListener( v -> {
            Intent intent = new Intent(ExhibitorActivity.this , ImagesFullScreen.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("images" , (Serializable) imageList);
            intent.putExtras(bundle);
            intent.putExtra("position" , 0);
            startActivity(intent);
        });

        btn_expand.setOnClickListener(v -> {
            if (expandableRelativeLayout.isExpanded()) {
                expandableRelativeLayout.setDuration(500);
                expandableRelativeLayout.toggle();
                tv_description.setVisibility(View.VISIBLE);
                btn_expand.setText("Ver mais");
            } else {
                btn_expand.setText("Ver menos");
                expandableRelativeLayout.setDuration(500);
                expandableRelativeLayout.toggle();
                tv_description.setVisibility(View.GONE);
            }

        });

        img_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExhibitorActivity.this, VideoActivity.class);
                intent.putExtra("url", "https://www.youtube.com/watch?v=x-Fs-wAmbRY");
                startActivity(intent);
            }
        });

        btn_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetCourses bottomSheetCourses = new BottomSheetCourses();
                bottomSheetCourses.show(getSupportFragmentManager() , "bottomSheet");
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
        tv_description = findViewById(R.id.tv_description);
        rv_gallery = findViewById(R.id.rv_gallery);
        img_play = findViewById(R.id.img_play);
        btn_courses = findViewById(R.id.btn_courses);
        btn_expand = findViewById(R.id.btn_expand);
        expandableRelativeLayout = findViewById(R.id.expandableLayout);
        btn_galery = findViewById(R.id.btn_galery);
    }
}

