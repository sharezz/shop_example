package com.sharezzorama.example.shop.cart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sharezzorama.example.shop.R;
import com.sharezzorama.example.shop.data.catalog.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Map<Item, Integer> mData;
    private List<Item> mItems;
    private CartItemClickListener mListener;

    public CartAdapter(CartItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        final Item item = mItems.get(position);
        final Integer count = mData.get(item);
        holder.mItemNameView.setText(item.getName());
        holder.mCountView.setText(String.valueOf(count));
        holder.mTotalPriceView.setText(String.format("%.2f", item.getPrice() * count));

        holder.mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDecreaseButtonClick(item);
            }
        });
        holder.mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onIncreaseButtonClick(item);
            }
        });
        holder.mDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemDeleteClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateItem(Item item) {
        notifyItemChanged(mItems.indexOf(item));
    }

    public void removeItem(Item item) {
        notifyItemRemoved(mItems.indexOf(item));
    }

    public void setData(Map<Item, Integer> data) {
        mItems = new ArrayList<>(data.keySet());
        mData = new HashMap<>(data);
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        private TextView mItemNameView;
        private TextView mTotalPriceView;
        private TextView mCountView;
        private Button mMinusButton;
        private Button mPlusButton;
        private View mDeleteView;

        public CartViewHolder(View itemView) {
            super(itemView);
            mItemNameView = (TextView) itemView.findViewById(R.id.itemName);
            mTotalPriceView = (TextView) itemView.findViewById(R.id.totalPrice);
            mMinusButton = (Button) itemView.findViewById(R.id.minusButton);
            mPlusButton = (Button) itemView.findViewById(R.id.plusButton);
            mCountView = (TextView) itemView.findViewById(R.id.count);
            mDeleteView = itemView.findViewById(R.id.deleteButton);
        }
    }

    interface CartItemClickListener {

        void onDecreaseButtonClick(Item item);

        void onIncreaseButtonClick(Item item);

        void onItemDeleteClick(Item item);

    }
}
