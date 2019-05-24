package com.example.demo4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FindWeather extends AppCompatActivity {
    public EditText find_edit;
    public Button find_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findweather);
        find_edit = findViewById(R.id.find_edit);
        find_button = findViewById(R.id.find_button);
        find_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(find_edit.getText().toString().equals("")){
                    Toast.makeText(FindWeather.this,"请输入查找地区",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent= new Intent(FindWeather.this, ShowWeather.class);
                    String ss =find_edit.getText().toString();
                    intent.putExtra("city",ss);
                    startActivity(intent);
                }
            }
        });
    }
}
