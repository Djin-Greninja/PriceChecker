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
                return R.drawable.bellpepper_info;
            case 1:
                return R.drawable.ginger_info;
            case 2:
                return R.drawable.lettuce_info;
            case 3:
                return R.drawable.squash_info;
            case 4:
                return R.drawable.carrots_info;
            case 5:
                return R.drawable.broccoli_info;
            case 6:
                return R.drawable.beef_info;
            case 7:
                return R.drawable.pork_info;
            case 8:
                return R.drawable.lamb_info;
            case 9:
                return R.drawable.rabbit_info;
            case 10:
                return R.drawable.drumsticks_info;
            case 11:
                return R.drawable.wings_info;
            // Add more cases for additional pages if needed
            default:
                return R.drawable.notfound;// Default background if no matching page ID
        }
    }
}