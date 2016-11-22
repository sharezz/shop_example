package com.sharezzorama.example.shop.items;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sharezzorama.example.shop.BaseFragment;
import com.sharezzorama.example.shop.MainActivity;
import com.sharezzorama.example.shop.R;
import com.sharezzorama.example.shop.data.catalog.category.Category;
import com.sharezzorama.example.shop.data.catalog.item.Item;
import com.sharezzorama.example.shop.data.catalog.item.ItemRepository;
import com.sharezzorama.example.shop.data.catalog.item.external.ItemFakeExternalDataSource;
import com.sharezzorama.example.shop.data.catalog.item.local.ItemLocalDataSource;
import com.sharezzorama.example.shop.itemdetail.ItemDetailFragment;

import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class ItemsFragment extends BaseFragment implements ItemContract.View,
        ItemsAdapter.ItemClickListener {
    private final static String EXTRA_KEY_CATEGORY = "ItemsFragment.EXTRA_KEY_CATEGORY";
    private ItemContract.Presenter mPresenter;
    private Category mCategory;
    private ItemsAdapter mItemsAdapter;

    private RecyclerView mRecyclerView;
    private TextView mCategoryNameView;

    public static ItemsFragment newInstance(Category category) {
        ItemsFragment fragment = new ItemsFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_KEY_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategory = (Category) getArguments().getSerializable(EXTRA_KEY_CATEGORY);
        new ItemsPresenter(ItemRepository.getInstance(ItemFakeExternalDataSource.getInstance(), ItemLocalDataSource.getInstance(getContext())),
                this,
                mCategory.getCategoryId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_items, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.itemRecyclerView);
        mCategoryNameView = (TextView) root.findViewById(R.id.categoryName);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mItemsAdapter = new ItemsAdapter(this);
        mRecyclerView.setAdapter(mItemsAdapter);
        mCategoryNameView.setText(mCategory.getName());
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.start();
    }

    @Override
    public void showItems(List<Item> items) {
        mItemsAdapter.setItems(items);
    }

    @Override
    public void showNoItems() {
        Toast.makeText(getContext(), R.string.no_items, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ItemContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClick(Item item) {
        ((MainActivity) getActivity()).switchContent(ItemDetailFragment.newInstance(item), false);
    }
}
