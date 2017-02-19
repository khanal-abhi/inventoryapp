package abhinash.io.inventoryapp.domain;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import abhinash.io.inventoryapp.database.InventoryContract;

/**
 * Created by khanal on 2/18/17.
 * Parcelable inventory object.
 */

public class Inventory implements Parcelable {

    /**
     * Item id.
     */
    private Long _id;
    /**
     * Item Name.
     */
    private String name;
    /**
     * Stock quantity.
     */
    private Integer quantity;
    /**
     * Item price * 100 in integer.
     */
    private Integer price;
    /**
     * Price currency. USD here.
     */
    private String currency;
    /**
     * Name of supplier.
     */
    private String supplier;
    /**
     * Supplier email address.
     */
    private String email;
    /**
     * Item image uri string.
     */
    private String image;

    /**
     * Default constructor for the pojo.
     */
    public Inventory() {
    }

    private Inventory(Parcel in) {
        /**
         * Extract the saved bundle using the overloaded method for this class loader.
         */
        Bundle bundle = in.readBundle(getClass().getClassLoader());
        /**
         * Set the attributes accordingly.
         */
        set_id(bundle.getLong(InventoryContract.InventoryEntry._ID));
        setName(bundle.getString(InventoryContract.InventoryEntry.COLUMN_NAME_NAME));
        setQuantity(bundle.getInt(InventoryContract.InventoryEntry.COLUMN_NAME_QUANTITY));
        setPrice(bundle.getInt(InventoryContract.InventoryEntry.COLUMN_NAME_PRICE));
        setCurrency(bundle.getString(InventoryContract.InventoryEntry.COLUMN_NAME_CURRENCY));
        setSupplier(bundle.getString(InventoryContract.InventoryEntry.COLUMN_NAME_SUPPLIER));
        setEmail(bundle.getString(InventoryContract.InventoryEntry.COLUMN_NAME_EMAIL));
        setImage(bundle.getString(InventoryContract.InventoryEntry.COLUMN_NAME_IMAGE));
    }

    /**
     * Static methods the implement parceling.
     */
    public static final Creator<Inventory> CREATOR = new Creator<Inventory>() {
        @Override
        public Inventory createFromParcel(Parcel in) {
            return new Inventory(in);
        }

        @Override
        public Inventory[] newArray(int size) {
            return new Inventory[size];
        }
    };

    /**
     * Very obvious getters and setters.
     */

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Not using this at this time.
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        /**
         * Bundle up the pojo.
         */
        Bundle bundle = new Bundle();
        bundle.putLong(InventoryContract.InventoryEntry._ID, get_id());
        bundle.putString(InventoryContract.InventoryEntry.COLUMN_NAME_NAME, getName());
        bundle.putInt(InventoryContract.InventoryEntry.COLUMN_NAME_QUANTITY, getQuantity());
        bundle.putInt(InventoryContract.InventoryEntry.COLUMN_NAME_PRICE, getPrice());
        bundle.putString(InventoryContract.InventoryEntry.COLUMN_NAME_CURRENCY, getCurrency());
        bundle.putString(InventoryContract.InventoryEntry.COLUMN_NAME_SUPPLIER, getSupplier());
        bundle.putString(InventoryContract.InventoryEntry.COLUMN_NAME_EMAIL, getEmail());
        bundle.putString(InventoryContract.InventoryEntry.COLUMN_NAME_IMAGE, getImage());

        /**
         * Write the bundle to the parcel.
         */
        parcel.writeBundle(bundle);
    }
}
