package com.example.waelelsharkawy.inventoryappstage1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.waelelsharkawy.inventoryappstage1.data.ProductContract.ProductEntry;
import com.example.waelelsharkawy.inventoryappstage1.data.ProductDbHelper;
public class MainActivity extends AppCompatActivity {
    private ProductDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });
        mDbHelper = new ProductDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor = db.rawQuery("SELECT * FROM " +ProductEntry.TABLE_NAME, null);
        TextView displayView = findViewById(R.id.text_view_product);
        try {
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(ProductEntry._ID + " - " +
                    ProductEntry.COLUMN_PRO_NAME + " - " +
                    ProductEntry.COLUMN_PRO_PRICE + " - " +
                    ProductEntry.COLUMN_PRO_QUANTITY+ " - " +
                    ProductEntry.COLUMN_PRO_SUP_NAME+ " - " +
                    ProductEntry.COLUMN_PRO_SUP_PHONE + "\n");
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(ProductEntry._ID);
            int proNameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_NAME);
            int proPriceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_PRICE);
            int proQuantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_QUANTITY);
            int proSupNameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_SUP_NAME);
            int proSupPhoneColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_SUP_PHONE);
            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentProName = cursor.getString(proNameColumnIndex);
                int currentProPrice = cursor.getInt(proPriceColumnIndex);
                int currentProQuantity = cursor.getInt(proQuantityColumnIndex);
                String currentProSupName = cursor.getString(proSupNameColumnIndex);
                String currentProSupPhone = cursor.getString(proSupPhoneColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentProName + " - " +
                        currentProPrice + " - " +
                        currentProQuantity + " - " +
                        currentProSupName + " - " +
                        currentProSupPhone));
            }
        } finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
