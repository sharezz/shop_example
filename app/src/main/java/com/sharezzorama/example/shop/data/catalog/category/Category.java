package com.sharezzorama.example.shop.data.catalog.category;

import com.sharezzorama.example.shop.data.BaseEntity;

/**
 * Created by sharezzorama on 11/21/16.
 */

public class Category extends BaseEntity {
    private String name;
    private Long categoryId;

    public Category(Long id, Long categoryId, String name) {
        super(id);
        this.categoryId = categoryId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
