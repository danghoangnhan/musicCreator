package code.name.monkey.retromusic.activities;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonApi {
    @POST("login")
    Call<authentication> login(@Query("username") String username, @Query("password") String password);
}
