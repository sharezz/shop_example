package com.sharezzorama.example.shop.data.catalog.category;

import java.util.List;

/**
 * Created by sharezzorama on 11/21/16.
 */

public interface CategoryDataSource {

    interface LoadCategoriesCallback {

        void onItemsLoaded(List<Category> categories);

        void onDataNotAvailable();
    }

    interface SaveCategoriesCallback {

        void onCategoriesSaved(List<Category> categories);
    }

    void getCategories(LoadCategoriesCallback callback);

    void saveCategories(List<Category> categories, SaveCategoriesCallback callback);
}
