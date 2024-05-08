package com.example.pricechecker;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PriceCheckActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_price_check);

        constraintLayout = findViewById(R.id.main_price_check);

        // Manually assign drawables to each index
        int selectedIndex = getIntent().getIntExtra("selected_index", 0);


        // Manually assign drawables to each index
        int[] backgroundResources = {
                R.drawable.pc_bellpepper,
                R.drawable.pc_ginger,
                R.drawable.pc_lettuce,
                R.drawable.pc_squash,
                R.drawable.pc_carrots,
                R.drawable.pc_broccli,
                R.drawable.pc_beef,
                R.drawable.pc_pork,
                R.drawable.pc_lamb,
                R.drawable.pc_rabbit,
                R.drawable.pc_drumsticks,
                R.drawable.pc_wings
        };

        // Set the background resource based on the selected index
        if (selectedIndex >= 0 && selectedIndex < backgroundResources.length) {
            constraintLayout.setBackgroundResource(backgroundResources[selectedIndex]);
        } else {
            // Handle invalid index
            // You can set a default background or finish the activity
        }
    }
}

