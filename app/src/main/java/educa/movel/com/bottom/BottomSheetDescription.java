package educa.movel.com.bottom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import educa.movel.com.R;


public class BottomSheetDescription extends BottomSheetDialogFragment {
    View view;
    private Toolbar toolbar;
    private TextView tv_description;
    private String title, description;

    public BottomSheetDescription(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bottom_description , container , false);
        initUI();
        tv_description.setText(description);
        toolbar.setTitle(title);
        return  view;
    }

    private void initUI() {
        toolbar = view.findViewById(R.id.toolbar);
        tv_description = view.findViewById(R.id.tv_description);
    }


}
