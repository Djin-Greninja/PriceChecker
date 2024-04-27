package com.example.pricechecker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    ImageButton logout_btn, back_btn;
    Button acc_info, password_set, pp_set, notif_set,feedback;
    Switch toggle_notif;
    TextView username_profile, email_profile;

    private FirebaseAuth mAuth;//Used for firebase authentication

    private DatabaseReference usersRef;


    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_page, container, false);
        usersRef = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();

        logout_btn = view.findViewById(R.id.logout_btn_profile);
        username_profile = view.findViewById(R.id.uname_profile);
        email_profile = view.findViewById(R.id.mail_profile);
        acc_info = view.findViewById(R.id.account_info_btn);
        password_set = view.findViewById(R.id.password_profile);
        pp_set = view.findViewById(R.id.privacy_policy);
        notif_set = view.findViewById(R.id.notif_btn);
        toggle_notif = view.findViewById(R.id.switch1);
        feedback = view.findViewById(R.id.fback);

        acc_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start a new activity or perform any other desired action
                // For example, you can start a new activity
                Intent intent = new Intent(getActivity(), UpdateProfileActivity.class);
                startActivity(intent);
            }
        });

        password_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start a new activity or perform any other desired action
                // For example, you can start a new activity
                Intent intent = new Intent(getActivity(), UpdatePassword.class);
                startActivity(intent);
            }
        });

        // Fetch the username from Firebase Realtime Database
        String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        usersRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String fullName = dataSnapshot.child("fullName").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    // Update UI with the fetched username
                    // For example, if you have a TextView to display the username:
                    TextView textViewFullName = view.findViewById(R.id.uname_profile);
                    textViewFullName.setText(fullName);
                    TextView textViewEmail = view.findViewById(R.id.mail_profile);
                    textViewEmail.setText(email);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform logout
                FirebaseAuth.getInstance().signOut();
                // Redirect user to login screen or any other desired action
                // For example, you can start LoginActivity
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                // Finish current activity to prevent user from going back to it using the back button
                getActivity().finish();
            }
        });

        return view;

    }

}