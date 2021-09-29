package educa.movel.com.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import educa.movel.com.model.User;
import educa.movel.com.utils.CheckField;
import educa.movel.com.utils.InitFirebase;

public class CreateAccountActivity extends AppCompatActivity {

    private Button btn_create;
    private EditText ed_email, ed_name, ed_number, ed_password;
    private TextView have_account;
    private ProgressBar progress;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();
        initUI();

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFields();
            }
        });
    }

    private void checkFields() {
        CheckField field = new CheckField();
        String name = ed_name.getText().toString().trim();
        String email = ed_email.getText().toString().trim();
        String number = ed_number.getText().toString().trim();
        String password = ed_password.getText().toString().trim();

        if (field.isEmail(name)) {
            ed_name.requestFocus();
            ed_name.setHintTextColor(Color.RED);
        } else if (field.isEmpty(email)) {
            ed_email.requestFocus();
            ed_email.setHintTextColor(Color.RED);
        } else if (field.isEmpty(number)) {
            ed_number.requestFocus();
            ed_number.setHintTextColor(Color.RED);
        } else if (field.isEmpty(password)) {
            ed_password.requestFocus();
            ed_password.setHintTextColor(Color.RED);
        } else {
            setProgressState(true);

            User user = new User("", number, "" , email ,
                    "", "", name, password, "", "", "");
            authUser(user);

        }
    }

    private void authUser(User user) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                      String uid = authResult.getUser().getUid();
                      user.setUserId(uid);
                      setUser(user);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        setProgressState(false);
                        Toast.makeText(CreateAccountActivity.this, "Ocorreu uma falha: "+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void setUser(User user) {
        InitFirebase
                .initFirebase()
                .child("users")
                .child(user.getUserId())
                .setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        // set data in result and back to login
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user", user);
                        intent.putExtras(bundle);
                        CreateAccountActivity.this.setResult(Activity.RESULT_OK, intent);
                        CreateAccountActivity.this.finish();
                    }
                });
    }

    private void setProgressState(boolean inProgress) {
        if (inProgress) {
            progress.setVisibility(View.VISIBLE);
            btn_create.setVisibility(View.GONE);
        } else {
            progress.setVisibility(View.GONE);
            btn_create.setVisibility(View.VISIBLE);
        }
    }

    private void initUI() {
        btn_create = (Button) findViewById(R.id.btn_enter);
        ed_email = findViewById(R.id.ed_email);
        ed_name = findViewById(R.id.ed_name);
        ed_number = findViewById(R.id.ed_number);
        ed_password = findViewById(R.id.ed_password);
        progress = findViewById(R.id.progress);
    }
}