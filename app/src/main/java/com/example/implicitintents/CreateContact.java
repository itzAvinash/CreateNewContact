package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity implements View.OnClickListener {
    EditText etName,etPhone,etWeb,etLocation;
    ImageView ivHappy,ivSad,ivNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        etName=findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etWeb = findViewById(R.id.etWeb);
        etLocation = findViewById(R.id.etLocation);

        ivHappy = findViewById(R.id.ivHappy);
        ivSad = findViewById(R.id.ivSad);
        ivNormal = findViewById(R.id.ivNormal);

        ivNormal.setOnClickListener(this);
        ivSad.setOnClickListener(this);
        ivHappy.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(etName.getText().toString().isEmpty() || etLocation.getText().toString().isEmpty() || etWeb.getText().toString().isEmpty() || etPhone.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent =new Intent();
            intent.putExtra("name",etName.getText().toString().trim());
            intent.putExtra("phone",etPhone.getText().toString().trim());
            intent.putExtra("web",etWeb.getText().toString().trim());
            intent.putExtra("map",etLocation.getText().toString().trim());

            if (view.getId() == R.id.ivHappy) {
                intent.putExtra("mood","happy");
            }
            else if(view.getId() == R.id.ivNormal)
            {
                intent.putExtra("mood","normal");
            }
            else{
                intent.putExtra("mood","sad");
            }

            setResult(RESULT_OK,intent);
            CreateContact.this.finish();
        }
        
    }
}