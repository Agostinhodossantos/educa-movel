package educa.movel.com.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;

import educa.movel.com.R;
import educa.movel.com.databinding.FragmentHomeBinding;
import educa.movel.com.ui.VideoActivity;
import educa.movel.com.ui.VideosListActivity;
import educa.movel.com.ui.game.GameActivity;
import educa.movel.com.ui.login.LoginActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private CardView card_video, card_concourse;
    private View root;
    private ShapeableImageView img_background;
    private ShapeableImageView img_background_2;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
            homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    root = binding.getRoot();


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {


            }
        });

        initUI();

        card_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideosListActivity.class);
                startActivity(intent);
            }
        });

        card_concourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: 9/22/2021 check if current user is loged //
                if(true) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), GameActivity.class);
                    startActivity(intent);
                }

            }
        });

        shapeImage();
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

    private void initUI() {
        img_background = root.findViewById(R.id.img_background);
        card_video = root.findViewById(R.id.card_video);
        card_concourse = root.findViewById(R.id.card_concourse);
        img_background_2 = root.findViewById(R.id.img_background_2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}