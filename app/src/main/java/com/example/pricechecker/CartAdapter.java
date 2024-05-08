package com.example.pricechecker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartItems;
    private Context context;

    public CartAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    public void updateCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_list, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.nameTextView.setText(cartItem.getName());
        holder.priceTextView.setText(String.format("%.2f", cartItem.getPrice()));
        holder.imageView.setImageResource(cartItem.getImage());
        holder.constraintLayout.setBackgroundResource(getBackgroundResource(cartItem.getPageId()));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
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
            default:
                return R.drawable.notfound;
        }
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        public View constraintLayout;
        TextView nameTextView;
        TextView priceTextView;
        ImageView imageView;

        CartViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_name_text_view);
            priceTextView = itemView.findViewById(R.id.item_price_text_view);
            imageView = itemView.findViewById(R.id.item_image_view);
        }
    }
}