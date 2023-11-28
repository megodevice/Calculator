package com.geeks.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.slider.RangeSlider;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewResult;
    private AppCompatSpinner spinner;
    private RangeSlider rangeSlider1;
    private RangeSlider rangeSlider2;
    private boolean isLike;
    ImageButton imageButtonLike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textViewResult = findViewById(R.id.text_view_result);
        imageButtonLike = findViewById(R.id.button_like);
        spinner = findViewById(R.id.spinner);
        rangeSlider1 = findViewById(R.id.range_slider1);
        rangeSlider2 = findViewById(R.id.range_slider2);
        String[] items = new String[]{"Type", "Age", "Name"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        textViewResult.setText(getIntent().getStringExtra(MainActivity.KEY1));
        rangeSlider1.setValues(30.0f,57.0f);
        rangeSlider2.setValues(30.0f,57.0f);
        isLike = false;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onLikeButtonClick(View view) {
        if (isLike) {
            imageButtonLike.setImageDrawable(getDrawable(R.drawable.ic_icon_unlike));
            isLike = false;
        }
        else {
            imageButtonLike.setImageDrawable(getDrawable(R.drawable.ic_icon_like));
            isLike = true;
        }
    }

    public void onNextButtonClick(View view) {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(MainActivity.KEY2, true);
        startActivity(intent);
        finish();
    }
}