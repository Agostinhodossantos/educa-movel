package educa.movel.com.ui.educaplus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.databinding.FragmentNotificationsBinding;
import educa.movel.com.model.News;
import educa.movel.com.rv.RvNews;
import educa.movel.com.utils.InitFirebase;

public class EducaplusFragment extends Fragment {

    private EducaplusViewModel educaplusViewModel;
    private FragmentNotificationsBinding binding;
    private RecyclerView rv_news;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
            educaplusViewModel =
                new ViewModelProvider(this).get(EducaplusViewModel.class);

    binding = FragmentNotificationsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    initUI(root);
    initRv();

    return root;
    }

    private void initRv() {
        List<News> newsList = new ArrayList<>();


        InitFirebase
                .initFirebase()
                .child("novidades")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot objSnapshot: snapshot.getChildren()) {
                            News news = objSnapshot.getValue(News.class);
                            newsList.add(news);
                        }
                        RvNews rvNews = new RvNews(getContext(), newsList);
                        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
                        rv_news.setLayoutManager(layoutManager);
                        rv_news.setAdapter(rvNews);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


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