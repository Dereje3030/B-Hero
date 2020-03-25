package com.ethioptech.b_hero;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText email_editText;
    private EditText password_editText;
    private Button login_btn;
    private TextView register_textView;
    FirebaseAuth firebaseAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_editText=findViewById(R.id.editText_username);
        password_editText=findViewById(R.id.editText_password);
        login_btn=findViewById(R.id.button_login);
        register_textView=findViewById(R.id.textView_createAccount);
        firebaseAuth=FirebaseAuth.getInstance();
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=email_editText.getText().toString();
                String password=password_editText.getText().toString();
                if(TextUtils.isEmpty(email)){
                    email_editText.setError("Email is required!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    password_editText.setError("Password is required!");
                    return;
                }
                if(password.length()<6){
                    password_editText.setError("password must have > 6 character");
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent=new Intent(LoginActivity.this,Main2Activity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Email or Password is incorrect",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        register_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,CreateAccoutActivity.class);
                startActivity(intent);
            }
        });
    }
}
