package educa.movel.com.ui.game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

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
    private int currentQuestion = 0;
    private int playerPoints = 0;
    private AlertDialog dialog = null;
    private static final int GAME_QUESTION = 20;
    private LottieAnimationView animationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.custom_black));

        initUI();
        getQuestions();
        setQuestionUI();

        btn_option_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentClickedBtn = 1;
                checkAnswer();
            }
        });


        btn_option_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentClickedBtn = 2;
                checkAnswer();
            }
        });


        btn_option_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentClickedBtn = 3;
                checkAnswer();
            }
        });


        btn_option_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentClickedBtn = 4;
                checkAnswer();
            }
        });


    }

    private void checkAnswer() {
        disableBtns();
        String correctAnswer = questionList
                .get(currentQuestion)
                .getCorrectAnswer()
                .trim();


        if (isCorrect(correctAnswer, getAnswer())) {
            animationView.setVisibility(View.VISIBLE);
            playerPoints++;
            nextGame();
        } else  {

            switch (currentClickedBtn) {

                case 1:
                    btn_option_1.setBackgroundResource(R.drawable.btn_bg_erro);
                    break;
                case 2:
                    btn_option_2.setBackgroundResource(R.drawable.btn_bg_erro);
                    break;
                case 3:
                    btn_option_3.setBackgroundResource(R.drawable.btn_bg_erro);
                    break;
                case 4:
                    btn_option_4.setBackgroundResource(R.drawable.btn_bg_erro);
                    break;
            }

            nextGame();
        }

    }

    private void nextGame() {
        showTheCorrectAnswer();
        updateUI();
    }

    private void updateUI() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_option_1.setBackgroundResource(R.drawable.btn_bg_1);
                btn_option_2.setBackgroundResource(R.drawable.btn_bg_1);
                btn_option_3.setBackgroundResource(R.drawable.btn_bg_1);
                btn_option_4.setBackgroundResource(R.drawable.btn_bg_1);

                currentQuestion++;
                animationView.setVisibility(View.GONE);

                if (currentQuestion > questionList.size() - 1 || currentQuestion == 21) {
                    endGame();
                } else {
                    setQuestionUI();
                    enableBtns();
                }

            }
        }, 4000);


    }

    private void setQuestionUI() {

        tv_option_1.setText(questionList.get(currentQuestion).getAnswer1());
        tv_option_2.setText(questionList.get(currentQuestion).getAnswer2());
        tv_option_3.setText(questionList.get(currentQuestion).getAnswer3());
        tv_option_4.setText(questionList.get(currentQuestion).getAnswer4());
        tv_question.setText(questionList.get(currentQuestion).getQuestion());
    }

    private void showTheCorrectAnswer() {

        if (tv_option_1.getText().toString().trim().equalsIgnoreCase(questionList.get(currentQuestion)
                .getCorrectAnswer().trim())) {
            btn_option_1.setBackgroundResource(R.drawable.btn_bg_certo);
        }

        if (tv_option_2.getText().toString().trim().equalsIgnoreCase(questionList.get(currentQuestion)
                .getCorrectAnswer().trim())) {
            btn_option_2.setBackgroundResource(R.drawable.btn_bg_certo);
        }

        if (tv_option_3.getText().toString().trim().equalsIgnoreCase(questionList.get(currentQuestion)
                .getCorrectAnswer().trim())) {
            btn_option_3.setBackgroundResource(R.drawable.btn_bg_certo);
        }

        if (tv_option_4.getText().toString().trim().equalsIgnoreCase(questionList.get(currentQuestion)
                .getCorrectAnswer().trim())) {
            btn_option_4.setBackgroundResource(R.drawable.btn_bg_certo);
        }
    }

    private String getAnswer() {
        String answer = "";
        switch (currentClickedBtn) {
            case 1:
                answer = tv_option_1.getText().toString().trim().toLowerCase();
                break;
            case 2:
                answer = tv_option_2.getText().toString().trim().toLowerCase();
                break;
            case 3:
                answer = tv_option_3.getText().toString().trim().toLowerCase();
                break;
            case 4:
                answer = tv_option_4.getText().toString().trim().toLowerCase();
                break;
            default:
                break;
        }
        return answer;
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

    private void endGame() {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.end_game, null);

        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    private void disableBtns() {
        btn_option_1.setClickable(false);
        btn_option_2.setClickable(false);
        btn_option_3.setClickable(false);
        btn_option_4.setClickable(false);
    }

    private void enableBtns() {
        btn_option_1.setClickable(true);
        btn_option_2.setClickable(true);
        btn_option_3.setClickable(true);
        btn_option_4.setClickable(true);
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

        tv_question = (TextView) findViewById(R.id.tv_question);

        animationView = findViewById(R.id.animationView);
    }

}