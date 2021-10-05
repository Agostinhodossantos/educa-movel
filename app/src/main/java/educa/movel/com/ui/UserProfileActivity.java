package educa.movel.com.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import educa.movel.com.R;
import educa.movel.com.model.User;
import educa.movel.com.utils.InitFirebase;

public class UserProfileActivity extends AppCompatActivity {

    private Button btnLogout;
    FirebaseAuth auth;
    private TextView tv_name, tv_phone, tv_email, tv_location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        auth = FirebaseAuth.getInstance();

        initUI();
        getUser();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Toast.makeText(UserProfileActivity.this, "Sessão terminada com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void getUser() {
        String uid = auth.getUid();
        InitFirebase.initFirebase()
                .child("users")
                .child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);

                        if (user != null) {
                            tv_email.setText(user.getEmail());
                            tv_name.setText(user.getName());
                            tv_location.setText(user.getResidence());
                            tv_phone.setText(user.getContact());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initUI() {
        btnLogout = findViewById(R.id.btn_logout);
        tv_name = findViewById(R.id.tv_name);
        tv_phone = findViewById(R.id.tv_call);
        tv_email = findViewById(R.id.tv_email);
        tv_location = findViewById(R.id.tv_location);
    }
}