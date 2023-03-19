package com.example.lab1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lab1.models.Data;
import com.example.lab1.R;

public class DataInputFragment extends Fragment {

    private EditText inputFirstNumberText;
    private EditText inputSecondNumberText;
    private RadioGroup radioGroup;
    private int checkedButtonId;
    public DataInputFragment(){
        super(R.layout.input_data);
    }
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.input_data, container, false);
        this.rootView = rootView;
        return rootView;
    }

    public Data getData () {
        Data data = new Data();
        inputFirstNumberText = rootView.findViewById(R.id.firstNumberEditText);
        inputSecondNumberText = rootView.findViewById(R.id.secondNumberEditText);
        radioGroup = rootView.findViewById(R.id.operationsRadioGroup);
        double firstNumber = Double.parseDouble(String.valueOf(inputFirstNumberText.getText()));
        double secondNumber = Double.parseDouble(String.valueOf(inputSecondNumberText.getText()));
        checkedButtonId = radioGroup.getCheckedRadioButtonId();

        if (inputSecondNumberText.getText().toString().equals("") || inputFirstNumberText.getText().toString().equals("")){
            Toast toast = Toast.makeText(getActivity(),
                    "Заповніть усі поля!", Toast.LENGTH_SHORT);
            toast.show();

        } else {
            data.setButtonId(checkedButtonId);
            data.setFirstNumber(firstNumber);
            data.setSecondNumber(secondNumber);

        }
    return data;
    }
}


