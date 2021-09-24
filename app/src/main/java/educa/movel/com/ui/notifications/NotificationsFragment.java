package educa.movel.com.ui.notifications;

import android.os.Bundle;
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

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;

import java.util.ArrayList;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.databinding.FragmentNotificationsBinding;
import educa.movel.com.model.News;
import educa.movel.com.rv.RvNews;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private RecyclerView rv_news;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
            notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

    binding = FragmentNotificationsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    initUI(root);
    initRv();

    return root;
    }

    private void initRv() {
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            newsList.add(new News("1", "https://firebasestorage.googleapis.com/v0/b/igepe-6785f.appspot.com/o/exhibitor%2F66b8b93a-f7c7-4601-b9b6-7a901eb91fb1?alt=media&token=e7f57190-eed2-4ec0-86aa-0bf5393e5413",
                    "Xiaomi anuncia notebook gamer Redmi G 2021 com RTX 3060", ""));
        }

        RvNews rvNews = new RvNews(getContext(), newsList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        rv_news.setLayoutManager(layoutManager);
        rv_news.setAdapter(rvNews);
    }

    private void initUI(View root) {
        rv_news = root.findViewById(R.id.rv_news);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}