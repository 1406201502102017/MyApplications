package com.gb.android.lessons.hw2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gb.android.lessons.R;

public class Calculator extends AppCompatActivity {

    Button one, two, three, plus, minus, four, five, six, divide, multiply, seven, eight, nine, zero, point, clean, equals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        one = (Button) findViewById(R.id.button1);
        two = (Button) findViewById(R.id.button2);
        three = (Button) findViewById(R.id.button3);
        plus = (Button) findViewById(R.id.button4);
        minus = (Button) findViewById(R.id.button5);

        four = (Button) findViewById(R.id.button6);
        five = (Button) findViewById(R.id.button7);
        six = (Button) findViewById(R.id.button8);
        divide = (Button) findViewById(R.id.button9);
        multiply = (Button) findViewById(R.id.button10);

        seven = (Button) findViewById(R.id.button11);
        eight = (Button) findViewById(R.id.button12);
        nine = (Button) findViewById(R.id.button13);
        zero = (Button) findViewById(R.id.button20);
        point = (Button) findViewById(R.id.button22);

        clean = (Button) findViewById(R.id.button21);
        equals = (Button) findViewById(R.id.button14);
    }

    public void clickButton(View view) {

    }
}
