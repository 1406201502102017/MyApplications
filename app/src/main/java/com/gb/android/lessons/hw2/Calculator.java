package com.gb.android.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_calculator extends AppCompatActivity {

    TextView result;
    TextView operation;
    EditText number;
    Double operand = null;
    String lastOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calculator);

        result = findViewById(R.id.result);
        operation = findViewById(R.id.operation);
        number = findViewById(R.id.number);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if (operand != null) {outState.putDouble("OPERAND", operand);}
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        result.setText(operand.toString());
        operation.setText(lastOperation);
    }

    public void onNumberClick(View view) {

        Button button = (Button) view;
        number.append(button.getText());

        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    public void onOperationClick(View view) {

        Button button = (Button) view;
        String op = button.getText().toString();
        String numberTwo = number.getText().toString();

        if (numberTwo.length() > 0) {
            numberTwo = numberTwo.replace(',', '.');
            try {
                performOperation(Double.valueOf(numberTwo), op);
            } catch (NumberFormatException e) {
                number.setText("");
            }
        }
        lastOperation = op;
        operation.setText(lastOperation);
    }

    @SuppressLint("SetTextI18n")
    private void performOperation(Double numberTwo, String operation) {
        if (operand == null) {
            operand =numberTwo;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch (lastOperation) {
                case "=":
                    operand = numberTwo;
                    break;
                case "/":
                    if (numberTwo == 0) {
                        operand = 0.0;
                    } else {
                        operand /= numberTwo;
                    }
                    break;
                case "*":
                    operand *= numberTwo;
                    break;
                case "+":
                    operand += numberTwo;
                    break;
                case "-":
                    operand -= numberTwo;
                    break;
            }
        }
        result.setText(operand.toString().replace('.', ','));
        number.setText("");
    }
}
