package com.example.implicitintents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView ivMood,ivPhone,ivWeb,ivLocation;
    Button btnCreate;
    final int CREATE_CONTACT = 1;
    String name = "", number = "",web = "",location = "",mood = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivMood=findViewById(R.id.ivMood);
        ivPhone = findViewById(R.id.ivPhone);
        ivLocation = findViewById(R.id.ivLocation);
        ivWeb = findViewById(R.id.ivWeb);
        btnCreate = findViewById(R.id.btnCreate);

        ivMood.setVisibility(View.GONE);
        ivPhone.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);
        ivWeb.setVisibility(View.GONE);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,com.example.implicitintents.CreateContact.class);
                startActivityForResult(intent,CREATE_CONTACT);

            }
        });
        ivMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" +number));
                startActivity(intent);

            }
        });
        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://"+web));
                startActivity(intent);


            }
        });
       ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q=" +location));
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE_CONTACT){
            if(resultCode == RESULT_OK){
                ivMood.setVisibility(View.VISIBLE);
                ivPhone.setVisibility(View.VISIBLE);
                ivLocation.setVisibility(View.VISIBLE);
                ivWeb.setVisibility(View.VISIBLE);

                name = data.getStringExtra("name");
                number = data.getStringExtra("phone");
                web = data.getStringExtra("web");
                location = data.getStringExtra("map");
                mood = data.getStringExtra("mood");

                if(mood.equals("happy")){
                    ivMood.setImageResource(R.drawable.happy);
                }
                else if (mood.equals("sad")){
                    ivMood.setImageResource(R.drawable.sad);
                }
                else{
                    ivMood.setImageResource(R.drawable.neutral);
                }
            }
            else
            {
                Toast.makeText(this, "No data passed through!!", Toast.LENGTH_SHORT).show();
            }

        }
    }
}