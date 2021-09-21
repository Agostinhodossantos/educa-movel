package educa.movel.com.ui.game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.Question;

public class GameActivity extends AppCompatActivity {

    private LinearLayout btn_option_1, btn_option_2, btn_option_3, btn_option_4;
    private TextView tv_option_1, tv_option_2, tv_option_3, tv_option_4;
    private TextView tv_question;
    private List<Question> questionList = new ArrayList<>();
    private int currentClickedBtn = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.BLACK);

        initUI();

        btn_option_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentClickedBtn = 1;
                checkAnswer();
            }
        });


    }

    private void checkAnswer() {

        switch (currentClickedBtn) {
            case 1:
                if (isCorrect(, tv_option_1.getText().toString().trim()))
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    private boolean isCorrect(String correctAnswer, String userAnswer) {
        if (correctAnswer.equalsIgnoreCase(userAnswer)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initUI();
        getQuestions();
    }

    private void getQuestions() {
        questionList.add(new Question("2 + 2", "4", "2", "6", "8", "4"));
        questionList.add(new Question("3 + 2", "4", "5", "6", "8", "5"));
        questionList.add(new Question("5 + 5", "4", "2", "6", "10", "10"));
        questionList.add(new Question("2 + 2", "4", "2", "6", "8", "4"));
    }

    private void initUI() {
        btn_option_1 = (LinearLayout) findViewById(R.id.btn_option_1);
        btn_option_2 = (LinearLayout) findViewById(R.id.btn_option_2);
        btn_option_3 = (LinearLayout) findViewById(R.id.btn_option_3);
        btn_option_4 = (LinearLayout) findViewById(R.id.btn_option_4);

        tv_option_1 = (TextView) findViewById(R.id.tv_option_1);
        tv_option_2 = (TextView) findViewById(R.id.tv_option_2);
        tv_option_3 = (TextView) findViewById(R.id.tv_option_3);
        tv_option_4 = (TextView) findViewById(R.id.tv_option_4);
    }
}