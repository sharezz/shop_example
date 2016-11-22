package com.sharezzorama.example.shop.data.catalog.category;

import android.util.Log;

import com.sharezzorama.example.shop.data.catalog.category.external.CategoryFakeExternalDataSource;
import com.sharezzorama.example.shop.data.catalog.category.local.CategoryLocalDataSource;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CategoryRepository implements CategoryDataSource {
    private static CategoryRepository INSTANCE;
    private CategoryLocalDataSource mLocalDataSource;
    private CategoryFakeExternalDataSource mExternalDataSource;

    private CategoryRepository(CategoryFakeExternalDataSource externalDataSource, CategoryLocalDataSource localDataSource) {
        mExternalDataSource = externalDataSource;
        mLocalDataSource = localDataSource;
    }

    public static CategoryRepository getInstance(CategoryFakeExternalDataSource externalDataSource, CategoryLocalDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CategoryRepository(externalDataSource, localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getCategories(final LoadCategoriesCallback callback) {
        mLocalDataSource.getCategories(new LoadCategoriesCallback() {
            @Override
            public void onItemsLoaded(List<Category> categories) {
                callback.onItemsLoaded(categories);
            }

            @Override
            public void onDataNotAvailable() {
                getCategoriesFromExternalDataSource(callback);
            }
        });
    }

    @Override
    public void saveCategories(List<Category> categories, SaveCategoriesCallback callback) {
        mLocalDataSource.saveCategories(categories, callback);
    }

    private void getCategoriesFromExternalDataSource(final LoadCategoriesCallback callback) {
        mExternalDataSource.getCategories(new LoadCategoriesCallback() {
            @Override
            public void onItemsLoaded(List<Category> categories) {
                saveCategories(categories, new SaveCategoriesCallback() {
                    @Override
                    public void onCategoriesSaved(List<Category> categories) {
                        Log.d(this.getClass().getSimpleName(), MessageFormat.format("Saved {0} categories", categories.size()));
                    }
                });
                callback.onItemsLoaded(categories);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

}
