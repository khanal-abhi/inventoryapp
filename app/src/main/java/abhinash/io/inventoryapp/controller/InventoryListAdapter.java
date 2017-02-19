package abhinash.io.inventoryapp.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import abhinash.io.inventoryapp.R;
import abhinash.io.inventoryapp.domain.Inventory;

/**
 * Created by khanal on 2/18/17.
 * This is the adapter for the inventory list.
 */

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.ViewHolder> {

    /**
     * Inventory list listener.
     */
    private InventoryListListener mInventoryListListener;
    /**
     * List of inventory.
     */
    private ArrayList<Inventory> mInventories;

    /**
     * Public constructor.
     * @param mInventoryListListener -.
     * @param mInventories -.
     */
    public InventoryListAdapter(@NonNull final InventoryListListener mInventoryListListener,
                                @NonNull final ArrayList<Inventory> mInventories) {
        this.mInventoryListListener = mInventoryListListener;
        this.mInventories = mInventories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_inventory_list, parent, false);
        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mInventories.get(position));
    }

    @Override
    public int getItemCount() {
        if (mInventories == null) {
            mInventories = new ArrayList<>();
        }
        return mInventories.size();
    }

    /**
     * View holder to hold the inventory.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Bound inventory.
         */
        private Inventory mInventory;
        /**
         * Item name.
         */
        private TextView mName;
        /**
         * Item Price.
         */
        private TextView mPrice;
        /**
         * Item Quantity.
         */
        private TextView mQuantity;
        /**
         * Sold Button.
         */
        private Button mSold;

        /**
         * Initilize the views.
         * @param itemView -.
         */
        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.txt_name);
            mPrice = (TextView) itemView.findViewById(R.id.txt_price);
            mQuantity = (TextView) itemView.findViewById(R.id.txt_quantity);
            mSold = (Button) itemView.findViewById(R.id.btn_sale);
        }

        /**
         * Bind the inventory to the row.
         * @param inventory -.
         */
        private void bind(@NonNull final Inventory inventory) {
            mInventory = inventory;
            if (null != inventory.getName()) {
                mName.setText(inventory.getName());
            } else {
                mName.setText("");
            }

            if (null != inventory.getPrice()) {
                double price = inventory.getPrice() / 100.00;
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
                mPrice.setText(format.format(price));
            } else {
                mPrice.setText("");
            }

            if (null != inventory.getQuantity()) {
                mQuantity.setText(inventory.getQuantity());
                if (inventory.getQuantity() > 0) {
                    mSold.setEnabled(true);
                } else {
                    mSold.setEnabled(false);
                }
            } else {
                mQuantity.setText("");
                mSold.setEnabled(false);
            }
        }
    }

    /**
     * The list's listener.
     */
    public interface InventoryListListener {

        /**
         * Fired when the inventory is selected.
         * @param inventory -.
         */
        void onInventoryClicked(@NonNull final Inventory inventory);

        /**
         * Fired when an inventory is sold.
         * @param inventory -.
         */
        void onInventorySold(@NonNull final Inventory inventory);
    }
}
