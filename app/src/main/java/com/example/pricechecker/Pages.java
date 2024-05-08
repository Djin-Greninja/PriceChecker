package com.example.pricechecker;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.util.ArrayList;
import java.util.List;

public class Pages extends AppCompatActivity {
    private SharedViewModel viewModel;
    ImageButton addToCart, priceCheck;
    private CartAdapter adapter;
    private List<CartItem> cartItems;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);
        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        int pageId = getIntent().getIntExtra("id", 0);
        // Set background dynamically based on page ID
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ConstraintLayout constraintLayout = findViewById(R.id.item_details);
        constraintLayout.setBackgroundResource(getBackgroundResource(pageId));
        TextView textView = findViewById(R.id.price);
        addToCart = findViewById(R.id.addtocart);
        priceCheck = findViewById(R.id.save_money);
        adapter = new CartAdapter( this,new ArrayList<>());



        addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Determine the page's unique identifier (e.g., page number)
                    List<CartItem> cartItems = new ArrayList<>();
                    switch (pageId) {
                        case 0:
                            cartItems.add(new CartItem(R.drawable.bellpepper, "Red Bell Pepper", 150.00));
                            break;
                        case 1:
                            cartItems.add(new CartItem(R.drawable.ginger, "Ginger", 120.00));
                            break;
                        case 2:
                            cartItems.add(new CartItem(R.drawable.lettuce, "Fresh Lettuce", 110.00));
                            break;
                        case 3:
                            cartItems.add(new CartItem(R.drawable.squash, "Butternut Squash", 135.00));
                            break;
                        case 4:
                            cartItems.add(new CartItem(R.drawable.carrots, "Organic Carrots", 110.00));
                            break;
                        case 5:
                            cartItems.add(new CartItem(R.drawable.brocolli, "Fresh Broccoli", 120.00));
                            break;
                        case 6:
                            cartItems.add(new CartItem(R.drawable.beef, "Beef Meat", 320.00));
                            break;
                        case 7:
                            cartItems.add(new CartItem(R.drawable.pork, "Pork Meat", 280.00));
                            break;
                        case 8:
                            cartItems.add(new CartItem(R.drawable.lamb, "Lamb Meat", 320.00));
                            break;
                        case 9:
                            cartItems.add(new CartItem(R.drawable.rabbit, "Rabbit Meat", 200.00));
                            break;
                        case 10:
                            cartItems.add(new CartItem(R.drawable.drumsticks, "Drumsticks", 210.00));
                            break;
                        case 11:
                            cartItems.add(new CartItem(R.drawable.wings, "Chicken Wings", 200.00));
                            break;
                        default:
                            break;
                    }
                    if (!cartItems.isEmpty()) {
                        viewModel.addToCart(cartItems);
                        Toast.makeText(Pages.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Pages.this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        switch (pageId) {
            case 0:
                textView.setText("150/kg");
                break;
            case 1:
                textView.setText("₱120/kg");
                break;
            case 2:
                textView.setText("₱110/kg");
                break;
            case 3:
                textView.setText("₱135/kg");
                break;
            case 4:
                textView.setText("₱110/kg");
                break;
            case 5:
                textView.setText("₱120/kg");
                break;
            case 6:
                textView.setText("₱320/kg");
                break;
            case 7:
                textView.setText("₱280/kg");
                break;
            case 8:
                textView.setText("₱320/kg");
                break;
            case 9:
                textView.setText("₱200/kg");
                break;
            case 10:
                textView.setText("₱210/kg");
                break;
            case 11:
                textView.setText("₱200/kg");
                break;
            default:
                textView.setText("Default Content");
                break;
        }
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
    public void onPageClicked(View view){
        int pageId = getIntent().getIntExtra("id", 0);

        switch (pageId) {
            case 0:
                CartItem item = new CartItem(R.drawable.bellpepper, "Description of Page 1", 10.0);
                viewModel.addToCart((List<CartItem>) item);
                break;

    }
    }
}
