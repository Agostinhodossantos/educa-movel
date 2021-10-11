package educa.movel.com.api;

import educa.movel.com.model.Question;
import educa.movel.com.utils.Utils;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPICall {
    String BASE_URL = Utils.api_url;
    @GET()
    Call<Question> getData();
}
