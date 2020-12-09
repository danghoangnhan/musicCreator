package code.name.monkey.retromusic.activities;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import code.name.monkey.retromusic.R;

public class LoginActivity extends AppCompatActivity {
    private EditText ename;
    private EditText epassword;
    private Button elogin;
    private TextView eattempsinfo;

    private String Username = "Admin";
    private String Password = "12345678";
    boolean isValid = false;
    private int counter=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ename = findViewById(R.id.editTextTextPersonName);
        epassword = findViewById(R.id.editTextTextPersonName2);
        elogin = findViewById(R.id.button);
        eattempsinfo=findViewById(R.id.textView4);

        elogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputname = ename.getText().toString();
                String inputPassword = epassword.getText().toString();

                if (inputname.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this,"Please enter all the details correctly",Toast.LENGTH_SHORT).show();
                } else {

                    isValid = validate(inputname, inputPassword);
                    if (!isValid) {

                        counter--;
                        Toast.makeText(LoginActivity.this,"Incorrect credentials entered!",Toast.LENGTH_SHORT).show();
                        eattempsinfo.setText("No. of attempts remaining : "+counter);
                        if (counter == 0) {
                            elogin.setEnabled(false);
                        }
                    }else{
                        Toast.makeText(LoginActivity.this,"Login successful!",Toast.LENGTH_SHORT).show();
                        //Add the code to go to new activity
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

    }
    private boolean validate(String name,String password){
        if(name.equals(Username)&&password.equals(Password))
        {
            return true;
        }
        return false;
    }
}