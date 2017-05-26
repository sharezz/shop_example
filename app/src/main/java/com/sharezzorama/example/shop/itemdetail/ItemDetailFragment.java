package com.sharezzorama.example.shop.itemdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sharezzorama.example.shop.BaseFragment;
import com.sharezzorama.example.shop.R;
import com.sharezzorama.example.shop.ShopApplication;
import com.sharezzorama.example.shop.cart.CartContract;
import com.sharezzorama.example.shop.cart.CartLinkedHashMap;
import com.sharezzorama.example.shop.cart.CartPresenter;
import com.sharezzorama.example.shop.category.CategoryContract;
import com.sharezzorama.example.shop.data.catalog.item.Item;

import java.util.Map;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class ItemDetailFragment extends BaseFragment {
    private static final String EXTRA_KEY_ITEM = "ItemDetailFragment.EXTRA_KEY_ITEM";
    private Item mItem;

    private TextView mNameView;
    private TextView mDescriptionView;
    private TextView mPriceView;
    private Button mCartButton;
    private CartContract.Presenter mPresenter;
    private CartContract.View mCartView = new CartContract.CartView() {
        @Override
        public void itemAdded(int pos) {
            updateCartButton(true);
        }

        @Override
        public void itemRemoved(int pos) {
            updateCartButton(false);
        }

        @Override
        public void showAll(CartLinkedHashMap data) {
            updateCartButton(data.containsKey(mItem));
        }
    };

    public static ItemDetailFragment newInstance(Item item) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_KEY_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItem = (Item) getArguments().getSerializable(EXTRA_KEY_ITEM);
        mPresenter = ShopApplication.getInstance().getCartPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_item_detail, container, false);
        mNameView = (TextView) root.findViewById(R.id.itemName);
        mDescriptionView = (TextView) root.findViewById(R.id.itemDescription);
        mPriceView = (TextView) root.findViewById(R.id.itemPrice);
        mCartButton = (Button) root.findViewById(R.id.cartButton);

        mNameView.setText(mItem.getName());
        mDescriptionView.setText(mItem.getDescription());
        mPriceView.setText(getString(R.string.currency_usd, mItem.getPrice()));
        mCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addOrRemove(mItem);
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.attachView(mCartView);
        mPresenter.getAll();
    }

    @Override
    public void onPause() {
        mPresenter.detachView(mCartView);
        super.onPause();
    }

    private void updateCartButton(boolean itemInCart) {
        mCartButton.setText(itemInCart ? R.string.remove_from_cart_button_text : R.string.add_to_cart_button_text);
    }
}
