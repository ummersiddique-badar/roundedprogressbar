package com.logicworms.roundedprogressbarexample;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.logicworms.roundedprogressbar.OrderProgressBar;
import com.logicworms.roundedprogressbar.RemainingTimeTextView;
import com.logicworms.roundedprogressbar.utils.TimeUtils;
import com.logicworms.roundedprogressbar.utils.WaitingTime;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

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
        orderProgressBar.setCurrentProgress(305);

        RemainingTimeTextView textView = findViewById(R.id.textView);
        String DATE = "02/May/2019";
        String TIME = "2:54 PM";
        WaitingTime waitingTime = new WaitingTime();
        waitingTime.hour = 0;
        waitingTime.minute = 15;
        int targetTime = TimeUtils.getDifferenceFromTargetTime(DATE, TIME,waitingTime.hour,waitingTime.minute   );
        Log.i(TAG, "Target Time : " + targetTime);
        textView.setTargetTime(targetTime);
        textView.setMaxLateTime(600);
    }
}
