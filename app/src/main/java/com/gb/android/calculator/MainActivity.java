package com.gb.android.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.radiobutton.MaterialRadioButton;
/*
homework #4
1. Переделайте все кнопки на материал.
2. Все размеры и строки сделайте ресурсами.
3. Создайте стиль для своего приложения.
4. *Создайте светлую и тёмную тему для приложения.
homework#5
1. Создайте активити с настройками, где включите выбор темы приложения.
2. Доделайте приложение «Калькулятор». Это последний урок с созданием приложения
«Калькулятор».
 */
public class MainActivity extends AppCompatActivity {

    TextView result;
    TextView operation;
    EditText number;
    Double operand = null;
    String lastOperation = "=";

    private static final String NameSharedPreference = "LOGIN";
    private static final String appTheme = "APP_THEME";
    private static final int MyCoolCodeStyle = 0;
    private static final int AppThemeLightCodeStyle = 1;
    private static final int AppThemeDarkCodeStyle = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(getAppTheme(R.style.MyCoolStyle));

        setContentView(R.layout.activity_main);

        initThemeChooser();

        result = findViewById(R.id.result);
        operation = findViewById(R.id.operation);
        number = findViewById(R.id.number);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }
    }
 //-------------------------------------------------------------------------------------
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
    //----------------------------------------------------------------------------------------
    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonMaterialLight),
                AppThemeLightCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonMaterialDark),
                AppThemeDarkCodeStyle);
        RadioGroup rg = findViewById(R.id.radioButtons);

        ((MaterialRadioButton)rg.getChildAt(getCodeStyle(MyCoolCodeStyle))).setChecked(true);
    }

    private void initRadioButton(View button, final int codeStyle) {
        button.setOnClickListener(view -> {
            setAppTheme(codeStyle);
            recreate();
        });
    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    private int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return sharedPref.getInt(appTheme, codeStyle);
    }

    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(appTheme, codeStyle);
        editor.apply();
    }

    private int codeStyleToStyleId(int codeStyle){
        switch(codeStyle){
            case AppThemeLightCodeStyle:
                return R.style.AppThemeLight;
            case AppThemeDarkCodeStyle:
                return R.style.AppThemeDark;
            default:
                return R.style.MyCoolStyle;
        }
    }
}
