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
import com.sharezzorama.example.shop.ShopApplication;
import com.sharezzorama.example.shop.data.cart.CartKeeper;
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
        mPresenter = ShopApplication.getInstance().getCartPresenter();
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
    public void itemAdded(int pos) {

    }

    @Override
    public void itemUpdated(int pos) {
        mCartAdapter.notifyItemChanged(pos);
    }

    @Override
    public void itemRemoved(int pos) {
        mCartAdapter.notifyItemRemoved(pos);
        updateEmptyView();
    }

    @Override
    public void showAll(CartLinkedHashMap data) {
        mCartAdapter.setData(data);
        mCartAdapter.notifyDataSetChanged();
        updateEmptyView();
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

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);
        mPresenter.getAll();
    }

    @Override
    protected void onStop() {
        mPresenter.detachView(this);
        super.onStop();
    }
}
