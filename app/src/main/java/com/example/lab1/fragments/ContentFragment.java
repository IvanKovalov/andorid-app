package com.example.lab1.fragments;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lab1.db.DBHelper;
import com.example.lab1.OnSelectedButtonListener;
import com.example.lab1.R;
import com.example.lab1.SecondActivity;

public class ContentFragment extends Fragment {
    private Button button;
    private int checkedButtonId;
    private double firstNumber;
    private double secondNumber;
    private TextView textView;
    private Button openButton;
    private Button clearDBButton;

    public ContentFragment(){
        super(R.layout.fragment_content);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_content, container, false);

        textView = rootView.findViewById(R.id.textView);
        button = rootView.findViewById(R.id.button);
        openButton = rootView.findViewById(R.id.openButton);
        clearDBButton = rootView.findViewById(R.id.clearDBButton);
        DBHelper dbHelper = new DBHelper(getActivity());

        button.setOnClickListener(new View.OnClickListener () {

            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {


                OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
                assert listener != null;
                listener.onButtonSelected(checkedButtonId, firstNumber, secondNumber);

                double res = 0;
                boolean success = true;
                switch (checkedButtonId) {
                    case -1:
                        Toast.makeText(getActivity(), "Ви нічого не обрали!",
                                Toast.LENGTH_SHORT).show();
                        success = false;
                        break;
                    case R.id.PlusButton:
                        res = firstNumber + secondNumber;
                        textView.setText(String.format("Result: %.2f", res));
                        break;
                    case R.id.MinusButton:
                        res = firstNumber - secondNumber;
                        textView.setText(String.format("Result: %.2f", res));
                        break;
                    case R.id.DivButton:
                        res = firstNumber / secondNumber;
                        textView.setText(String.format("Result: %.2f", res));
                        break;
                    case R.id.MulButton:
                        res = firstNumber * secondNumber;
                        textView.setText(String.format("Result: %.2f", res));
                        break;

                }

                if (success){
                    ContentValues contentValues = new ContentValues();
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    contentValues.put("result", res);
                    long rowId = database.insert("resultTable", null, contentValues);
                    Toast.makeText(getActivity(), "Result saved",
                            Toast.LENGTH_SHORT).show();
                    dbHelper.close();
                }

            }
        });

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                startActivity(intent);
            }
        });

        clearDBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                database.delete("resultTable", null, null);
            }
        });


        return rootView;
    }

    public void setData (int buttonId, double firstNum, double secondNum){
        this.firstNumber = firstNum;
        this.secondNumber = secondNum;
        this.checkedButtonId = buttonId;

    }


}
