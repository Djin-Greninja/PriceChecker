package com.example.pricechecker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Pages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);

        int pageId = getIntent().getIntExtra("id", 0);

        TextView textPageId = findViewById(R.id.textPageId);
        textPageId.setText("PAGE : " + pageId);
        // Set background dynamically based on page ID
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) 
        ConstraintLayout constraintLayout = findViewById(R.id.item_details);
        constraintLayout.setBackgroundResource(getBackgroundResource(pageId));
        
        
        
    }

    private int getBackgroundResource(int pageId) {
        switch (pageId) {
            case 1:
                return R.drawable.item_details_ginger;
            case 2:
                return R.drawable.item_details_ginger;
            // Add more cases for additional pages if needed
            default:
                return // Default background if no matching page ID
        }
}