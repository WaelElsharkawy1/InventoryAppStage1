package com.example.waelelsharkawy.inventoryappstage1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.waelelsharkawy.inventoryappstage1.data.ProductContract.*;
import com.example.waelelsharkawy.inventoryappstage1.data.ProductDbHelper;

public class AddProductActivity extends AppCompatActivity {
    private EditText mProNameEditText;
    private EditText mProPriceEditText;
    private EditText mProQuantityEditText;
    private EditText mProSupNameEditText;
    private EditText mProSupPhoneEditText;
    private Button   mProAddButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        mProNameEditText=findViewById(R.id.edit_pro_name);
        mProPriceEditText=findViewById(R.id.edit_pro_price);
        mProQuantityEditText=findViewById(R.id.edit_pro_quantity);
        mProSupNameEditText=findViewById(R.id.edit_pro_sup_name);
        mProSupPhoneEditText=findViewById(R.id.edit_pro_sup_phone);
        mProAddButton=findViewById(R.id.btn_add_product);
        mProAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertProduct();
                finish();
            }
        });
    }
    private void insertProduct(){
        {
            // Read from input fields
            // Use trim to eliminate leading or trailing white space
            String proNameString = mProNameEditText.getText().toString();
            int proPrice = Integer.parseInt(mProPriceEditText.getText().toString());
            int proQuantity = Integer.parseInt(mProQuantityEditText.getText().toString());
            String proSupNameString = mProSupNameEditText.getText().toString();
            String proSupPhoneString = mProSupPhoneEditText.getText().toString();
            // Create database helper
            ProductDbHelper mDbHelper = new ProductDbHelper(this);
            // Gets the database in write mode
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            // Create a ContentValues object where column names are the keys,
            // and pet attributes from the editor are the values.
            ContentValues values = new ContentValues();
            values.put(ProductEntry.COLUMN_PRO_NAME, proNameString);
            values.put(ProductEntry.COLUMN_PRO_PRICE, proPrice);
            values.put(ProductEntry.COLUMN_PRO_QUANTITY,proQuantity);
            values.put(ProductEntry.COLUMN_PRO_SUP_NAME, proSupNameString);
            values.put(ProductEntry.COLUMN_PRO_SUP_PHONE, proSupPhoneString);
            // Insert a new row for pet in the database, returning the ID of that new row.
            long newRowId = db.insert(ProductEntry.TABLE_NAME, null, values);
            // Show a toast message depending on whether or not the insertion was successful
            if (newRowId == -1) {
                // If the row ID is -1, then there was an error with insertion.
                Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast with the row ID.
                Toast.makeText(this, "Product saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
