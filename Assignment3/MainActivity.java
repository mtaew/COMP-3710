package com.example.notemanager;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    dbManager db;
    TextView changeCurNote;
    EditText changePrevNote;
    Button btnInsertNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new dbManager(this);

        changeCurNote = (TextView) findViewById(R.id.changeCurNote);
        btnInsertNote  = (Button)findViewById(R.id.btnSave);
        changePrevNote = (EditText)findViewById(R.id.txtPrevNote);
        AddNote();
    }

    public void AddNote(){
        btnInsertNote.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean result = db.createNote(changeCurNote.getText().toString());
                        if (result)
                            Toast.makeText(MainActivity.this, "Note Created", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Note Not Created", Toast.LENGTH_LONG).show();
                        getNotes();
                    }
                }
        );
    }

    public void getNotes(){
        Cursor result = db.getAllData();
        StringBuffer buffer = new StringBuffer();
        while(result.moveToNext()){
            buffer.append("Note #" + result.getString(0) + ": ");
            buffer.append(result.getString(1) + "\n\n");
        }

        MainActivity.this.changePrevNote.setText(buffer);
    }
}