package com.example.pricechecker;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pricechecker.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.home
            }

            return true;
        });
    }
}

   /**!bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.action_home) {
                    // Handle Home item click
                    // For example, navigate to the Home fragment
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new HomeFragment())
                            .commit();
                    return true;
                } else if (itemId == R.id.action_discover) {
                    // Handle Discover item click
                    // For example, navigate to the Discover fragment
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new DiscoverFragment())
                            .commit();
                    return true;
                } else if (itemId == R.id.action_grocery_list) {
                    // Handle Grocery List item click
                    // For example, navigate to the Grocery List fragment
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new ListFragment())
                            .commit();
                    return true;
                } else if (itemId == R.id.action_profile) {
                    // Handle Profile item click
                    // For example, navigate to the Profile fragment
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new ProfileFragment())
                            .commit();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
