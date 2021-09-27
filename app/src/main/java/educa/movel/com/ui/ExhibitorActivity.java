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
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import educa.movel.com.R;
import educa.movel.com.bottom.BottomSheetCourses;
import educa.movel.com.bottom.BottomSheetDescription;
import educa.movel.com.model.College;
import educa.movel.com.model.Course;
import educa.movel.com.model.Exhibitor;
import educa.movel.com.model.Image;
import educa.movel.com.rv.RvGallery;
import educa.movel.com.utils.InitFirebase;
import educa.movel.com.utils.Utils;

public class ExhibitorActivity extends AppCompatActivity {

    List<Image> imageList = new ArrayList<>();
    private RecyclerView rv_gallery;
    private ImageView img_play;
    private Button btn_courses,btn_college;
    private TextView btn_expand, tv_description, tv_title;
    private TextView tv_name, tv_call, tv_email, tv_location;
    private CircleImageView img_profile;
    Exhibitor exhibitor;
    private String uid;
    private List<Course> courseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitor);

        getIntentValue();
        initUI();


        btn_college.setOnClickListener( v -> {

        });

        btn_expand.setOnClickListener(v -> {
            if (exhibitor != null) {
                BottomSheetDescription bottomSheetDescription = new BottomSheetDescription(exhibitor.getInstitution_name(), exhibitor.getInstitution_description() );
                bottomSheetDescription.show(getSupportFragmentManager(), "bottomsheet");
            } else {

            }

        });

        img_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExhibitorActivity.this, VideoActivity.class);
                intent.putExtra("url", exhibitor.getVideo_link());
                startActivity(intent);
            }
        });

        btn_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetCourses bottomSheetCourses = new BottomSheetCourses(courseList);
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

    }

    private void getExhibitorValues() {
        InitFirebase.initFirebase()
                .child("institution")
                .child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        exhibitor = snapshot.getValue(Exhibitor.class);
                        tv_title.setText(exhibitor.getInstitution_name());
                        tv_description.setText(Utils.getCutStr(exhibitor.getInstitution_description(), 225));
                        tv_name.setText(exhibitor.getInstitution_name());
                        tv_call.setText(exhibitor.getPhone());
                        tv_email.setText(exhibitor.getEmail());
                        tv_location.setText(exhibitor.getLocation());

                        if (!exhibitor.getImg1().isEmpty()) {
                            Picasso.get().load(exhibitor.getImg1()).into(img_profile);
                        } else {

                        }
                        initGallery();
                        getCourses();
                        getCollege();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("exhibitor", error.toString());
                    }
                });
    }

    private void getCollege() {
        List<College> collegeList = new ArrayList<>();

        InitFirebase.initFirebase()
                .child("institution")
                .child(exhibitor.getUid())
                .child("college")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        collegeList.clear();
                        for (DataSnapshot objSnapshot : snapshot.getChildren()) {
                            College college = objSnapshot.getValue(College.class);
                            collegeList.add(college);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void getCourses() {
       InitFirebase.initFirebase()
               .child("institution")
               .child(exhibitor.getUid())
               .child("course")
               .addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       courseList.clear();
                       for (DataSnapshot objSnapshot: snapshot.getChildren()) {
                           Course course = objSnapshot.getValue(Course.class);
                           courseList.add(course);
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
    }

    private void initGallery() {

        imageList.clear();
        InitFirebase
                .initFirebase()
                .child("institution")
                .child(exhibitor.getUid())
                .child("gallery")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot objDataSnapshot: snapshot.getChildren()) {
                            Image image = objDataSnapshot.getValue(Image.class);
                            imageList.add(image);
                        }
                        RvGallery adapter = new RvGallery(ExhibitorActivity.this , imageList);
                        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1 , LinearLayoutManager.HORIZONTAL);
                        rv_gallery.setLayoutManager(layoutManager);
                        rv_gallery.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

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
        img_profile = findViewById(R.id.img_profile);
        tv_location = findViewById(R.id.tv_location);
        tv_email = findViewById(R.id.tv_email);
        tv_call = findViewById(R.id.tv_call);
        tv_name = findViewById(R.id.tv_name);
        tv_title = findViewById(R.id.tv_title);
        tv_description = findViewById(R.id.tv_description);
        rv_gallery = findViewById(R.id.rv_gallery);
        img_play = findViewById(R.id.img_play);
        btn_courses = findViewById(R.id.btn_courses);
        btn_expand = findViewById(R.id.btn_expand);
        btn_college = findViewById(R.id.btn_college);
    }
}

