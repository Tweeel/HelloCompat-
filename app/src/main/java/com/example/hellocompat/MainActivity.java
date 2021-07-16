package com.example.hellocompat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mHelloTextView;
    private final String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelloTextView = findViewById(R.id.hello_textview);
        // restore saved instance state (the text color)
        if (savedInstanceState != null) {
            mHelloTextView.setTextColor(savedInstanceState.getInt("color"));
        }

    }

    public void changeColor(View view) {
        // Get a random color name from the color array (20 colors).

        Random random = new Random();
        String colorName = mColorArray[random.nextInt(20)];

        // Get the color identifier that matches the color name.

        int colorResourceName = getResources().getIdentifier(colorName,
                "color", getApplicationContext().getPackageName());

        // Get the color ID from the resources.

        int colorRes;
        
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            colorRes = getResources().getColor(colorResourceName, this.getTheme());
        }
        else  colorRes = ContextCompat.getColor(this, colorResourceName);

        // Set the text color.

        mHelloTextView.setTextColor(colorRes);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // save the current text color
        outState.putInt("color", mHelloTextView.getCurrentTextColor());
    }
}
