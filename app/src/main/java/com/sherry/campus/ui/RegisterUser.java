package com.sherry.campus.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sherry.campus.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

@BindView(R.id.editTextName)
    EditText eTxtName;
@BindView(R.id.editTextEmail)
EditText eTxtEmail;

    @BindView(R.id.editTextPassword)
    EditText eTxtPassword;

    @BindView(R.id.buttonRegister)
    Button btnRegister;

    FirebaseAuth auth;
    ProgressDialog progressDialog;
    com.sherry.campus.model.User user;

    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);
        btnRegister.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();

        user= new com.sherry.campus.model.User();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
    }
    /*void saveUser(){
        userCollection.document(uid).set(user).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(RegisterUser.this,"User Saved in DB",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterUser.this,HomeActivity.class);
                startActivity(intent);
                finish();
                progressDialog.dismiss();
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterUser.this,"Error while saving User",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });}*/

        void registerUser(){

            progressDialog.show();

            auth.createUserWithEmailAndPassword(user.email,user.password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                uid = task.getResult().getUser().getUid();
                                // uid can be saved in the Firebase Database along with other details of user

                                Toast.makeText(RegisterUser.this,user.name+" Registered Successfully !!",Toast.LENGTH_LONG).show();
                                Log.i("User","User Registered: "+uid);


                            }

progressDialog.dismiss();
                        }
                    }).addOnFailureListener(this,new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("User","User Registration Failed: "+e.getMessage());
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            });

        }

      /*void loginUser(){
            progressDialog.show();
            auth.signInWithEmailAndPassword(user.email,user.password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(RegisterUser.this, user.name + " Login Success !!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterUser.this,SplashActivity.class);
                                startActivity(intent);
                                finish();
                                progressDialog.dismiss();
                            }
                        }
                    })
                    .addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("User","User Login Failed: "+e.getMessage());
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    });


        }*/

    @Override
    public void onClick(View view) {
            user.name = eTxtName.getText().toString().trim();
            user.email = eTxtEmail.getText().toString().trim();
            user.password = eTxtPassword.getText().toString().trim();

            registerUser();
            //loginUser();
    }
}
