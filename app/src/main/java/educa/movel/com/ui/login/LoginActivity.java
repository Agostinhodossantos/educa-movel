package educa.movel.com.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import educa.movel.com.R;
import educa.movel.com.ui.game.GameActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText ed_email, ed_password;
    private Button btn_enter;
    private FirebaseAuth mAuth;
    private ProgressBar progress;
    private TextView tv_create_account, tv_forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        initUI();

        tv_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = ed_email.getText().toString().trim();
                password = ed_password.getText().toString().trim();

                if (email.isEmpty()) {
                    ed_email.requestFocus();
                    ed_email.setHintTextColor(Color.RED);
                } else if (password.isEmpty()) {
                    ed_password.requestFocus();
                    ed_password.setHintTextColor(Color.RED);
                } else {
                    setProgressState(true);

                    login(email, password);
                }
            }
        });

    }

    private void setProgressState(boolean inProgress) {
        if (inProgress) {
            progress.setVisibility(View.VISIBLE);
            btn_enter.setVisibility(View.GONE);
        } else {
            progress.setVisibility(View.GONE);
            btn_enter.setVisibility(View.VISIBLE);
        }
    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent = new Intent(LoginActivity.this, GameActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, ""+e, Toast.LENGTH_LONG).show();
                        setProgressState(false);
                    }
                });
    }

    private void initUI() {
        tv_create_account = findViewById(R.id.tv_create_acount);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        progress = findViewById(R.id.progress);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        btn_enter = findViewById(R.id.btn_enter);
    }
}