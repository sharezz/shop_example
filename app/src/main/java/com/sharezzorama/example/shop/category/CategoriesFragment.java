package com.sharezzorama.example.shop.category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sharezzorama.example.shop.BaseFragment;
import com.sharezzorama.example.shop.MainActivity;
import com.sharezzorama.example.shop.R;
import com.sharezzorama.example.shop.ShopApplication;
import com.sharezzorama.example.shop.data.catalog.category.Category;
import com.sharezzorama.example.shop.data.catalog.category.CategoryRepository;
import com.sharezzorama.example.shop.data.catalog.category.external.CategoryFakeExternalDataSource;
import com.sharezzorama.example.shop.data.catalog.category.local.CategoryLocalDataSource;
import com.sharezzorama.example.shop.items.ItemsFragment;

import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CategoriesFragment extends BaseFragment implements CategoryContract.View, CategoryAdapter.CategoryClickListener {
    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryAdapter;
    private CategoryContract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = ShopApplication.getInstance().getCategoryPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.categoryRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCategoryAdapter = new CategoryAdapter(this);
        mRecyclerView.setAdapter(mCategoryAdapter);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showCategories(List<Category> categories) {
        mCategoryAdapter.setCategories(categories);
    }

    @Override
    public void showNoCategories() {
        Toast.makeText(getContext(), R.string.no_categories, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCategoryClick(Category category) {
        ((MainActivity) getActivity()).switchContent(ItemsFragment.newInstance(category), false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.attachView(this);
        mPresenter.loadCategories();
    }

    @Override
    public void onStop() {
        mPresenter.detachView(this);
        super.onStop();
    }
}
