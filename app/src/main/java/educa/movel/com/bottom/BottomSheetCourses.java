package educa.movel.com.bottom;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.Course;


public class BottomSheetCourses extends BottomSheetDialogFragment {
    View view;
    private ListView listView;

    private Toolbar toolbar;

    private List<Course> courseList;

    public BottomSheetCourses(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bottom_courses , container , false);
        initUI();

        String[] listItem = new String[courseList.size()];
        for (int i = 0; i < courseList.size(); i++) {
            listItem[i] = courseList.get(i).getCourse()+"";
        }


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value=adapter.getItem(position);
                Toast.makeText(getContext().getApplicationContext(),value,Toast.LENGTH_SHORT).show();

            }
        });
        return  view;
    }

    private void initUI() {
        listView = view.findViewById(R.id.course_list);
        toolbar = view.findViewById(R.id.toolbar);
    }


}
