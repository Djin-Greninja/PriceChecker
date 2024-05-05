package com.example.pricechecker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Pages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);

        int pageId = getIntent().getIntExtra("id", 0);
        // Set background dynamically based on page ID
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ConstraintLayout constraintLayout = findViewById(R.id.item_details);
        constraintLayout.setBackgroundResource(getBackgroundResource(pageId));


    }

    private int getBackgroundResource(int pageId) {
        switch (pageId) {
            case 0:
                return R.drawable.bellpepper;
            case 1:
                return R.drawable.item_details_ginger;
            case 2:
                return R.drawable.lettuce;
            case 3:
                return R.drawable.squash;
            case 4:
                return R.drawable.carrots;
            case 5:
                return R.drawable.brocolli;
            case 6:
                return R.drawable.beef;
            case 7:
                return R.drawable.pork;
            case 8:
                return R.drawable.lamb;
            case 9:
                return R.drawable.rabbit;
            case 10:
                return R.drawable.drumsticks;
            case 11:
                return R.drawable.wings;
            // Add more cases for additional pages if needed
            default:
                return R.drawable.beef;// Default background if no matching page ID
        }
    }
}