package abhinash.io.inventoryapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import abhinash.io.inventoryapp.domain.Inventory;

/**
 * Created by khanal on 2/18/17.
 * This is Data Access Object layer.
 * This will have built in methods for CRUD operation.
 * This will be singleton for the app level.
 */

public class InventoryDAO {

    /**
     * Static instance.
     */
    private static InventoryDAO instance;
    /**
     * Inventory database.
     */
    private InventorySqlHelper mInventoryDatabase;

    /**
     * Private constructor to preserve singleton.
     * @param context -.
     */
    private InventoryDAO(@NonNull final Context context) {
        mInventoryDatabase = new InventorySqlHelper(context);
    }

    /**
     * Singleton access to the instance.
     * @param context
     * @return
     */
    public static InventoryDAO getInstance(@NonNull final Context context) {
        if (null == instance) {
            instance = new InventoryDAO(context);
        }
        return instance;
    }

    /**
     * Starting the CRUD operations.
     */

    /**
     * CREATE.
     * @param inventory -.
     * @return -.
     */
    public long addInventory(@NonNull final Inventory inventory) {

        ContentValues contentValues = getContentValuesFromInventory(inventory);
        /**
         * Return the result of the inster. -1 means there was an error.
         */
        return mInventoryDatabase.getWritableDatabase().insert(InventoryContract.InventoryEntry.TABLE_NAME,
                null,
                contentValues);

    }

    private ContentValues getContentValuesFromInventory(@NonNull final Inventory inventory) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_NAME_NAME, inventory.getName());
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_NAME_QUANTITY, inventory.getQuantity());
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_NAME_PRICE, inventory.getPrice());
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_NAME_CURRENCY, inventory.getCurrency());
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_NAME_SUPPLIER, inventory.getSupplier());
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_NAME_EMAIL, inventory.getEmail());
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_NAME_IMAGE, inventory.getImage());
        return contentValues;
    }

    /**
     * Multiple inventories being added.
     * @param inventories -.
     * @return -.
     */
    public List<Long> addInventories(final List<Inventory> inventories) {
        ArrayList<Long> ids = new ArrayList<>();
        for (Inventory inventory : inventories) {
            ids.add(addInventory(inventory));
        }
        return ids;
    }

    /**
     * READ
     * @param id Item id.
     * @return -.
     */
    public Inventory getInventory(final long id) {
        Inventory inventory = null;
        Cursor cursor = mInventoryDatabase.getReadableDatabase().query(
                InventoryContract.InventoryEntry.TABLE_NAME,
                null,
                InventoryContract.InventoryEntry._ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);

        if (null != cursor) {
            if (cursor.moveToFirst()) {
                inventory = parseInventoryFrom(cursor);
            }
            cursor.close();
        }

        return inventory;
    }

    /**
     * Private method to parse the inventory from the cursor.
     * @param cursor -.
     * @return -.
     */
    private Inventory parseInventoryFrom(@NonNull final Cursor cursor) {
        Inventory inventory = new Inventory();
        inventory.set_id(cursor.getLong(cursor.getColumnIndex(InventoryContract.InventoryEntry._ID)));
        inventory.setName(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_NAME_NAME)));
        inventory.setQuantity(cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_NAME_QUANTITY)));
        inventory.setPrice(cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_NAME_PRICE)));
        inventory.setCurrency(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_NAME_CURRENCY)));
        inventory.setSupplier(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_NAME_SUPPLIER)));
        inventory.setEmail(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_NAME_EMAIL)));
        inventory.setImage(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_NAME_IMAGE)));
        return inventory;
    }

    /**
     * Get all the inventories.
     * @return -.
     */
    public List<Inventory> getAllInventories() {
        ArrayList<Inventory> inventories = null;
        Cursor cursor = mInventoryDatabase.getReadableDatabase().query(
                InventoryContract.InventoryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        if (null != cursor) {
            if (cursor.moveToFirst()) {
                inventories = new ArrayList<>();
                do {
                    Inventory inventory = parseInventoryFrom(cursor);
                    if (null != inventory) {
                        inventories.add(inventory);
                    }
                } while(cursor.moveToNext());
            }

            cursor.close();
        }

        return inventories;
    }

    /**
     * UPDATE the existing inventory.
     * @param inventory -.
     * @return updated count.
     */
    public int updateInventory(@NonNull final Inventory inventory) {
        int result = 0;

        /**
         * Using id as the selection clause. So no point if it is null.
         */
        if (null != inventory.get_id()) {

            ContentValues contentValues = getContentValuesFromInventory(inventory);
            contentValues.remove(InventoryContract.InventoryEntry._ID);


            result = mInventoryDatabase.getWritableDatabase().update(
                    InventoryContract.InventoryEntry.TABLE_NAME,
                    contentValues,
                    InventoryContract.InventoryEntry._ID + "=?",
                    new String[]{inventory.get_id().toString()}
            );
        }

        return result;

    }

    /**
     * DELETE inventroy.
     * @param inventory -.
     * @return deleted inventory count.
     */
    public int deleteInventory(@NonNull final Inventory inventory) {
        int result = 0;
        if (null != inventory.get_id()) {
            result = mInventoryDatabase.getWritableDatabase().delete(
                    InventoryContract.InventoryEntry.TABLE_NAME,
                    InventoryContract.InventoryEntry._ID + "=?",
                    new String[]{inventory.get_id().toString()}
            );
        }

        return result;
    }

    /**
     * Deletes all the inventory from the database.
     * @return deleted inventory count.
     */
    public int deleteAllInventories() {
        return mInventoryDatabase.getWritableDatabase().delete(
                InventoryContract.InventoryEntry.TABLE_NAME,
                "1",
                null
        );
    }
}
