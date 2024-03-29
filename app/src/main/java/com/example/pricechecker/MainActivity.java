package com.example.pricechecker;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

