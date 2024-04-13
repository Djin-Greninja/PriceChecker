package com.example.pricechecker;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button location;
    SearchView sV;
    ViewPager viewPager;
    private final long DELAY_MS = 500; // Delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 6000; // Time in milliseconds between successive task executions
    private Handler handler = new Handler();
    private Runnable runnable;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        location = findViewById(R.id.button2);
        sV = findViewById(R.id.searchBar);
        viewPager = findViewById(R.id.viewPager1);

        List<String> data = new ArrayList<>();
        data.add("Item 1");
        data.add("Item 2");
        data.add("Item 3");

        HorizontalPagerAdapter adapter = new HorizontalPagerAdapter(this, data);
        viewPager.setAdapter(adapter);

        // Start auto-scrolling
        startAutoScroll();
        // Set listener for item selection events
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        // Handle Home item click
                        // For example, navigate to the Home fragment
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new HomeFragment())
                                .commit();
                        return true;
                    case R.id.action_discover:
                        // Handle Discover item click
                        // For example, navigate to the Discover fragment
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new DiscoverFragment())
                                .commit();
                        return true;
                    case R.id.action_grocery_list:
                        // Handle Grocery List item click
                        // For example, navigate to the Grocery List fragment
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new ListFragment())
                                .commit();
                        return true;
                    case R.id.action_profile:
                        // Handle Profile item click
                        // For example, navigate to the Profile fragment
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new ProfileFragment())
                                .commit();
                        return true;
                    default:
                        return false;
                }
            }
        });
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
    protected void onDestroy() {
        super.onDestroy();
        // Stop auto-scrolling when the activity is destroyed to avoid memory leaks
        handler.removeCallbacks(runnable);
    }
}

