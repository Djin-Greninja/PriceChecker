package com.example.pricechecker;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button log_btn;
    ImageButton back_btn;
    EditText log_email;
    TextInputEditText login_password;
    TextView forgetpwd, signup_intent;
    FirebaseUser currentUser;//used to store current user of account
    FirebaseAuth mAuth;//Used for firebase authentication
    ProgressDialog loadingBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        log_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_text_password);
        back_btn = findViewById(R.id.back_btn_login);
        log_btn = findViewById(R.id.login_btn);
        forgetpwd = findViewById(R.id.forgetPassword);
        signup_intent = findViewById(R.id.register_go);
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        currentUser = mAuth.getCurrentUser();


        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllowUserToLogin();
            }
        });
        signup_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToRegister();
            }
        });
        //if user forgets the password then to reset it
        forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPasswordUser();
            }
        });
    }

    private void resetPasswordUser() {
        String email = log_email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(LoginActivity.this, "Please enter your email id", Toast.LENGTH_SHORT).show();
        } else {
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Reset Email sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void sendUserToRegister() {
        //When user wants to create a new account send user to Register Activity
        Intent registerIntent = new Intent(this, SignUpActivity.class);
        startActivity(registerIntent);
    }

    private void AllowUserToLogin() {
        String email = log_email.getText().toString().trim();
        String pwd = login_password.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(LoginActivity.this, "Please enter email id", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
        } else {
            //When both email and password are available log in to the account
            //Show the progress on Progress Dialog
            loadingBar.setTitle("Sign In");
            loadingBar.setMessage("Please wait ,Because Good things always take time");
            mAuth.signInWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())//If account login successful print message and send user to main Activity
                            {
                                sendToMainActivity();
                                Toast.makeText(LoginActivity.this, "Welcome to Reference Center", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            } else//Print the error message incase of failure
                            {
                                String msg = task.getException().toString();
                                Toast.makeText(LoginActivity.this, "Error: " + msg, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    protected void onStart() {
        //Check if user has already signed in if yes send to mainActivity
        //This to avoid signing in everytime you open the app.
        super.onStart();
        if (currentUser != null) {
            sendToMainActivity();
        }
    }
    private void sendToMainActivity() {
        //This is to send user to MainActivity
        Intent  MainIntent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(MainIntent);
    }
}