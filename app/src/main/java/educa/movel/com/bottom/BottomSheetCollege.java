package educa.movel.com.bottom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.College;


public class BottomSheetCollege extends BottomSheetDialogFragment {
    View view;
    private ListView listView;

    private Toolbar toolbar;

    private List<College> collegeList;

    public BottomSheetCollege(List<College> collegeList) {
        this.collegeList = collegeList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bottom_colleges , container , false);
        initUI();

        String[] listItem = new String[collegeList.size()];
        for (int i = 0; i < collegeList.size(); i++) {
            listItem[i] = collegeList.get(i).getCollege()+"";
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
        listView = view.findViewById(R.id.college_list);
        toolbar = view.findViewById(R.id.toolbar);
    }


}
