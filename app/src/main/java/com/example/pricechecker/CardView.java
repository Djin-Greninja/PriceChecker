package com.example.pricechecker;

import android.os.Bundle;

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

    ArrayList<RecyclerView_List> recyclerView_list;
    RecyclerView recyclerView = findViewById(R.id.recyclerView_card);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView_list = new ArrayList<RecyclerView_List>();
        recyclerView_list.add(new RecyclerView_List(R.drawable.bellpepper, "Bell Pepper Red", "₱150/kg"));
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
    }
}