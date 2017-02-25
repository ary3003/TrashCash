package com.in.trashcash.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.in.trashcash.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mPasswordView, ed_phn;
    private String str_mPasswordView;
    private String str_mEmailView;
    private Button signinbtn;

    private ProgressDialog dialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        str_mEmailView = mEmailView.getText().toString();


        mPasswordView = (EditText) findViewById(R.id.password);
        str_mPasswordView = mPasswordView.getText().toString();
        ed_phn = (EditText) findViewById(R.id.ed_phone);


        signinbtn = (Button) findViewById(R.id.email_sign_in_button);
        FloatingActionButton fabutton = (FloatingActionButton) findViewById(R.id.skipButton);
        fabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            }
        });

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Regular.ttf");
        signinbtn.setTypeface(custom_font);
        mEmailView.setTypeface(custom_font);
        mPasswordView.setTypeface(custom_font);
        ed_phn.setTypeface(custom_font);



        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    // User is signed in
                    Log.d("FirebaseAuth", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("FirebaseAuth", "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private void createAccount(String email, String password ){
        dialog = ProgressDialog.show(RegisterActivity.this, "", "Registering", false);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                        if (!task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Authentication Failed" + task.getException(), Toast.LENGTH_SHORT).show();

                        } else {
                            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                        }
                    }
                });
    }

    public void invokeLogin(View view) {
        createAccount(str_mEmailView, str_mPasswordView);
    }
}