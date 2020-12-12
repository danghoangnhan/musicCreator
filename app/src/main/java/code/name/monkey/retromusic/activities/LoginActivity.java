package code.name.monkey.retromusic.activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import code.name.monkey.retromusic.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class LoginActivity extends AppCompatActivity {
    private String TAG = LoginActivity.class.getSimpleName();
    private EditText    ename;
    private EditText    epassword;
    private Button      elogin;
    private Button      eregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addControl() {
        ename = findViewById(R.id.editTextTextPersonName);
        epassword = findViewById(R.id.editTextTextPersonName2);
        elogin = findViewById(R.id.button);
        eregister = findViewById(R.id.button3);


    }
    private void addEvent() {


        elogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (epassword == null && ename == null)
                        return;
                    elogin.setEnabled(true);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://140.136.151.130/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    JsonApi Jsonapi = retrofit.create(JsonApi.class);
                    Call<authentication> placeHolderApis = Jsonapi.login(ename.getText().toString(), epassword.getText().toString());
                    placeHolderApis.enqueue(new Callback<authentication>() {
                        @Override
                        public void onResponse(@NonNull Call<authentication> call, @NonNull Response<authentication> response) {
                            if (response.isSuccessful()) {
                                Toast toast=Toast.makeText(LoginActivity.this,"welcome "+ename.getText().toString(),Toast.LENGTH_SHORT);
                                toast.show();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                        @Override
                        public void onFailure(@NonNull Call<authentication> call, @NonNull Throwable t) {
                            Toast toast=Toast.makeText(LoginActivity.this,"login failed",Toast.LENGTH_SHORT);
                            toast.show();
                            t.printStackTrace();
                        }
                    });
                }
                catch (Exception e){
                    Toast toast=Toast.makeText(LoginActivity.this,e.toString(),Toast.LENGTH_SHORT);
                    toast.show();
                }
                setResult(Activity.RESULT_OK);
            }
        });}

}