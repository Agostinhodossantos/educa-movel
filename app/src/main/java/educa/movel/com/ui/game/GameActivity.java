package educa.movel.com.ui.game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import educa.movel.com.R;
import educa.movel.com.model.GameUser;
import educa.movel.com.model.Question;
import educa.movel.com.model.User;
import educa.movel.com.ui.login.LoginActivity;
import educa.movel.com.utils.InitFirebase;
import educa.movel.com.utils.Utils;

public class GameActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 20000;
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
    private FirebaseUser userAuth;
    private TextView tv_points, tv_timer;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private Button btn_end_game;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.custom_black));

        firebaseAuth = FirebaseAuth.getInstance();
        userAuth =  firebaseAuth.getCurrentUser();

        if (userAuth == null) {
            startActivity(new Intent(this, LoginActivity.class));

        }

        initUI();

        getQuestions();


        getUser();
        startTimer();

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
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Toast.makeText(GameActivity.this, ""+keyCode, Toast.LENGTH_SHORT).show();
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            finish();
//            Toast.makeText(GameActivity.this, "Hello", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                endGame(false);
            }
        }.start();

        mTimerRunning = true;
    }

    private void updateCountDownText() {
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormated = String.format(Locale.getDefault(), "%02d", seconds);
        tv_timer.setText(timeLeftFormated);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    private void resetTimer() {
        pauseTimer();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        startTimer();
       // updateCountDownText();
    }

    private void checkAnswer() {
        resetTimer();
        disableBtns();
        String correctAnswer = questionList
                .get(currentQuestion)
                .getCorrectAnswer()
                .trim();


        if (isCorrect(correctAnswer, getAnswer())) {
            animationView.setVisibility(View.VISIBLE);
            playerPoints();
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

            decreasePoints();
            nextGame();


        }

    }

    private void createUser(User user) {

        GameUser userGame = new GameUser(user.getUserId(), user.getUserId(), user.getName(), getUSerCategory(user), Utils.getDate(), 0);

        InitFirebase.initFirebase()
                .child("educa_movel")
                .child("user_list")
                .child(user.getUserId())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("Snap", snapshot+"");
                        if (snapshot.exists() != true) {
                            InitFirebase.initFirebase()
                                    .child("educa_movel")
                                    .child("user_list")
                                    .child(user.getUserId())
                                    .setValue(userGame)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            getPoints();
                                        }
                                    });
                        } else {
                            endGame(true);
                            pauseTimer();
                            getPoints();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private String getUSerCategory(User user) {
        String category = "" ;
        String contact = user.getContact();
        if (!contact.isEmpty()) {
            if (contact.startsWith("84") || contact.startsWith("85")) {
                category =  "vodacom";
            } else if (contact.startsWith("87") || contact.startsWith("86")) {
                category =  "movitel";
            } else if (contact.startsWith("82") || contact.startsWith("83")) {
                category =  "tmcel";
            } else {
                category =  "bdq";
            }
        }
        return category;
    }

    private void getUser() {
        String userUid = userAuth.getUid();

        InitFirebase.initFirebase()
                .child("users")
                .child(userUid)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);

                        if (user != null) {
                            createUser(user);
                        } else {
                            Toast.makeText(GameActivity.this, "Erro: usuario nao encotrado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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

                if (currentQuestion == questionList.size() - 1 || currentQuestion == 20) {
                    endGame(false);
                } else {
                    setQuestionUI();
                    enableBtns();
                }

            }
        }, 3000);


    }

    private void setQuestionUI() {


        tv_option_1.setText(questionList.get(currentQuestion).getAnswer1());
        tv_option_2.setText(questionList.get(currentQuestion).getAnswer2());
        tv_option_3.setText(questionList.get(currentQuestion).getAnswer3());
        tv_option_4.setText(questionList.get(currentQuestion).getAnswer4());
        tv_question.setText(questionList.get(currentQuestion).getQuestion());
    }

    private void showTheCorrectAnswer() {

        if ("answer1".equalsIgnoreCase(questionList.get(currentQuestion)
                .getCorrectAnswer().trim())) {
            btn_option_1.setBackgroundResource(R.drawable.btn_bg_certo);
        }

        if ("answer2".equalsIgnoreCase(questionList.get(currentQuestion)
                .getCorrectAnswer().trim())) {
            btn_option_2.setBackgroundResource(R.drawable.btn_bg_certo);
        }

        if ("answer3".equalsIgnoreCase(questionList.get(currentQuestion)
                .getCorrectAnswer().trim())) {
            btn_option_3.setBackgroundResource(R.drawable.btn_bg_certo);
        }

        if ("answer4".equalsIgnoreCase(questionList.get(currentQuestion)
                .getCorrectAnswer().trim())) {
            btn_option_4.setBackgroundResource(R.drawable.btn_bg_certo);
        }
    }

    private void playerPoints() {
        playerPoints++;
        tv_points.setText(playerPoints+"");
        InitFirebase.initFirebase()
                .child("educa_movel")
                .child("user_list")
                .child(userAuth.getUid())
                .child("score")
                .setValue(playerPoints);
    }

    private void decreasePoints() {
        playerPoints--;

        if (playerPoints < 0) {
            endGame(false);
        } else {
            tv_points.setText(playerPoints+"");
            InitFirebase.initFirebase()
                    .child("educa_movel")
                    .child("user_list")
                    .child(userAuth.getUid())
                    .child("score")
                    .setValue(playerPoints);
        }

    }

    private void getPoints() {

        InitFirebase.initFirebase()
                .child("educa_movel")
                .child("user_list")
                .child(userAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        GameUser user = snapshot.getValue(GameUser.class);
                        playerPoints = user.getScore();
                        tv_points.setText(user.getScore()+"");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private String getAnswer() {
        String answer = "";
        switch (currentClickedBtn) {
            case 1:
                answer = "answer1";
                break;
            case 2:
                answer = "answer2";
                break;
            case 3:
                answer = "answer3";
                break;
            case 4:
                answer = "answer4";
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

    }

    private void getQuestions() {
        Random random = new Random();
        int rand = random.nextInt(3);
        int list0[] ={0,1,2,3};
        int list1[] ={3,2,1,0};
        int list2[] ={2,3,0,1};
        int list3[] ={1,2,0,3};
        int list[];

        switch (rand) {
            case 0:
                list = list0;
                break;
            case 1:
                list = list1;
                break;
            case 2:
                list = list2;
                break;
            case 3:
                list = list3;
                break;
            default:
                list = list0;
                break;

        }

        for (int i = 0; i < list.length; i++) {
           switch (list[i]) {
                case 0:
                    getCultureQuestions(list[i] == list[list.length - 1]);
                case 1:
                    getHistoryQuestions(list[i] == list[list.length - 1]);
                case 2:
                    getOtherQuestions(list[i] == list[list.length - 1]);
                case 3:
                    getTechQuestions(list[i] == list[list.length - 1]);

            }
        }



        //questionList.add(new Question("5 + 5", "4", "2", "6", "10", "10"));
        //questionList.add(new Question("2 + 2", "4", "2", "6", "8", "4"));
    }



    private void getTechQuestions(Boolean isEnd) {

        InitFirebase.initFirebase()
                .child("educa_movel")
                .child("quiz")
                .child("tech")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot objSnapshot1: snapshot.getChildren()) {
                            Question question = objSnapshot1.getValue(Question.class);
                            questionList.add(question);
                        }
                        if (isEnd) {
                            setQuestionUI();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void getOtherQuestions(Boolean isEnd) {

        InitFirebase.initFirebase()
                .child("educa_movel")
                .child("quiz")
                .child("other")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot objSnapshot1: snapshot.getChildren()) {
                            Question question = objSnapshot1.getValue(Question.class);
                            questionList.add(question);
                        }
                        if (isEnd) {
                            setQuestionUI();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void getHistoryQuestions(Boolean isEnd) {
        InitFirebase.initFirebase()
                .child("educa_movel")
                .child("quiz")
                .child("history")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot objSnapshot1: snapshot.getChildren()) {
                            Question question = objSnapshot1.getValue(Question.class);
                            questionList.add(question);
                        }
                        if (isEnd) {
                            setQuestionUI();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void getCultureQuestions(Boolean isEnd) {
        InitFirebase.initFirebase()
                .child("educa_movel")
                .child("quiz")
                .child("culture")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot objSnapshot1: snapshot.getChildren()) {
                            Question question = objSnapshot1.getValue(Question.class);
                            questionList.add(question);
                        }
                        if (isEnd) {
                            setQuestionUI();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void endGame(Boolean isFinal) {
        pauseTimer();
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.end_game, null);
        TextView tv_message = mView.findViewById(R.id.tv_message);

        if (isFinal) {
            tv_message.setVisibility(View.VISIBLE);
        }



        btn_end_game = mView.findViewById(R.id.btn_end_game);
        btn_end_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                dialog.dismiss();
                finish();
            }
        });



        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        dialog.show();

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                dialog.dismiss();
                finish();
                return false;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        pauseTimer();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        resetTimer();
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

        tv_timer = (TextView) findViewById(R.id.tv_timer);
        tv_points = (TextView) findViewById(R.id.tv_points);

        tv_question = (TextView) findViewById(R.id.tv_question);

        animationView = findViewById(R.id.animationView);
    }

}