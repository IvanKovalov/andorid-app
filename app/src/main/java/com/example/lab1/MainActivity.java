package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lab1.fragments.ContentFragment;
import com.example.lab1.fragments.DataInputFragment;
import com.example.lab1.models.Data;

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

    }

    @Override
    public void onButtonSelected(int buttonIndex, double firstNum, double secondNum) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Получаем ссылку на второй фрагмент по ID
        ContentFragment contentFragment = (ContentFragment) fragmentManager
                .findFragmentById(R.id.fragment_container_view);
        DataInputFragment dataInput = (DataInputFragment) fragmentManager
                .findFragmentById(R.id.fragment_container_view_1);
        assert dataInput != null;
        Data data = dataInput.getData();
        // Выводим нужную информацию
        if (contentFragment != null)
            contentFragment.setData(data.getButtonId(), data.getFirstNumber(), data.getSecondNumber());
    }
}