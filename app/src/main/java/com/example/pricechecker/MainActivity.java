package com.example.pricechecker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 500; // Delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 3000; // Time in milliseconds between successive task executions
    Button location;
    SearchView sV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        location = findViewById(R.id.button2);
        sV= findViewById(R.id.searchBar);
        viewPager = findViewById(R.id.viewPager1);

        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return false;
            }
        };
        viewPager.setAdapter(adapter);

        // Auto scroll the ViewPager
        autoScrollViewPager();
    }

    private void autoScrollViewPager() {
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == Integer.MAX_VALUE) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
