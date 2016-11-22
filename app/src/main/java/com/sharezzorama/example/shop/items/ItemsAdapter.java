package com.sharezzorama.example.shop.items;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sharezzorama.example.shop.R;
import com.sharezzorama.example.shop.data.catalog.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private List<Item> mItems = new ArrayList<>();
    private ItemClickListener mItemClickListener;

    public ItemsAdapter(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Item item = mItems.get(position);
        holder.mItemNameView.setText(item.getName());
        holder.mItemPriceView.setText(String.format("%.2f", item.getPrice()));
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(item);
                }
            });
        }
    }

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mItemNameView;
        private TextView mItemPriceView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemNameView = (TextView) itemView.findViewById(R.id.itemName);
            mItemPriceView = (TextView) itemView.findViewById(R.id.itemPrice);
        }
    }

    public interface ItemClickListener {

        void onItemClick(Item item);
    }
}
