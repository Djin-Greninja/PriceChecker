package com.example.pricechecker;

public class CartItem {
    private int image;
    private String name;
    private double price;

    public CartItem(int image, String name, double price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}


