package com.example.spendingmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {
    DatabaseManager spendingDB;
    TextView bal;
    EditText dateUI, priceUI, itemUI;
    Button addUI, subUI;
    TextView historyBot;

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
        historyBot = (TextView) findViewById(R.id.historyBot);
        addHistory();
        retHistory();
    }
    
    public void addHistory(){
        addUI.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double price = Double.parseDouble(priceUI.getText().toString());
                        boolean result = spendingDB.createHistory(itemUI.getText().toString(), dateUI.getText().toString(), price);
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

        subUI.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double price = -1 * Double.parseDouble(priceUI.getText().toString());
                        boolean result = spendingDB.createHistory(itemUI.getText().toString(), dateUI.getText().toString(), price);
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

    public void retHistory(){
        Cursor result = spendingDB.pullData();
        StringBuffer str = new StringBuffer();
        Double balance = 0.0;

        while(result.moveToNext()){
            String priceString = result.getString(3);
            double price = Double.parseDouble(result.getString(3));
            balance += price;

            if (price < 0) {
                str.append("Spent $");
                priceString = priceString.substring(1);
            }
            else {
                str.append("Added $");
            }
            str.append(priceString + " on " + result.getString(2)
                    + " for " + result.getString(1) + "\n");
        }
        MainActivity.this.bal.setText("Current Balance: $" + Double.toString(balance));
        MainActivity.this.historyBot.setText(str);
    }
}
