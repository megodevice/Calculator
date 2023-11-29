package com.geeks.calculator;

import androidx.appcompat.app.AppCompatActivity;

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

    private boolean isLike;
    ImageButton imageButtonLike;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textViewResult = findViewById(R.id.text_view_result);
        imageButtonLike = findViewById(R.id.button_like);
        Spinner spinner = findViewById(R.id.spinner);
        RangeSlider rangeSlider1 = findViewById(R.id.range_slider1);
        RangeSlider rangeSlider2 = findViewById(R.id.range_slider2);
        String[] items = {"Type", "Age", "Name"};
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(SecondActivity.this, R.xml.spinner_text, items);
        adapter.setDropDownViewResource(R.xml.spinner_dropdown);
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