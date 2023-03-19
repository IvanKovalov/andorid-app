package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab1.db.DBHelper;

public class SecondActivity extends AppCompatActivity {

    private Button backButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        backButton = findViewById(R.id.backTMainActivityButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView editText = findViewById(R.id.textView2);
        StringBuilder stringBuilder = new StringBuilder();
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor c = database.query("resultTable", null, null, null, null, null, null);
        if (c.moveToFirst()){
            int idColIndex = c.getColumnIndex("id");
            int resultColIndex = c.getColumnIndex("result");
            do {
                stringBuilder.append("ID: " + c.getInt(idColIndex))
                        .append(" Result: " + c.getDouble(resultColIndex) + "\n");

            } while (c.moveToNext());
        }else {
            c.close();

        }
        dbHelper.close();

        String res = stringBuilder.toString();
        if (res.length() == 0){
            editText.setText("Db is clear");
        } else {
            editText.setText(res);
        }
    }
}