package educa.movel.com.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.Image;
import educa.movel.com.pager.ViewPagerAdapterFull;


public class ImagesFullScreen extends AppCompatActivity {

    private List<Image> mList = new ArrayList<>();
    private int position = 0;
    private ViewPager viewPager ;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_tela_cheia);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.BLACK);

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        mList = (List<Image>) bundle.getSerializable("images");

        if (intent != null ){

            position = intent.getIntExtra("position",0);


        }

        viewPager = findViewById(R.id.vp_publicidade_id);
        ViewPagerAdapterFull mAdapterIntro = new ViewPagerAdapterFull(this,mList);
        viewPager.setAdapter(mAdapterIntro);
        viewPager.setCurrentItem(position);
    }
}
