package educa.movel.com.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.bottom.BottomSheetCourses;
import educa.movel.com.model.Exhibitor;
import educa.movel.com.model.Image;
import educa.movel.com.rv.RvGallery;
import educa.movel.com.utils.InitFirebase;
import educa.movel.com.utils.Utils;

public class ExhibitorActivity extends AppCompatActivity {

    List<Image> imageList = new ArrayList<>();
    private RecyclerView rv_gallery;
    private ImageView img_play;
    private Button btn_courses,btn_galery;
    private TextView btn_expand, tv_description, tv_title;

    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitor);

        getIntentValue();
        initUI();


        btn_galery.setOnClickListener( v -> {
            Intent intent = new Intent(ExhibitorActivity.this , ImagesFullScreen.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("images" , (Serializable) imageList);
            intent.putExtras(bundle);
            intent.putExtra("position" , 0);
            startActivity(intent);
        });

        btn_expand.setOnClickListener(v -> {

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
        getIntentValue();
        initUI();
        getExhibitorValues();
        getImages();
        initGallery();
    }

    private void getExhibitorValues() {
        InitFirebase.initFirebase()
             .child("institution")
             .child(uid)
             .addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                     Exhibitor exhibitor = snapshot.getValue(Exhibitor.class);
                     tv_title.setText(exhibitor.getInstitution_name());
                     tv_description.setText(Utils.getCutStr(exhibitor.getInstitution_description(), 225));

                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {
                     Log.d("exhibitor", error.toString());
                 }
             });
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

    private void getIntentValue() {
        Intent intent = getIntent();
        if(getIntent()!=null && getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            if(!bundle.getString("uid").equals(null)) {
                uid = intent.getExtras().getString("uid");
            }else {
                Toast.makeText(this, "Ocoreu uma falha", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    private void initUI() {
        tv_title = findViewById(R.id.tv_title);
        tv_description = findViewById(R.id.tv_description);
        rv_gallery = findViewById(R.id.rv_gallery);
        img_play = findViewById(R.id.img_play);
        btn_courses = findViewById(R.id.btn_courses);
        btn_expand = findViewById(R.id.btn_expand);
        btn_galery = findViewById(R.id.btn_galery);
    }
}

