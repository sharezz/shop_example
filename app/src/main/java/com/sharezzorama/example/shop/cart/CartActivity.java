package com.sharezzorama.example.shop.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sharezzorama.example.shop.BaseActivity;
import com.sharezzorama.example.shop.R;
import com.sharezzorama.example.shop.data.catalog.item.Item;

import java.util.Map;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CartActivity extends BaseActivity implements CartAdapter.CartItemClickListener, CartContract.View {
    private RecyclerView mCartRecyclerView;
    private CartAdapter mCartAdapter;
    private CartContract.Presenter mPresenter;
    private TextView mEmptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCartRecyclerView = (RecyclerView) findViewById(R.id.cartRecyclerView);
        mEmptyView = (TextView) findViewById(R.id.emptyView);
        mCartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCartAdapter = new CartAdapter(this);
        mCartRecyclerView.setAdapter(mCartAdapter);
        new CartPresenter(getCartKeeper(), this);
        mPresenter.start();
    }

    @Override
    public void onDecreaseButtonClick(Item item) {
        mPresenter.decreaseCount(item);
    }

    @Override
    public void onIncreaseButtonClick(Item item) {
        mPresenter.increaseCount(item);
    }

    @Override
    public void onItemDeleteClick(Item item) {
        mPresenter.remove(item);
    }

    @Override
    public void itemAdded(Item item) {

    }

    @Override
    public void itemUpdated(Item item) {
        mCartAdapter.updateItem(item);
    }

    @Override
    public void itemRemoved(Item item) {
        mCartAdapter.removeItem(item);
        updateEmptyView();
    }

    @Override
    public void showAll(Map<Item, Integer> data) {
        mCartAdapter.setData(data);
        mCartAdapter.notifyDataSetChanged();
        updateEmptyView();
    }

    @Override
    public void updateAll(Map<Item, Integer> data) {
        mCartAdapter.setData(data);
    }

    @Override
    public void setPresenter(CartContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateEmptyView() {
        mEmptyView.setVisibility(mCartAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }
}
