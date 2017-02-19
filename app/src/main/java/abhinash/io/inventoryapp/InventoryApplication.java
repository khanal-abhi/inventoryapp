package abhinash.io.inventoryapp;

import android.app.Application;

import abhinash.io.inventoryapp.database.InventoryDAO;

/**
 * Created by khanal on 2/18/17.
 * Custom Application.
 */
public class InventoryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * Instantiate the singleton.
         */
        InventoryDAO.getInstance(this);
    }
}
