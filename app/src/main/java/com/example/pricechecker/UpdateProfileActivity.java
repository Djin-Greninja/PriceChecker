package com.example.pricechecker;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfileActivity extends AppCompatActivity {

    private DatabaseReference usersRef;
    private Button save_btn;
    public EditText username_u, emailadd_u, gender_u, fullName_u, birthDate_u;
    private ImageButton backbtn;
    private FirebaseAuth mAuth; // Used for Firebase authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_update_profile_page);

        usersRef = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();

        save_btn = findViewById(R.id.update_account_btn);
        username_u = findViewById(R.id.username_update);
        emailadd_u = findViewById(R.id.login_email_update);
        gender_u = findViewById(R.id.gender_signup_update);
        fullName_u = findViewById(R.id.fNAme_update);
        birthDate_u = findViewById(R.id.birthdate_update);
        backbtn = findViewById(R.id.backbtn_update_profile);

        // Get the current user's ID
        String userId = mAuth.getCurrentUser().getUid();

        // Fetch the user's information from the Firebase Realtime Database
        usersRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the user's information from the DataSnapshot
                String username = dataSnapshot.child("username").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String fullName = dataSnapshot.child("fullName").getValue(String.class);
                String gender = dataSnapshot.child("gender").getValue(String.class);
                String birthDate = dataSnapshot.child("birthDate").getValue(String.class);

                // Display the user's information in the respective fields
                username_u.setText(username);
                emailadd_u.setText(email);
                fullName_u.setText(fullName);
                gender_u.setText(gender);
                birthDate_u.setText(birthDate);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });

        // Set up a TextWatcher for the birthDate_u field
        BirthdateTextWatcher birthdateTextWatcher = new BirthdateTextWatcher(birthDate_u, false);
        // Attach the TextWatcher to the birthDate_u EditText field
        birthDate_u.addTextChangedListener(birthdateTextWatcher);

        // Set OnClickListener for the save button
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the updated user information from the fields
                String updatedUsername = username_u.getText().toString();
                String updatedEmail = emailadd_u.getText().toString();
                String updatedFullName = fullName_u.getText().toString();
                String updatedGender = gender_u.getText().toString();
                String updatedBirthDate = birthDate_u.getText().toString();

                // Update the user's information in the Firebase Realtime Database
                usersRef.child(userId).child("username").setValue(updatedUsername);
                usersRef.child(userId).child("email").setValue(updatedEmail);
                usersRef.child(userId).child("fullName").setValue(updatedFullName);
                usersRef.child(userId).child("gender").setValue(updatedGender);
                usersRef.child(userId).child("birthDate").setValue(updatedBirthDate)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Display toast message indicating account update
                                    Toast.makeText(UpdateProfileActivity.this, "Account updated", Toast.LENGTH_SHORT).show();
                                    // Return to the profile fragment
                                    finish();
                                } else {
                                    // Handle unsuccessful update
                                    Toast.makeText(UpdateProfileActivity.this, "Failed to update account", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        // Set OnClickListener for the back button
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity and go back to the previous screen
                finish();
            }
        });
    }
    }