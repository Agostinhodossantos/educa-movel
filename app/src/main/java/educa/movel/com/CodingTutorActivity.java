package educa.movel.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.CornerFamily;

public class CodingTutorActivity extends AppCompatActivity {

    private MaterialCardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding_tutor);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.purple_500));

        cardView = findViewById(R.id.card_tutor);

        //float radius = getResources().getDimension(R.dimen.my_corner_radius);
//        cardView.setShapeAppearanceModel(
//                cardView.getShapeAppearanceModel()
//                        .toBuilder()
//                        .setTopLeftCorner(CornerFamily.ROUNDED,..)
//      .setTopRightCorner(CornerFamily.ROUNDED,..)
//      .setBottomRightCorner(CornerFamily.ROUNDED,radius)
//                .setBottomLeftCornerSize(0)
//                .build());

    }
}