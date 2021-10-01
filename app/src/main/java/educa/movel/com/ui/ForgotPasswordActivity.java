package educa.movel.com.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import educa.movel.com.R;
import educa.movel.com.utils.CheckField;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText ed_email;
    private Button btn_send;
    private LottieAnimationView animationView;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initUI();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField();
            }

            private void checkField() {
                animationView.setVisibility(View.VISIBLE);
                logo.setVisibility(View.GONE);
                String email = ed_email.getText().toString().trim();
                if (new CheckField().isEmpty(email)) {
                    ed_email.requestFocus();
                    ed_email.setHintTextColor(Color.RED);
                } else {
                    resetPassword(email);
                }
            }
        });
    }

    private void resetPassword(String email) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
             .addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                     animationView.setVisibility(View.GONE);
                     logo.setVisibility(View.VISIBLE);
                     Toast.makeText(getApplicationContext(), "Ocoreu uma falha", Toast.LENGTH_LONG).show();
                 }
             })
             .addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {
                     if (task.isSuccessful()) {
                         Toast.makeText(getApplicationContext(), "Veja o link no seu email.", Toast.LENGTH_LONG).show();
                     }
                     animationView.setVisibility(View.GONE);
                     logo.setVisibility(View.VISIBLE);
                 }
             }
             );
    }

    private void initUI() {
        logo = findViewById(R.id.logo);
        animationView = findViewById(R.id.animationView);
        ed_email = findViewById(R.id.ed_email);
        btn_send = findViewById(R.id.btn_enter);
    }
}