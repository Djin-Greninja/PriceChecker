package com.example.pricechecker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    Button location;
    SearchView sV;
    ViewPager viewPager;
    private final long DELAY_MS = 500; // Delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 6000; // Time in milliseconds between successive task executions
    private final Handler handler = new Handler();
    private Runnable runnable;
    private FirebaseAuth mAuth;//Used for firebase authentication

    private DatabaseReference usersRef;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        usersRef = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();
        // Find views by ID using the 'view' object
        location = view.findViewById(R.id.button2);
        sV = view.findViewById(R.id.searchBar);
        viewPager = view.findViewById(R.id.viewPager1);


        // Fetch the username from Firebase Realtime Database
        String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        usersRef.child(userId).child("fullName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String fullName = dataSnapshot.getValue(String.class);
                    // Update UI with the fetched username
                    // For example, if you have a TextView to display the username:
                    TextView textViewUsername = view.findViewById(R.id.username_id);
                    textViewUsername.setText(fullName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });



        List<String> data = new ArrayList<>();
        data.add("Item 1");
        data.add("Item 2");
        data.add("Item 3");

        HorizontalPagerAdapter adapter = new HorizontalPagerAdapter(getActivity(), data);
        viewPager.setAdapter(adapter);

        // Start auto-scrolling
        startAutoScroll();

        // Set listener for item selection events

        // Return the inflated view
        return view;
    }

    private void startAutoScroll() {
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int totalItems = viewPager.getAdapter().getCount();
                int nextItem = (currentItem + 1) % totalItems; // Calculate the next item index
                viewPager.setCurrentItem(nextItem, true); // Set the current item with smooth scrolling
                handler.postDelayed(this, PERIOD_MS);
            }
        };

        handler.postDelayed(runnable, DELAY_MS);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stop auto-scrolling when the activity is destroyed to avoid memory leaks
        handler.removeCallbacks(runnable);
    }
}
