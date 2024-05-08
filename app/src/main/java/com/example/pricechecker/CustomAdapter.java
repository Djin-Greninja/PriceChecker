package com.example.pricechecker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context mContext;
    private int[] mBackgroundResources;
    private int mSelectedIndex;

    public CustomAdapter(Context context, int[] backgroundResources, int selectedIndex) {
        mContext = context;
        mBackgroundResources = backgroundResources;
        mSelectedIndex = selectedIndex;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_price_check, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int backgroundResource = mBackgroundResources[position];

        // Set the background drawable for the item
        holder.itemView.setBackgroundResource(backgroundResource);
    }

    @Override
    public int getItemCount() {
        return mBackgroundResources.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
