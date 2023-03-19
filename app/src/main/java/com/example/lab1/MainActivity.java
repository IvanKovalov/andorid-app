package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnSelectedButtonListener{

    private Button button;
    private RadioButton plusButton;
    private RadioButton minusButton;
    private RadioButton divButton;
    private RadioButton mulButton;
    private EditText inputFirstNumberText;
    private EditText inputSecondNumberText;
    private TextView textView;
    private RadioGroup radioGroup;

    private RadioButton checkedButton;
    private int checkedButtonId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        plusButton = findViewById(R.id.PlusButton);
        minusButton = findViewById(R.id.MinusButton);
        divButton = findViewById(R.id.DivButton);
        mulButton = findViewById(R.id.MulButton);
        inputFirstNumberText = findViewById(R.id.firstNumberEditText);
        inputSecondNumberText = findViewById(R.id.secondNumberEditText);
        textView = findViewById(R.id.textView);
        radioGroup = findViewById(R.id.operationsRadioGroup);
       /* button.setOnClickListener(new View.OnClickListener () {

            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                if (inputSecondNumberText.getText().toString().equals("") || inputFirstNumberText.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Заповніть усі поля!", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                double firstNumber = Double.parseDouble(String.valueOf(inputFirstNumberText.getText()));
                double secondNumber = Double.parseDouble(String.valueOf(inputSecondNumberText.getText()));
                checkedButtonId = radioGroup.getCheckedRadioButtonId();

                double res;
                switch (checkedButtonId) {
                    case -1:
                        Toast.makeText(getApplicationContext(), "Ви нічого не обрали!",
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
        });*/
    }

    @Override
    public void onButtonSelected(int buttonIndex, double firstNum, double secondNum) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Получаем ссылку на второй фрагмент по ID
        ContentFragment contentFragment = (ContentFragment) fragmentManager
                .findFragmentById(R.id.fragment_container_view);
        DataInput dataInput = (DataInput) fragmentManager
                .findFragmentById(R.id.fragment_container_view_1);
        assert dataInput != null;
        Data data = dataInput.getData();
        // Выводим нужную информацию
        if (contentFragment != null)
            contentFragment.setData(data.getButtonId(), data.getFirstNumber(), data.getSecondNumber());
    }
}