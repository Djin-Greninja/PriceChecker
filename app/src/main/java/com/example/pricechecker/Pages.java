package com.example.pricechecker;

import static androidx.core.content.ContentProviderCompat.requireContext;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<CartItem> cartItems = new ArrayList<>();
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
        adapter = new CartAdapter(this, cartItems);

        // Assuming you have access to the ViewModel instance in your fragment


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Determine the page's unique identifier (e.g., page number)

                // Create a CartItem object based on the page's unique identifier
                CartItem item;
                switch (pageId) {
                    case 0:
                        item = new CartItem(R.drawable.bellpepper, "Red Bell Pepper", 150.00);
                        break;
                    case 1:
                        item = new CartItem(R.drawable.ginger, "Ginger", 120.00);
                        break;
                    case 2:
                        item = new CartItem(R.drawable.lettuce, "Fresh Lettuce", 110.00);
                        break;
                    case 3:
                        item = new CartItem(R.drawable.squash, "Butternut Squash", 135.00);
                        break;
                    case 4:
                        item = new CartItem(R.drawable.carrots, "Organic Carrots", 110.00);
                        break;
                    case 5:
                        item = new CartItem(R.drawable.brocolli, "Fresh Brocolli", 120.00);
                        break;
                    case 6:
                        item = new CartItem(R.drawable.beef, "Beef Meat", 320.00);
                        break;
                    case 7:
                        item = new CartItem(R.drawable.pork, "Pork Meat", 280.00);
                        break;
                    case 8:
                        item = new CartItem(R.drawable.lamb, "Lamb Meat", 320.00);
                        break;
                    case 9:
                        item = new CartItem(R.drawable.rabbit, "Rabbit Meat", 200.00);
                        break;
                    case 10:
                        item = new CartItem(R.drawable.drumsticks, "Drumsticks", 210.00);
                        break;
                    case 11:
                        item = new CartItem(R.drawable.wings, "Chicken Wings", 200.00);
                        break;
                    // Add more cases for other pages as needed
                    default:
                        // Default case if the page identifier doesn't match any known cases
                        item = null; // or handle default behavior
                        break;
                }
                // If item is not null, add it to the cart
                if (item != null) {
                    cartItems.add(item); // Add item to cartItems list
                    viewModel.addToCart(item);
                    Toast.makeText(Pages.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                    adapter.updateCartItems(cartItems);
                } else {
                    // Handle the case where item is null (optional)
                    Toast.makeText(Pages.this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Determine the current page or case

        // Update TextView content based on current page or case
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
            // Add more cases as needed
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
            // Add more cases for additional pages if needed
            default:
                return R.drawable.notfound;// Default background if no matching page ID
        }
    }




}