package com.sharezzorama.example.shop.data.catalog.category.external;

import android.util.Log;

import com.sharezzorama.example.shop.data.catalog.category.Category;
import com.sharezzorama.example.shop.data.catalog.category.CategoryDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CategoryFakeExternalDataSource implements CategoryDataSource{

    private static CategoryFakeExternalDataSource INSTANCE;

    private CategoryFakeExternalDataSource() {
    }

    public static CategoryFakeExternalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryFakeExternalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getCategories(LoadCategoriesCallback callback) {
        Log.d(this.getClass().getSimpleName(), "getCategories()");
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(null, 0L, "Телевизоры, аудио, видео"));
        categories.add(new Category(null, 1L, "Ноутбуки, компьютеры"));
        categories.add(new Category(null, 2L, "Телефоны, планшеты"));
        callback.onItemsLoaded(categories);
    }

    @Override
    public void saveCategories(List<Category> categories, SaveCategoriesCallback callback) {
        // Not required for the external data source.
    }
}
