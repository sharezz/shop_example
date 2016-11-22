package com.sharezzorama.example.shop.data.catalog.item;

import com.sharezzorama.example.shop.data.BaseEntity;

/**
 * Created by sharezzorama on 11/21/16.
 */

public class Item extends BaseEntity {
    private String name;
    private String description;
    private Double price;
    private Long categoryId;

    public Item(Long id, Long categoryId, String name, String description, Double price) {
        super(id);
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
