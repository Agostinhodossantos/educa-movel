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
import educa.movel.com.R;
import educa.movel.com.databinding.FragmentHomeBinding;
import educa.movel.com.ui.VideoActivity;
import educa.movel.com.ui.VideosListActivity;
import educa.movel.com.ui.game.GameActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private CardView card_video, card_concourse;
    private View root;


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
                Intent intent = new Intent(getContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    private void initUI() {
        card_video = root.findViewById(R.id.card_video);
        card_concourse = root.findViewById(R.id.card_concourse);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}