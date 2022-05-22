package com.gb.android.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
/*
3. *Сделайте интент-фильтр для запуска калькулятора извне, а также напишите тестовое
приложение, запускающее приложение-калькулятор.
 */
public class MainActivityStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("example://intent");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                @SuppressLint({"WrongConstant", "QueryPermissionsNeeded"})

                ActivityInfo activityInfo =
                        intent.resolveActivityInfo(getPackageManager(),
                                intent.getFlags());
                if (activityInfo != null) {
                    startActivity(intent);
                }
            }
        });
    }

//    public void onClick(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
}
