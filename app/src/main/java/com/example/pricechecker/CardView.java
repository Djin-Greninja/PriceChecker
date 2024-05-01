package com.example.pricechecker;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardView extends AppCompatActivity {
    ImageButton bckbtn_items;
    ArrayList<RecyclerView_List> recyclerView_list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cardview);

        bckbtn_items = findViewById(R.id.back_btn_itemList);
        recyclerView = findViewById(R.id.recyclerView_card);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView_list = new ArrayList<RecyclerView_List>();
        recyclerView_list.add(new RecyclerView_List(R.drawable.bellpepper, "Red Bell Pepper", "₱150/kg"));
        recyclerView_list.add(new RecyclerView_List(R.drawable.ginger, "Ginger", "₱120/kg"));
        recyclerView_list.add(new RecyclerView_List(R.drawable.lettuce, "Fresh Lettuce", "₱110/kg"));
        recyclerView_list.add(new RecyclerView_List(R.drawable.squash, "Butternut Squash", "₱135/kg"));
        recyclerView_list.add(new RecyclerView_List(R.drawable.carrots, "Organic Carrots", "₱110/kg"));
        recyclerView_list.add(new RecyclerView_List(R.drawable.brocolli, "Fresh Broccoli", "₱120/kg"));
        recyclerView_list.add(new RecyclerView_List(R.drawable.beef, "Beef Meat", "₱320/kg"));
        recyclerView_list.add(new RecyclerView_List(R.drawable.pork, "Pork Meat", "₱280/kg"));
        recyclerView_list.add(new RecyclerView_List(R.drawable.lamb, "Lamb Meat", "₱320/kg"));
        recyclerView_list.add(new RecyclerView_List(R.drawable.rabbit, "Rabbit Meat", "₱200/kg"));
        recyclerView_list.add(new RecyclerView_List(R.drawable.drumsticks, "Drumsticks", "₱210/kg"));
        recyclerView_list.add(new RecyclerView_List(R.drawable.wings, "Chicken Wings", "₱200/kg"));

        RecyclerView_Adapter recyclerView_adapter = new RecyclerView_Adapter(recyclerView_list, this);
        recyclerView.setAdapter(recyclerView_adapter);

        bckbtn_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity and go back to the previous screen
                finish();
            }
        });
    }
}