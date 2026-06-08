package com.inventory.queries;

public class Product {
    private int productID;
    private String name;
    private String category;
    private int stock;

    public Product(int productID, String name, String category, int stock) {
        this.productID = productID;
        this.name = name;
        this.category = category;
        this.stock = stock;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }
}
