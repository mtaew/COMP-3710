package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtF, txtC, txtMI, txtKM, txtLB, txtKG, txtGL, txtLI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtF = findViewById(R.id.txtF);
        txtC = findViewById(R.id.txtC);
        txtMI = findViewById(R.id.txtMI);
        txtKM = findViewById(R.id.txtKM);
        txtLB = findViewById(R.id.txtLB);
        txtKG = findViewById(R.id.txtKG);
        txtGL = findViewById(R.id.txtGL);
        txtLI = findViewById(R.id.txtLI);


        txtF.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) {
                String strF = s.toString();
                Log.i("UnitConverter", "Value in txtF = " + strF);

                if (!txtF.isFocused()) return;
                if (strF.length() == 0) return;


                try {
                    double valF = Double.parseDouble(strF);
                    double valC = (valF - 32.0)*5/9;
                    String strC = Double.toString(valC);
                    Log.i("UnitConverter", "Value in txtC = " + strC);

                    MainActivity.this.txtC.setText(strC);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );

        txtC.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) {
                String strC = s.toString();
                Log.i("UnitConverter", "Value in txtC = " + strC);

                if (!txtC.isFocused()) return;
                if (strC.length() == 0) return;

                try {
                    double valC = Double.parseDouble(strC);
                    double valF = valC*9/5 + 32;
                    String strF = Double.toString(valF);
                    Log.i("UnitConverter", "Value in txtF = " + strF);

                    MainActivity.this.txtF.setText(strF);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );

        txtMI.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {
                String strmi = s.toString();
                Log.i("UnitConverter", "Value in txtMI = " + strmi);

                if (!txtMI.isFocused()) return;
                if (strmi.length() == 0) return;
                try {
                    double valmi = Double.parseDouble(strmi);
                    // approximate conversion stated on google
                    double valkm = valmi * 1.609;
                    String strkm = Double.toString(valkm);
                    Log.i("UnitConverter", "Value in txtKM = " + strkm);

                    MainActivity.this.txtKM.setText(strkm);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );

        txtKM.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {
                String strkm = s.toString();
                Log.i("UnitConverter", "Value in txtKM = " + strkm);

                if (!txtKM.isFocused()) return;
                if (strkm.length() == 0) return;
                try {
                    double valkm = Double.parseDouble(strkm);
                    double valmi = valkm / 1.609;
                    String strmi = Double.toString(valmi);
                    Log.i("UnitConverter", "Value in txtMI = " + strmi);

                    MainActivity.this.txtMI.setText(strmi);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );

        txtLB.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {
                String strlbs = s.toString();
                Log.i("UnitConverter", "Value in txtLB = " + strlbs);

                if (!txtLB.isFocused()) return;
                if (strlbs.length() == 0) return;
                try {
                    double vallbs = Double.parseDouble(strlbs);
                    // approximate conversion stated on google
                    double valkg = vallbs / 2.205;
                    String strkg = Double.toString(valkg);
                    Log.i("UnitConverter", "Value in txtKG = " + strkg);

                    MainActivity.this.txtKG.setText(strkg);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );

        txtKG.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {
                String strkg = s.toString();
                Log.i("UnitConverter", "Value in txtKG = " + strkg);

                if (!txtKG.isFocused()) return;
                if (strkg.length() == 0) return;
                try {
                    double valkg = Double.parseDouble(strkg);
                    // approximate conversion stated on google
                    double vallbs = valkg * 2.205;
                    String strlbs = Double.toString(vallbs);
                    Log.i("UnitConverter", "Value in txtLB = " + strlbs);

                    MainActivity.this.txtLB.setText(strlbs);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );

        txtGL.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {
                String strgal = s.toString();
                Log.i("UnitConverter", "Value in txtGL = " + strgal);

                if (!txtGL.isFocused()) return;
                if (strgal.length() == 0) return;
                try {
                    double valgal = Double.parseDouble(strgal);
                    // approximate conversion stated on google
                    double valL = valgal * 3.785;
                    String strL = Double.toString(valL);
                    Log.i("UnitConverter", "Value in txtL = " + strL);

                    MainActivity.this.txtLI.setText(strL);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );

        txtLI.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {
                String strL = s.toString();
                Log.i("UnitConverter", "Value in txtL = " + strL);

                if (!txtLI.isFocused()) return;
                if (strL.length() == 0) return;
                try {
                    double valL = Double.parseDouble(strL);
                    // approximate conversion stated on google
                    double valgal = valL / 3.785;
                    String strgal = Double.toString(valgal);
                    Log.i("UnitConverter", "Value in txtGL = " + strgal);

                    MainActivity.this.txtGL.setText(strgal);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );
    }
}
