package com.example.pricechecker;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pricechecker.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.action_home) {
                replaceFragment(new HomeFragment());
                return true;
            } else if (item.getItemId() == R.id.action_discover) {
                replaceFragment(new DiscoverFragment());
                return true;
            } else if (item.getItemId() == R.id.action_grocery_list) {
                replaceFragment(new ListFragment());
                return true;
            } else if (item.getItemId() == R.id.action_profile) {
                replaceFragment(new ProfileFragment());
                return true;
            }
            return false;
        });
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_layout,fragment);
        fragmentTransaction.commit();
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
**/