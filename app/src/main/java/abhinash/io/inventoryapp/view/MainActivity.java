package abhinash.io.inventoryapp.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import abhinash.io.inventoryapp.R;
import abhinash.io.inventoryapp.controller.InventoryListAdapter;
import abhinash.io.inventoryapp.domain.Inventory;

public class MainActivity extends AppCompatActivity implements InventoryListAdapter.InventoryListListener {

    /**
     * Recycler view.
     */
    private RecyclerView mRecyclerView;
    /**
     * Placeholder view.
     */
    private View mPlaceholderView;
    /**
     * The list of inventory.
     */
    private ArrayList<Inventory> mInventories = new ArrayList<>();
    /**
     * Ineventory list adapter.
     */
    private InventoryListAdapter mInventoryListAdapter;
    /**
     * First item in view.
     */
    private int firstItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_main);
        mPlaceholderView = findViewById(R.id.view_placeholder);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpList();
    }

    /**
     * Save the current scrolled position.
     */
    private void saveFirstPosition() {
        if (null != mRecyclerView ) {
            firstItem = ((LinearLayoutManager)mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
    }

    /**
     * Set up the list with persisted scroll position.
     */
    private void setUpList() {
        if (null == mInventoryListAdapter) {
            mInventoryListAdapter = new InventoryListAdapter(this, mInventories);
        }
        mRecyclerView.setAdapter(mInventoryListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        mRecyclerView.getLayoutManager().scrollToPosition(firstItem);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveFirstPosition();
    }

    @Override
    public void onInventoryClicked(@NonNull Inventory inventory) {

    }

    @Override
    public void onInventorySold(@NonNull Inventory inventory) {

    }
}
