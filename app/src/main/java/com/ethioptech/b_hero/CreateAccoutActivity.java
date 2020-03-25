package com.ethioptech.b_hero;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ethioptech.b_hero.helper.UserA;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateAccoutActivity extends AppCompatActivity {
    private EditText etFullName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etCity;
    private RadioGroup rbtnBloodGroup;
    private RadioGroup radioGroup;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private UserA user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_accout);
        setView();

        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        user=new UserA();
    }
    public void createAccount(){
        Log.d("userNull","isUserNull "+(user==null));
        if (!user.getEmail().isEmpty() &&
                !user.getFullName().isEmpty() &&
                !user.getPassword().isEmpty() &&
                !user.getCity().isEmpty()&&
                !user.getBlood().isEmpty()){
            mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("createAccount", "createUserWithEmail:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                updateUI(user);
                                addDataToFirestore();
                                showToast("Authentication Successful");

                 // call the Homepage
//                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("createFailur", "createUserWithEmail:failure", task.getException());
                               showToast("Authentication failed");
//                                updateUI(null);
                            }
                        }
                    });
        }
        else{
            showToast("Please fill all fields");
        }
    }
 public void getUserInput(){
       user.setFullName(etFullName.getText().toString());
       user.setEmail(etEmail.getText().toString());
       user.setCity(etCity.getText().toString());
       user.setPassword(etPassword.getText().toString());

       String bloodGroup=" ";
     int selectedBloodRadioButtonId=radioGroup.getCheckedRadioButtonId();
     if (selectedBloodRadioButtonId==R.id.rbnA)
         bloodGroup="A";
     if (selectedBloodRadioButtonId==R.id.rbnB)
         bloodGroup="B";
     if (selectedBloodRadioButtonId==R.id.rbnC)
         bloodGroup="C";
     if (selectedBloodRadioButtonId==R.id.rbnAB)
         bloodGroup="AB";
     user.setBlood(bloodGroup);


     String gender=" ";
     int selectedRadioButtonId=radioGroup.getCheckedRadioButtonId();
     if (selectedRadioButtonId==R.id.rbnMale)
         gender="Female";
     if (selectedRadioButtonId==R.id.rbnFemale)
         gender="Male";
     user.setGender(gender);
 }
    public void setView(){
        rbtnBloodGroup=findViewById(R.id.bloodGroupC);
        etCity=findViewById(R.id.cityC);
        etEmail=findViewById(R.id.emailC);
        etPassword=findViewById(R.id.passwordC);
        radioGroup=findViewById(R.id.genderC);
        etFullName=findViewById(R.id.fullNameC);
    }

    public void addDataToFirestore(){
        db.collection("users")
                .document(user.getEmail())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                     showToast("User info added successfully");
                        Intent intent=new Intent(CreateAccoutActivity.this,Main2Activity.class);
                        startActivity(intent);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                      showToast("Error to upload user info");
                    }
                });

    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void createAccount(View view) {
        getUserInput();
        createAccount();
    }
}