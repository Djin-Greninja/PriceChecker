package com.example.pricechecker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

// SharedViewModel.java
public class SharedViewModel extends ViewModel {
    private MutableLiveData<List<CartItem>> cartItems = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<CartItem>> getCartItems() {
        return cartItems;
    }

    public void addToCart(int imageResId, String name, double price) {
        List<CartItem> currentCartItems = cartItems.getValue();
        if (currentCartItems == null) {
            currentCartItems = new ArrayList<>();
        }
        CartItem newItem = new CartItem(imageResId, name, price);
        currentCartItems.add(newItem);
        cartItems.postValue(currentCartItems);
    }

    public void addToCart(List<CartItem> cartItems) {


    }
}
