package com.example.shubham.intentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_1;
    private EditText et_2;
    private EditText et_3;
    private EditText et_4;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doSignUp();
    }

    public void doSignUp() {
        et_1 = findViewById(R.id.editText1);
        et_2 = findViewById(R.id.editText2);
        et_3 = findViewById(R.id.editText3);
        et_4 = findViewById(R.id.editText4);
        signup = findViewById(R.id.button1);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_1.getText().toString();
                String email = et_2.getText().toString();
                String mobile = et_3.getText().toString();
                String password = et_4.getText().toString();
                if(username.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Username can't empty",Toast.LENGTH_SHORT).show();
                } else if(email.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Email can't empty",Toast.LENGTH_SHORT).show();
                } else if(mobile.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Mobile can't empty",Toast.LENGTH_SHORT).show();
                } else if(password.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Password can't empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
                    intent.putExtra("username",username);
                    intent.putExtra("email",email);
                    intent.putExtra("mobile",mobile);
                    intent.putExtra("password",password);
                    startActivity(intent);
                }
            }
        });
    }

}
