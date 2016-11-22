package com.sharezzorama.example.shop.category;

import com.sharezzorama.example.shop.data.catalog.category.Category;
import com.sharezzorama.example.shop.data.catalog.category.CategoryDataSource;
import com.sharezzorama.example.shop.data.catalog.category.CategoryRepository;

import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CategoryPresenter implements CategoryContract.Presenter {
    private CategoryContract.View mCategoryView;
    private CategoryRepository mRepository;

    public CategoryPresenter(CategoryContract.View categoryView, CategoryRepository repository) {
        mCategoryView = categoryView;
        mRepository = repository;
        mCategoryView.setPresenter(this);
    }

    @Override
    public void loadCategories() {
        mRepository.getCategories(new CategoryDataSource.LoadCategoriesCallback() {
            @Override
            public void onItemsLoaded(List<Category> categories) {
                mCategoryView.showCategories(categories);
            }

            @Override
            public void onDataNotAvailable() {
                mCategoryView.showNoCategories();
            }
        });
    }

    @Override
    public void start() {
        loadCategories();
    }
}
