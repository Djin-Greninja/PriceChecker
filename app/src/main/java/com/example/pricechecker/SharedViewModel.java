package com.example.pricechecker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<List<CartItem>> cartItems = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<CartItem>> getCartItems() {
        return cartItems;
    }

    public void addToCart(CartItem item) {
        List<CartItem> currentCartItems = cartItems.getValue();
        currentCartItems.add(item);
        cartItems.postValue(currentCartItems);
    }
}