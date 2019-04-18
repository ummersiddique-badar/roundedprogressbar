package com.logicworms.roundedprogressbarexample;

import android.os.Bundle;
import android.widget.Toast;

import com.logicworms.roundedprogressbar.OrderProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OrderProgressBar orderProgressBar = findViewById(R.id.progressbar);
        orderProgressBar.setTimeOverListener(new OrderProgressBar.TimeOverListener() {
            @Override
            public void onIntervalCompleted(int interval) {
                Toast.makeText(MainActivity.this, "Interval : " + interval, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTimeFinished() {
                Toast.makeText(MainActivity.this, "Time Finished", Toast.LENGTH_SHORT).show();
            }
        });
        orderProgressBar.setCurrentProgress(180);
    }
}
