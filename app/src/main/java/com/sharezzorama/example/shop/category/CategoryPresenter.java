package com.sharezzorama.example.shop.category;

import com.sharezzorama.example.shop.mvp.BasePresenter;
import com.sharezzorama.example.shop.data.catalog.category.Category;
import com.sharezzorama.example.shop.data.catalog.category.CategoryDataSource;
import com.sharezzorama.example.shop.data.catalog.category.CategoryRepository;

import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CategoryPresenter extends BasePresenter<CategoryContract.View> implements CategoryContract.Presenter {
    private CategoryRepository mRepository;

    public CategoryPresenter( CategoryRepository repository) {
        mRepository = repository;
    }

    @Override
    public void loadCategories() {
        mRepository.getCategories(new CategoryDataSource.LoadCategoriesCallback() {
            @Override
            public void onItemsLoaded(List<Category> categories) {
                getView().showCategories(categories);
            }

            @Override
            public void onDataNotAvailable() {
                getView().showNoCategories();
            }
        });
    }


    @Override
    protected Class<CategoryContract.View> getViewClass() {
        return CategoryContract.View.class;
    }
}
