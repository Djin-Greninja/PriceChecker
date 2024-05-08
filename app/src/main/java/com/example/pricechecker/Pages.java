package com.example.pricechecker;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class Pages extends AppCompatActivity {
    private SharedViewModel viewModel;
    private ImageButton addToCart, priceCheck;
    private CartAdapter adapter;
    private List<CartItem> cartItems;
    private ConstraintLayout constraintLayout;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);
        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        int pageId = getIntent().getIntExtra("id", 0);
        int pageIds = getIntent().getIntExtra("id", 0);

        initViews();
        setupBackground_page(pageIds);
        setupBackground(pageId);
        setupPriceText(pageId);
        setupButtonListeners();
    }

    private void setupBackground_page(int pageIds) {
        switch (pageIds) {
            case 0:
                constraintLayout.setBackgroundResource(R.drawable.pc_bellpepper);
                break;
            case 1:
                constraintLayout.setBackgroundResource(R.drawable.pc_ginger);
                break;
            case 2:
                constraintLayout.setBackgroundResource(R.drawable.pc_lettuce);
                break;
            case 3:
                constraintLayout.setBackgroundResource(R.drawable.pc_squash);
                break;
            case 4:
                constraintLayout.setBackgroundResource(R.drawable.pc_carrots);
                break;
            case 5:
                constraintLayout.setBackgroundResource(R.drawable.pc_broccli);
                break;
            case 6:
                constraintLayout.setBackgroundResource(R.drawable.pc_beef);
                break;
            case 7:
                constraintLayout.setBackgroundResource(R.drawable.pc_pork);
                break;
            case 8:
                constraintLayout.setBackgroundResource(R.drawable.pc_lamb);
                break;
            case 9:
                constraintLayout.setBackgroundResource(R.drawable.pc_rabbit);
                break;
            case 10:
                constraintLayout.setBackgroundResource(R.drawable.pc_drumsticks);
                break;
            case 11:
                constraintLayout.setBackgroundResource(R.drawable.pc_wings);
                break;
            default:
                constraintLayout.setBackgroundResource(R.drawable.notfound);
                break;
        }
    }


    private void initViews() {
        constraintLayout = findViewById(R.id.item_details);
        textView = findViewById(R.id.price);
        addToCart = findViewById(R.id.addtocart);
        priceCheck = findViewById(R.id.save_money);
    }

    private void setupBackground(int pageId) {
        int backgroundResource = getBackgroundResource(pageId);
        if (constraintLayout!= null) {
            constraintLayout.setBackgroundResource(backgroundResource);
        }
    }

    private void setupPriceText(int pageId) {
        String priceText = getPriceText(pageId);
        if (textView!= null) {
            textView.setText(priceText);
        }
    }

    private void setupButtonListeners() {
        priceCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pageIds = getIntent().getIntExtra("id", 0);
                int backgroundResource = getBackgroundResources(pageIds);
                Intent intent = new Intent(Pages.this, PriceCheckActivity.class);
                boolean clickedPageIndex=true;
                intent.putExtra("clicked_page_index", clickedPageIndex); // Pass the clicked page index
                startActivity(intent);
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<CartItem> cartItems = new ArrayList<>();
                int pageId = getIntent().getIntExtra("id", 0);
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
                        cartItems.add(new CartItem(R.drawable.rabbit, "Rabbit Meat", 200.00));break;
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

    private int getBackgroundResources(int pageIds) {
        switch (pageIds) {
            case 0:
                return R.drawable.pc_bellpepper;
            case 1:
                return R.drawable.pc_ginger;
            case 2:
                return R.drawable.pc_lettuce;
            case 3:
                return R.drawable.pc_squash;
            case 4:
                return R.drawable.pc_carrots;
            case 5:
                return R.drawable.pc_broccli;
            case 6:
                return R.drawable.pc_beef;
            case 7:
                return R.drawable.pc_pork;
            case 8:
                return R.drawable.pc_lamb;
            case 9:
                return R.drawable.pc_rabbit;
            case 10:
                return R.drawable.pc_drumsticks;
            case 11:
                return R.drawable.pc_wings;
            default:
                return R.drawable.notfound;
        }
    }

    private String getPriceText(int pageId) {
        switch (pageId) {
            case 0:
                return "150/kg";
            case 1:
                return "120/kg";
            case 2:
                return "110/kg";
            case 3:
                return "135/kg";
            case 4:
                return "110/kg";
            case 5:
                return "120/kg";
            case 6:
                return "320/kg";
            case 7:
                return "280/kg";
            case 8:
                return "320/kg";
            case 9:
                return "200/kg";
            case 10:
                return "210/kg";
            case 11:
                return "200/kg";
            default:
                return "Default Content";
        }
    }
}