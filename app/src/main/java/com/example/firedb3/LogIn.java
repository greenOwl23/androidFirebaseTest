package com.example.firedb3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    public LogIn(){}
    private FirebaseAuth mAuth;
    private EditText et_email ;
    private EditText et_password ;
    private Button btn_signUp;
    private Button btn_logIn;
    String TAG ="";
    Context context = this;
    DatabaseReference dbRoot = FirebaseDatabase.getInstance().getReference();
    DatabaseReference messageRef = dbRoot.child("message");
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
        et_email = findViewById(R.id.et_login_email);
        et_password = findViewById(R.id.et_login_password);
        btn_logIn = findViewById(R.id.btn_login_login);
        btn_logIn.setOnClickListener(this);
    }

    private void signIn(){
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LogIn.this,Home.class);
                            onSuccessLogin();
                            startActivity(intent);
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    protected void onSuccessLogin(){
//        String username = user.getEmail();
        // Write new user
        writeNewUser(user.getUid(), user.getEmail());
    }
    public void writeNewUser(String userId, String email) {
        User user = new User(email);
        dbRoot.child("users").child(userId).setValue(user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login_login:
                signIn();
                break;
        }

    }
}
