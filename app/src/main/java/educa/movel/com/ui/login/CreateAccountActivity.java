package educa.movel.com.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import educa.movel.com.R;
import educa.movel.com.model.User;
import educa.movel.com.utils.CheckField;

public class CreateAccountActivity extends AppCompatActivity {

    private Button btn_create;
    private EditText ed_email, ed_name, ed_number, ed_password;
    private TextView have_account;
    private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

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
        } else if (field.isEmail(email)) {
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
        }
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