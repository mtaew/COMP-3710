package com.example.filteringspendingmanager;

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
    EditText dayIn, monthIn, yearIn, priceIn, itemIn, initialDay, initialMonth, initialYear;
    EditText finalDay, finalMonth, finalYear, initialAmount, finalAmount;
    Button addUI, subUI, filterUI, clearFilterUI;
    TableLayout historyTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spendingDB = new DatabaseManager(this);
        bal = (TextView) findViewById(R.id.bal);
        dayIn = (EditText) findViewById(R.id.dayIn);
        monthIn = (EditText) findViewById(R.id.monthIn);
        yearIn = (EditText) findViewById(R.id.yearIn);
        priceIn = (EditText) findViewById(R.id.priceIn);
        itemIn = (EditText) findViewById(R.id.itemIn);
        initialDay = (EditText) findViewById(R.id.initialDay);
        initialMonth = (EditText) findViewById(R.id.initialMonth);
        initialYear = (EditText) findViewById(R.id.initialYear);
        finalDay = (EditText) findViewById(R.id.finalDay);
        finalMonth = (EditText) findViewById(R.id.finalMonth);
        finalYear = (EditText) findViewById(R.id.finalYear);
        initialAmount = (EditText) findViewById(R.id.initialAmount);
        finalAmount = (EditText) findViewById(R.id.finalAmount);
        filterUI = (Button) findViewById(R.id.filterUI);
        clearFilterUI = (Button) findViewById(R.id.clearFilterUI);
        addUI = (Button) findViewById(R.id.addUI);
        subUI = (Button) findViewById(R.id.subUI);
        historyTable = (TableLayout) findViewById(R.id.historyTable);
        Cursor historyRes = spendingDB.pullData();
        retHistory(historyRes);
        addHistory();
        filterUI();
        clearFilterUI();
    }

    public void addHistory() {
        addUI.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double price = Double.parseDouble(priceIn.getText().toString());
                        String day = dayIn.getText().toString();
                        String month = monthIn.getText().toString();
                        String year = yearIn.getText().toString();
                        TableModel mTable = new TableModel();
                        mTable.mDate = concatDate(day, month, year);
                        mTable.mCategory = itemIn.getText().toString();
                        mTable.mAmount = price;
                        boolean result = spendingDB.createHistory(mTable);
                        if (result)
                            Toast.makeText(MainActivity.this, "Successfully Created", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Failed to Create", Toast.LENGTH_LONG).show();
                        Cursor historyRes = spendingDB.pullData();
                        retHistory(historyRes);
                        clearAll();
                    }
                }
        );

        subUI.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double price = -1 * Double.parseDouble(priceIn.getText().toString());
                        String day = dayIn.getText().toString();
                        String month = monthIn.getText().toString();
                        String year = yearIn.getText().toString();
                        TableModel mTable = new TableModel();
                        mTable.mDate = concatDate(day, month, year);
                        mTable.mCategory = itemIn.getText().toString();
                        mTable.mAmount = price;
                        boolean result = spendingDB.createHistory(mTable);
                        if (result)
                            Toast.makeText(MainActivity.this, "Successfully Created", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Failed to Create", Toast.LENGTH_LONG).show();
                        Cursor historyRes = spendingDB.pullData();
                        retHistory(historyRes);
                        clearAll();
                    }
                }
        );
    }

    public void filterUI(){
        filterUI.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String initAmount = initialAmount.getText().toString();
                        String finAmount = finalAmount.getText().toString();
                        String day = initialDay.getText().toString();
                        String month = initialMonth.getText().toString();
                        String year = initialYear.getText().toString();
                        String initialDate = concatDate(day, month, year);
                        day = finalDay.getText().toString();
                        month = finalMonth.getText().toString();
                        year = finalYear.getText().toString();
                        String finalDate = concatDate(day, month, year);
                        Cursor result = spendingDB.returnFilter(initAmount, finAmount, initialDate, finalDate);
                        retHistory(result);
                    }
                }
        );
    }

    public void clearFilterUI(){
        clearFilterUI.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearAll();
                        Cursor historyRes = spendingDB.pullData();
                        retHistory(historyRes);
                    }
                }
        );
    }

    public void retHistory(Cursor historyIn) {
        if (historyIn == null) {
            return;
        }

        Double balance = 0.0;
        int c = historyTable.getChildCount();
        for (int i = 1; i < c; i++) {
            historyTable.removeViewAt(1);
        }

        while (historyIn.moveToNext()) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams columnLayout = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            columnLayout.weight = 1;

            TextView dateRow = new TextView(this);
            dateRow.setLayoutParams(columnLayout);
            dateRow.setText(historyIn.getString(2));
            row.addView(dateRow);

            TextView amountRow = new TextView(this);
            amountRow.setLayoutParams(columnLayout);
            amountRow.setText(historyIn.getString(3));
            row.addView(amountRow);

            TextView categoryRow = new TextView(this);
            categoryRow.setLayoutParams(columnLayout);
            categoryRow.setText(historyIn.getString(1));
            row.addView(categoryRow);

            historyTable.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            double price = Double.parseDouble(historyIn.getString(3));
            balance += price;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        MainActivity.this.bal.setText("Current Balance: $" + df.format(balance));
    }
    public void clearAll() {
        MainActivity.this.dayIn.setText("");
        MainActivity.this.monthIn.setText("");
        MainActivity.this.yearIn.setText("");
        MainActivity.this.priceIn.setText("");
        MainActivity.this.itemIn.setText("");
        MainActivity.this.initialDay.setText("");
        MainActivity.this.initialMonth.setText("");
        MainActivity.this.initialYear.setText("");
        MainActivity.this.finalDay.setText("");
        MainActivity.this.finalMonth.setText("");
        MainActivity.this.finalYear.setText("");
        MainActivity.this.initialAmount.setText("");
        MainActivity.this.finalAmount.setText("");
    }
    public String concatDate (String day, String month, String year) {
        return month + "-" + day + "-" + year;
    }
}
