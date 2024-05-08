package com.example.pricechecker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cardview, container, false);


            // Find RecyclerView and setup layout manager
            recyclerView = view.findViewById(R.id.recyclerView_card);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

            // Initialize and populate the list of items
            ArrayList<RecyclerView_List> recyclerViewList = new ArrayList<>();
            recyclerViewList.add(new RecyclerView_List(R.drawable.bellpepper, "Red Bell Pepper", "₱150/kg", 0));
            recyclerViewList.add(new RecyclerView_List(R.drawable.ginger, "Ginger", "₱120/kg", 1));
            recyclerViewList.add(new RecyclerView_List(R.drawable.lettuce, "Fresh Lettuce", "₱110/kg", 2));
            recyclerViewList.add(new RecyclerView_List(R.drawable.squash, "Butternut Squash", "₱135/kg", 3));
            recyclerViewList.add(new RecyclerView_List(R.drawable.carrots, "Organic Carrots", "₱110/kg", 4));
            recyclerViewList.add(new RecyclerView_List(R.drawable.brocolli, "Fresh Broccoli", "₱120/kg", 5));
            recyclerViewList.add(new RecyclerView_List(R.drawable.beef, "Beef Meat", "₱320/kg", 6));
            recyclerViewList.add(new RecyclerView_List(R.drawable.pork, "Pork Meat", "₱280/kg", 7));
            recyclerViewList.add(new RecyclerView_List(R.drawable.lamb, "Lamb Meat", "₱320/kg", 8));
            recyclerViewList.add(new RecyclerView_List(R.drawable.rabbit, "Rabbit Meat", "₱200/kg", 9));
            recyclerViewList.add(new RecyclerView_List(R.drawable.drumsticks, "Drumsticks", "₱210/kg", 10));
            recyclerViewList.add(new RecyclerView_List(R.drawable.wings, "Chicken Wings", "₱200/kg", 11));

            // Set up RecyclerView adapter
            RecyclerView_Adapter recyclerViewAdapter = new RecyclerView_Adapter(recyclerViewList, requireContext());
            recyclerView.setAdapter(recyclerViewAdapter);

            return view;
        }
    }