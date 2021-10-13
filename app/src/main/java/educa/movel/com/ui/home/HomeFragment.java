package educa.movel.com.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import educa.movel.com.CodingTutorActivity;
import educa.movel.com.R;
import educa.movel.com.databinding.FragmentHomeBinding;
import educa.movel.com.model.CurrentLocation;
import educa.movel.com.ui.BooksActivity;
import educa.movel.com.ui.NewsActivity;
import educa.movel.com.ui.UserProfileActivity;
import educa.movel.com.ui.VideoActivity;
import educa.movel.com.ui.VideosListActivity;
import educa.movel.com.ui.game.GameActivity;
import educa.movel.com.ui.login.LoginActivity;
import educa.movel.com.utils.InitFirebase;
import educa.movel.com.utils.Utils;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private CardView card_video, card_concourse, card_programing_tutor,card_book, card_news,card_vocational_test;
    private View root;
    private ShapeableImageView img_background;
    private ShapeableImageView img_background_2;
    private FirebaseUser user;
    private ImageView img_start;
    private AlertDialog dialog = null;
    private TextView tv_location, tv_time;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        user =  firebaseAuth.getCurrentUser();

        checkApplicationVersion();

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {


            }
        });

        initUI();

        card_vocational_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.pravaler.com.br/testes/teste-vocacional-online-gratis";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        card_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewsActivity.class);
                startActivity(intent);
            }
        });

        img_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user =  firebaseAuth.getCurrentUser();

                if (user == null) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), UserProfileActivity.class);
                    startActivity(intent);
                }

            }
        });

        card_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideosListActivity.class);
                startActivity(intent);
            }
        });

        card_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BooksActivity.class);
                startActivity(intent);
            }
        });

        card_concourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user =  firebaseAuth.getCurrentUser();
                if(user == null) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), GameActivity.class);
                    startActivity(intent);
                }

            }
        });

        card_programing_tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CodingTutorActivity.class);
                intent.putExtra("url", "https://www.youtube.com/watch?v=4ynvsrkamt8");
                startActivity(intent);
            }
        });

        shapeImage();
        getCurrentLocation();
        return root;
    }

    private void shapeImage() {
        float radius = 25f;
        img_background.setShapeAppearanceModel(img_background.getShapeAppearanceModel()
                .toBuilder()
                .setBottomRightCorner(CornerFamily.ROUNDED,radius)
                .setBottomLeftCorner(CornerFamily.ROUNDED,radius)
                .build());

        img_background_2.setShapeAppearanceModel(img_background_2.getShapeAppearanceModel()
                .toBuilder()
                .setBottomRightCorner(CornerFamily.ROUNDED,radius)
                .setBottomLeftCorner(CornerFamily.ROUNDED,radius)
                .build());
    }

    private void getCurrentLocation() {
        InitFirebase.initFirebase()
                .child("educa_movel")
                .child("location")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        CurrentLocation location = snapshot.getValue(CurrentLocation.class);
                        tv_location.setText(location.getLocation());
                        tv_time.setText(location.getStart()+"H - "+location.getEnd()+"H");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void initUI() {
        tv_location = root.findViewById(R.id.tv_location);
        tv_time = root.findViewById(R.id.tv_time);
        card_news = root.findViewById(R.id.card_news);
        card_book = root.findViewById(R.id.card_book);
        img_background = root.findViewById(R.id.img_background);
        card_video = root.findViewById(R.id.card_video);
        card_vocational_test = root.findViewById(R.id.card_vocational_test);
        card_concourse = root.findViewById(R.id.card_concourse);
        card_programing_tutor = root.findViewById(R.id.card_programing_tutor);
        img_background_2 = root.findViewById(R.id.img_background_2);
        img_start = root.findViewById(R.id.profile);
    }

    private void checkApplicationVersion() {
        InitFirebase initFirebase = new InitFirebase();
        initFirebase
                .initFirebase()
                .child("educa_movel")
                .child("version")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String version = snapshot.getValue(String.class);

                        if (!version.equals(Utils.app_version)) {
                            updateApp();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void updateApp() {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        View mView = getLayoutInflater().inflate(R.layout.dialog_update, null);

        Button btn_update = mView.findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Utils.play_store_link));
                startActivity(browserIntent);
            }
        });


        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}