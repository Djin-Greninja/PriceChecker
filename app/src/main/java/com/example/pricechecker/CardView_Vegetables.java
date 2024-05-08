package com.example.pricechecker;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardView_Vegetables extends AppCompatActivity {
    ImageButton bckbtn_items_veggies;
    ArrayList<RecyclerView_List> recyclerView_list;
    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.category_vegetables);

        bckbtn_items_veggies = findViewById(R.id.back_btn_veggies);
        recyclerView = findViewById(R.id.recyclerView_card_veggies);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView_list = new ArrayList<RecyclerView_List>();
        recyclerView_list.add(new RecyclerView_List(R.drawable.bellpepper, "Red Bell Pepper", "₱150/kg",0));
        recyclerView_list.add(new RecyclerView_List(R.drawable.ginger, "Ginger", "₱120/kg",1));
        recyclerView_list.add(new RecyclerView_List(R.drawable.lettuce, "Fresh Lettuce", "₱110/kg",2));
        recyclerView_list.add(new RecyclerView_List(R.drawable.squash, "Butternut Squash", "₱135/kg",3));
        recyclerView_list.add(new RecyclerView_List(R.drawable.carrots, "Organic Carrots", "₱110/kg",4));
        recyclerView_list.add(new RecyclerView_List(R.drawable.brocolli, "Fresh Broccoli", "₱120/kg",5));

        RecyclerView_Adapter recyclerView_adapter = new RecyclerView_Adapter(recyclerView_list, this);
        recyclerView.setAdapter(recyclerView_adapter);

        bckbtn_items_veggies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity and go back to the previous screen
                finish();
            }
        });
    }
}