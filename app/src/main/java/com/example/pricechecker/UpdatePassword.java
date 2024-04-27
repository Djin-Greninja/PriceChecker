package com.example.pricechecker;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdatePassword extends AppCompatActivity {

    ImageButton back_btn;
    TextView fullname, email_p;
    TextInputLayout old_pwd, new_pwd, conf_newpwd;
    Button update_pwd_btn;
    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        usersRef = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();
        back_btn = findViewById(R.id.backbtn_update_password);
        fullname = findViewById(R.id.password_fname);
        email_p = findViewById(R.id.passwrd_email);
        old_pwd = findViewById(R.id.old_password);
        new_pwd = findViewById(R.id.new_password);
        conf_newpwd = findViewById(R.id.login_layout_password);
        update_pwd_btn = findViewById(R.id.update_password_btn);

        // Set OnClickListener for the update password button
        update_pwd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the old password, new password, and confirm new password
                String oldPassword = old_pwd.getEditText().getText().toString().trim();
                String newPassword = new_pwd.getEditText().getText().toString().trim();
                String confirmNewPassword = conf_newpwd.getEditText().getText().toString().trim();

                // Perform validation
                if (TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmNewPassword)) {
                    // Show a toast message indicating all fields are required
                    Toast.makeText(UpdatePassword.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return; // Exit method
                }

                if (!TextUtils.equals(newPassword, confirmNewPassword)) {
                    // Show a toast message indicating new passwords do not match
                    Toast.makeText(UpdatePassword.this, "New passwords do not match", Toast.LENGTH_SHORT).show();
                    return; // Exit method
                }

                // Validate old password against the password stored in the database
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    // First, reauthenticate the user with their current password
                    AuthCredential credential = EmailAuthProvider.getCredential(currentUser.getEmail(), oldPassword);
                    currentUser.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // If reauthentication is successful, update the user's password
                                        currentUser.updatePassword(newPassword)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            // Password updated successfully
                                                            // Now, update the password in the Firebase Realtime Database
                                                            String userId = currentUser.getUid();
                                                            usersRef.child(userId).child("password").setValue(newPassword);
                                                            // Display a success message
                                                            Toast.makeText(UpdatePassword.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                                                            // Finish the activity to return to the previous screen
                                                            finish();
                                                        } else {
                                                            // Failed to update password in Firebase Authentication
                                                            Toast.makeText(UpdatePassword.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    } else {
                                        // Reauthentication failed
                                        Toast.makeText(UpdatePassword.this, "Incorrect old password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // Handle the case where the current user is null
                    // This might happen if the user is not authenticated
                    // You can redirect the user to the login screen or display a message
                }
            }
        });

        // Set OnClickListener for the back button
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity and go back to the previous screen
                finish();
            }
        });
    }
}
