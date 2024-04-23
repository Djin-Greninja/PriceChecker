package com.example.pricechecker;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private DatabaseReference usersRef;
    private Button  cont_btn;
    public EditText username, emailadd;
    public TextInputLayout password, cpassword;
    private TextView terms;
    private ImageButton button;
    private FirebaseAuth mAuth;//Used for firebase authentication
    private ProgressDialog loadingBar;//Used to show the progress of the registration process

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        usersRef = FirebaseDatabase.getInstance().getReference("users");

        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username_signup);
        emailadd = findViewById(R.id.login_email);
        password = findViewById(R.id.login_layout_password);
        cpassword = findViewById(R.id.layout_cpassword);
        button = findViewById(R.id.back_btn_signup);
        cont_btn = findViewById(R.id.login_btn);
        terms = findViewById(R.id.textView6);
        loadingBar = new ProgressDialog(this);

        String clickableString = getString(R.string.clickable_string);

        // Convert HTML string to Spanned
        Spanned spannedText = Html.fromHtml(clickableString);

        // Create a SpannableString from the Spanned
        SpannableString spannableString = new SpannableString(spannedText);

        // Find the index of the clickable part in the full text
        int startIndex = clickableString.indexOf("terms of use");
        int endIndex = startIndex + "terms of use".length();

        // Set a ClickableSpan on the clickable part
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Handle click action for terms of use
                openTermsOfUse(); // You can define this method to open the terms of use page
            }
        };

        // Set the ForegroundColorSpan to make the text appear in blue color
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.blue));

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the text to the TextView
        terms.setText(spannableString);

        // Make the TextView clickable and handle link clicks
        terms.setMovementMethod(LinkMovementMethod.getInstance());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {navigateToPreviousActivity();}
        });


        cont_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewAccount();
            }
        });
    }

    private void createNewAccount() {
        String uname = username.getText().toString().trim();
        String email = emailadd.getText().toString().trim();
        String pwd = password.getEditText().getText().toString();
        String confpwd = cpassword.getEditText().getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter email id",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pwd))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(uname))
        {
            Toast.makeText(this,"Please enter username",Toast.LENGTH_SHORT).show();
        }
        if (!pwd.equals(confpwd)) {
            Toast.makeText(this,"Passwords do not match",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            //When both email and password are available create a new account
            //Show the progress on Progress Dialog
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Please wait, we are creating new Account");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();
            mAuth.createUserWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // User signup successful
                                // Get the unique user ID generated by Firebase Authentication
                                String userId = mAuth.getCurrentUser().getUid();
                                // Create a User object with the user's data
                                User user = new User(uname, email, pwd);
                                usersRef.child(userId).setValue(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> databaseTask) {
                                                if (databaseTask.isSuccessful()) {
                                                    // Data saved successfully
                                                    Toast.makeText(SignUpActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                                    sendUserToLoginActivity();
                                                } else {
                                                    // Error saving data to database
                                                    Log.e("Firebase", "Error saving data to database: " + databaseTask.getException().getMessage());
                                                    Toast.makeText(SignUpActivity.this, "Error creating account", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                // Authentication failed
                                Log.e("Firebase", "Error authenticating user: " + task.getException().getMessage());
                                Toast.makeText(SignUpActivity.this, "Error creating account", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void sendUserToLoginActivity() {
        //This is to send user to Login Activity.
        Intent loginIntent = new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(loginIntent);
    }



    private void openTermsOfUse() {
        String url = "https://google.com";
        // Open the terms of use page in a web browser
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    private void navigateToPreviousActivity() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}