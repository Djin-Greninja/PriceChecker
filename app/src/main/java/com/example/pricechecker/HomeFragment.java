package com.example.pricechecker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    ArrayList<RecyclerView_List> recyclerView_list;
    RecyclerView recyclerView;
    Button location;
    SearchView sV;
    ViewPager viewPager;
    TextView seeAllItem;
    ImageView vegetables,meat;
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
        seeAllItem = view.findViewById(R.id.see_all2);
        recyclerView = view.findViewById(R.id.recyclerView_home);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        vegetables = view.findViewById(R.id.veggie_cat);
        meat = view.findViewById(R.id.meat_cat);


        recyclerView_list = new ArrayList<>();
        recyclerView_list.add(new RecyclerView_List(R.drawable.bellpepper, "Bell Pepper", "₱150/kg",11));
        recyclerView_list.add(new RecyclerView_List(R.drawable.ginger, "Ginger", "₱120/kg",1));
        recyclerView_list.add(new RecyclerView_List(R.drawable.lettuce, "Fresh Lettuce","₱110/kg",7));

        RecyclerView_Adapter recyclerView_adapter = new RecyclerView_Adapter(recyclerView_list, getActivity());
        recyclerView.setAdapter(recyclerView_adapter);


        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CardView_Meat.class);
                startActivity(intent);
            }
        });
        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CardView_Vegetables.class);
                startActivity(intent);
            }
        });
        seeAllItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start a new activity or perform any other desired action
                // For example, you can start a new activity
                Intent intent = new Intent(getActivity(), CardView.class);
                startActivity(intent);
            }
        });

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
