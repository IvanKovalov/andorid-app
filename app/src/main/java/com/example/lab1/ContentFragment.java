package com.example.lab1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ContentFragment extends Fragment {
    private Button button;
    private int checkedButtonId;
    private double firstNumber;
    private double secondNumber;
    private TextView textView;

    public ContentFragment(){
        super(R.layout.fragment_content);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_content, container, false);

        textView = rootView.findViewById(R.id.textView);
        button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener () {

            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
                assert listener != null;
                listener.onButtonSelected(checkedButtonId, firstNumber, secondNumber);

                double res;
                switch (checkedButtonId) {
                    case -1:
                        Toast.makeText(getActivity(), "Ви нічого не обрали!",
                                Toast.LENGTH_SHORT).show();
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
