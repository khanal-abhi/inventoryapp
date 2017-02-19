package abhinash.io.inventoryapp.database;

import android.provider.BaseColumns;

/**
 * Created by khanal on 2/18/17.
 * The contract class for the Inventory database.
 */

public final class InventoryContract {

    /**
     * Prevent accidental instantiation.
     */
    private InventoryContract() {}

    /**
     * The column names for the database entry.
     */
    public static final class InventoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "inventory";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_CURRENCY = "currency";
        public static final String COLUMN_NAME_SUPPLIER = "supplier";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_IMAGE = "image";
    }

    /**
     * Database file name.
     */
    public static final String DATABASE_NAME = "inventory.db";

    /**
     * Sql create query.
     */
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + InventoryEntry.TABLE_NAME + " (" +
                    InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    InventoryEntry.COLUMN_NAME_NAME + " TEXT," +
                    InventoryEntry.COLUMN_NAME_PRICE + " INTEGER," +
                    InventoryEntry.COLUMN_NAME_CURRENCY + " TEXT," +
                    InventoryEntry.COLUMN_NAME_QUANTITY + " INTEGER," +
                    InventoryEntry.COLUMN_NAME_SUPPLIER + " TEXT," +
                    InventoryEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    InventoryEntry.COLUMN_NAME_IMAGE + " TEXT," +
                    ");";

    /**
     * Sql delete table query.
     */
    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + InventoryEntry.TABLE_NAME + ";";


}
