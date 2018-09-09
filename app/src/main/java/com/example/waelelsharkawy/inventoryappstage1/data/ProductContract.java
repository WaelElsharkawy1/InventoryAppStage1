package com.example.waelelsharkawy.inventoryappstage1.data;

import android.provider.BaseColumns;

public class ProductContract {
    public ProductContract() {
    }
    public static final class ProductEntry implements BaseColumns{
        public  final static String TABLE_NAME="product";
        public  final static String _ID="_id";
        public  final static String COLUMN_PRO_NAME="product_name";
        public  final static String COLUMN_PRO_PRICE="price";
        public  final static String COLUMN_PRO_QUANTITY="quantity";
        public  final static String COLUMN_PRO_SUP_NAME="supplier_name";
        public  final static String COLUMN_PRO_SUP_PHONE="supplier_phone";
    }
}
