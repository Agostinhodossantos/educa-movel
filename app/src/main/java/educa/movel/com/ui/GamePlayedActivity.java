package educa.movel.com.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import educa.movel.com.R;
import educa.movel.com.api.MyAPICall;
import educa.movel.com.model.Question;
import educa.movel.com.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GamePlayedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_played);

        // Retrofit Builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.api_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // instance for interface
        MyAPICall myAPICall = retrofit.create(MyAPICall.class);

        Call<Question> call = myAPICall.getData();

        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if (response.code() != 200) {

                } else {
                   // response.body().getAnswer2();
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {

            }
        });

    }
}