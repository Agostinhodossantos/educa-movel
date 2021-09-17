package educa.movel.com.ui.expo;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import educa.movel.com.R;
import educa.movel.com.databinding.FragmentExpoBinding;
import educa.movel.com.model.Exhibitor;
import educa.movel.com.model.ImageAds;
import educa.movel.com.pager.ViewPagerAdapter;
import educa.movel.com.rv.RvExhibitor;
import me.relex.circleindicator.CircleIndicator;

public class ExpoFragment extends Fragment {

    ViewPager viewPager;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 800;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 6000; //
    CircleIndicator circleIndicator;
    List<ImageAds> imageAds = new ArrayList<>();
    private RecyclerView rv_exhibitor;
    private ExpoViewModel expoViewModel;
    private FragmentExpoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
            expoViewModel = new ViewModelProvider(this).get(ExpoViewModel.class);

            binding = FragmentExpoBinding.inflate(inflater, container, false);
            View root = binding.getRoot();
            initUI(root);
            initAds();
            initExpo();


         expoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    private void initExpo() {
        List<Exhibitor> exhibitorList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            exhibitorList.add(new Exhibitor("1", "Google Glass",
                    "https://firebasestorage.googleapis.com/v0/b/igepe-6785f.appspot.com/o/exhibitor%2F66b8b93a-f7c7-4601-b9b6-7a901eb91fb1?alt=media&token=e7f57190-eed2-4ec0-86aa-0bf5393e5413", "84365568", "Mocambique, Maputo","agostinho@gmail.com",
                    "", "ss"));
        }

        RvExhibitor adapter = new RvExhibitor(getContext() , exhibitorList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2 , LinearLayoutManager.VERTICAL);
        rv_exhibitor.setLayoutManager(layoutManager);
        rv_exhibitor.setAdapter(adapter);
    }

    private void initAds() {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getContext() , getImages());
        viewPager.setAdapter(pagerAdapter);
        circleIndicator.setViewPager(viewPager);
        slider();
    }
    List<ImageAds> getImages() {
        for (int i = 0; i < 5; i++) {
            imageAds.add(new ImageAds("https://firebasestorage.googleapis.com/v0/b/igepe-6785f.appspot.com/o/exhibitor%2F66b8b93a-f7c7-4601-b9b6-7a901eb91fb1?alt=media&token=e7f57190-eed2-4ec0-86aa-0bf5393e5413"));
        }
        return imageAds;
    }

    private void slider() {
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {

                if (viewPager.getCurrentItem() == imageAds.size() - 1 ) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    private void initUI(View view) {
        rv_exhibitor = view.findViewById(R.id.rv_exhibitor);
        viewPager = view.findViewById(R.id.vp_ads);
        circleIndicator = (CircleIndicator) view.findViewById(R.id.indicator);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}