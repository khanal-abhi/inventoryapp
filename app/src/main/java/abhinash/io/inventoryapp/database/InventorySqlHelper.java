package abhinash.io.inventoryapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * Created by khanal on 2/18/17.
 * This is the SQL Helper class.
 */
public class InventorySqlHelper extends SQLiteOpenHelper {

    /**
     * Version int for the database.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Create a public constructor that only needs the context. Handle the rest of the data from
     * the contract.
     * @param context -.
     */
    public InventorySqlHelper(@NonNull final Context context) {
        super(context, InventoryContract.DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Create a new table for inventory entries.
     * @param sqLiteDatabase -.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(InventoryContract.SQL_CREATE_TABLE);
    }

    /**
     * Handle the upgrading of the database.
     * @param sqLiteDatabase -.
     * @param i Old version.
     * @param i1 New version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /**
         * Just delete the old table and create a new one for this app.
         */
        sqLiteDatabase.execSQL(InventoryContract.SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
