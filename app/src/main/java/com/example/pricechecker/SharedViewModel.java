package com.example.pricechecker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<List<CartItem>> cartItems = new MutableLiveData<>();

    public LiveData<List<CartItem>> getCartItems() {
        if (cartItems.getValue() == null) {
            cartItems.setValue(new ArrayList<>());
        }
        return cartItems;
    }

    public void addToCart(CartItem item) {
        List<CartItem> currentCartItems = cartItems.getValue();
        currentCartItems.add(item);
        cartItems.setValue(currentCartItems);
    }
}