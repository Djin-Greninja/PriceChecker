package com.example.pricechecker;

public class RecyclerView_List {
    private Integer image;
    private String text;
    private String price;

    public RecyclerView_List(Integer image, String text, String price) {
        this.image = image;
        this.text = text;
        this.price = price;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
