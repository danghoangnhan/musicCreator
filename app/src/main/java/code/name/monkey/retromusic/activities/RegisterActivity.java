package code.name.monkey.retromusic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import code.name.monkey.retromusic.R;

public class RegisterActivity extends AppCompatActivity {
    private String TAG = LoginActivity.class.getSimpleName();
    private EditText    rname;
    private EditText    rpassword;
    private Button      rregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControl();
    }
    private void addControl() {
        rname = findViewById(R.id.editTextTextPersonName);
        rpassword = findViewById(R.id.editTextTextPersonName2);
        rregister = findViewById(R.id.button2);


    }
}