package com.example.pricechecker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CardView_Meat extends AppCompatActivity {

    ImageButton bckbtn_items_meat;
    ArrayList<RecyclerView_List> recyclerView_list;
    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_card_view_meat);

        bckbtn_items_meat = findViewById(R.id.back_btn_meat);
        recyclerView = findViewById(R.id.recyclerView_card_meat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ConstraintLayout constraintLayout = findViewById(R.id.cardView_meat);


        recyclerView_list = new ArrayList<RecyclerView_List>();
        recyclerView_list.add(new RecyclerView_List(R.drawable.bellpepper, "Red Bell Pepper", "₱150/kg",0));
        recyclerView_list.add(new RecyclerView_List(R.drawable.ginger, "Ginger", "₱120/kg",1));
        recyclerView_list.add(new RecyclerView_List(R.drawable.lettuce, "Fresh Lettuce", "₱110/kg",2));
        recyclerView_list.add(new RecyclerView_List(R.drawable.squash, "Butternut Squash", "₱135/kg",3));
        recyclerView_list.add(new RecyclerView_List(R.drawable.carrots, "Organic Carrots", "₱110/kg",4));
        recyclerView_list.add(new RecyclerView_List(R.drawable.brocolli, "Fresh Broccoli", "₱120/kg",5));
        recyclerView_list.add(new RecyclerView_List(R.drawable.beef, "Beef Meat", "₱320/kg", 6));
        recyclerView_list.add(new RecyclerView_List(R.drawable.pork, "Pork Meat", "₱280/kg", 7));
        recyclerView_list.add(new RecyclerView_List(R.drawable.lamb, "Lamb Meat", "₱320/kg", 8));
        recyclerView_list.add(new RecyclerView_List(R.drawable.rabbit, "Rabbit Meat", "₱200/kg", 9));
        recyclerView_list.add(new RecyclerView_List(R.drawable.drumsticks, "Drumsticks", "₱210/kg", 10));
        recyclerView_list.add(new RecyclerView_List(R.drawable.wings, "Chicken Wings", "₱200/kg", 11));

        int startIndex = 6;
        if (startIndex >= 0 && startIndex < recyclerView_list.size()) {
            List<RecyclerView_List> subList = recyclerView_list.subList(startIndex, recyclerView_list.size());
            RecyclerView_Adapter recyclerView_adapter = new RecyclerView_Adapter(new ArrayList<>(subList), this);
            recyclerView.setAdapter(recyclerView_adapter);

            bckbtn_items_meat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Finish the activity and go back to the previous screen
                    finish();
                }
            });
        }
    }
}