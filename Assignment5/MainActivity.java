package com.example.spendingmanagertable;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    DatabaseManager spendingDB;
    TextView bal;
    EditText dateUI, priceUI, itemUI;
    Button addUI, subUI;
    TableLayout historyTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spendingDB = new DatabaseManager(this);
        bal = (TextView) findViewById(R.id.bal);
        dateUI = (EditText) findViewById(R.id.dateUI);
        priceUI = (EditText) findViewById(R.id.priceUI);
        itemUI = (EditText) findViewById(R.id.itemUI);
        addUI = (Button) findViewById(R.id.addUI);
        subUI = (Button) findViewById(R.id.subUI);
        historyTable = (TableLayout) findViewById(R.id.historyTable);
        addHistory();
        retHistory();
    }

    public void addHistory() {
        addUI.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double price = Double.parseDouble(priceUI.getText().toString());
                        TableModel mTable = new TableModel();
                        mTable.mDate = dateUI.getText().toString();
                        mTable.mCategory = itemUI.getText().toString();
                        mTable.mAmount = price;
                        boolean result = spendingDB.createHistory(mTable);
                        if (result)
                            Toast.makeText(MainActivity.this, "Successfully Created", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Failed to Create", Toast.LENGTH_LONG).show();
                        MainActivity.this.dateUI.setText("");
                        MainActivity.this.priceUI.setText("");
                        MainActivity.this.itemUI.setText("");
                        retHistory();
                    }
                }
        );

        subUI.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double price = -1 * Double.parseDouble(priceUI.getText().toString());
                        TableModel mTable = new TableModel();
                        mTable.mDate = dateUI.getText().toString();
                        mTable.mCategory = itemUI.getText().toString();
                        mTable.mAmount = price;
                        boolean result = spendingDB.createHistory(mTable);
                        if (result)
                            Toast.makeText(MainActivity.this, "Successfully Created", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Failed to Create", Toast.LENGTH_LONG).show();
                        retHistory();
                        MainActivity.this.dateUI.setText("");
                        MainActivity.this.priceUI.setText("");
                        MainActivity.this.itemUI.setText("");
                    }
                }
        );
    }

    public void retHistory() {
        int c = historyTable.getChildCount();
        for (int i = 1; i < c; i++) {
            historyTable.removeViewAt(1);
        }

        Cursor result = spendingDB.pullData();
        Double balance = 0.0;

        while (result.moveToNext()) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams columnLayout = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            columnLayout.weight = 1;

            TextView dateRow = new TextView(this);
            dateRow.setLayoutParams(columnLayout);
            dateRow.setText(result.getString(2));
            row.addView(dateRow);

            TextView amountRow = new TextView(this);
            amountRow.setLayoutParams(columnLayout);
            amountRow.setText(result.getString(3));
            row.addView(amountRow);

            TextView categoryRow = new TextView(this);
            categoryRow.setLayoutParams(columnLayout);
            categoryRow.setText(result.getString(1));
            row.addView(categoryRow);

            historyTable.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            double price = Double.parseDouble(result.getString(3));
            balance += price;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        MainActivity.this.bal.setText("Current Balance: $" + df.format(balance));
    }
}