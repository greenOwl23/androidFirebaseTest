package com.example.firedb3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity implements View.OnClickListener {
    FirebaseDatabase database;
    DatabaseReference dbRef;
    EditText et_value;
    Button btn_write;
    Context context;
    DatabaseReference dbRoot = FirebaseDatabase.getInstance().getReference();
    DatabaseReference messageRef = dbRoot.child("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        database = FirebaseDatabase.getInstance();
        et_value = findViewById(R.id.et_value);
        btn_write = findViewById(R.id.btn_write);
        btn_write.setOnClickListener(this);
    }

    protected void writeDB(){
        String ref = et_value.getTag().toString();
        String value = et_value.getText().toString();
        dbRef = database.getReference("message");
        messageRef.push().setValue(value);
    }


    @Override
    protected void onStart(){
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_write:
                writeDB();
                break;
        }

    }
}
