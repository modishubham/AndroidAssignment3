package com.example.shubham.intentdemo;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    private final int CAMERA_REQUEST_CODE = 1;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private EditText et1;
    private Button bt1;
    private Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tv1 = findViewById(R.id.tvName);
        tv2 = findViewById(R.id.tvEmail);
        tv3 = findViewById(R.id.tvMobile);
        tv4 = findViewById(R.id.tvPassword);
        et1 = findViewById(R.id.etSearchBox);
        bt1 = findViewById(R.id.btn1);
        bt2 = findViewById(R.id.btn2);

        String name = getIntent().getExtras().getString("username");
        String email = getIntent().getExtras().getString("email");
        String mobile = getIntent().getExtras().getString("mobile");
        String password = getIntent().getExtras().getString("password");
        tv1.setText(name);
        tv2.setText(email);
        tv3.setText(mobile);
        tv4.setText(password);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = et1.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, data);
                if(intent.resolveActivity(getPackageManager())!=null) {
                    startActivity(intent);
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermission(Manifest.permission.CAMERA, CAMERA_REQUEST_CODE);
            }
        });

    }

    private void askPermission(String permission,int requestCode) {
        if(ContextCompat.checkSelfPermission(this, permission)!= PackageManager.PERMISSION_GRANTED) {
            // if permission is not granted
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        } else {
            // if permission is already granted
            Toast.makeText(this,"permission is already granted",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case CAMERA_REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"camera permission is granted",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this,"camera permission is not granted",Toast.LENGTH_LONG).show();
                }
        }
    }
}
