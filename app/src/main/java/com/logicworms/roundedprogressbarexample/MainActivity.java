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
        orderProgressBar.setCurrentProgress(10);

        OrderProgressBar orderProgressBar2 = findViewById(R.id.progressbar2);
        orderProgressBar2.setCurrentProgress(90);

        OrderProgressBar orderProgressBar3 = findViewById(R.id.progressbar3);
        orderProgressBar3.setCurrentProgress(130);

//        RemainingTimeTextView textView = findViewById(R.id.textView);
//        RemainingTimeTextView textView2 = findViewById(R.id.textView2);
//        RemainingTimeTextView textView3 = findViewById(R.id.textView3);
//        String DATE = "08/May/2019";
//        String TIME = "2:46 PM";
//        WaitingTime waitingTime = new WaitingTime();
//        waitingTime.hour = 0;
//        waitingTime.minute = 15;
//        int targetTime = TimeUtils.getDifferenceFromTargetTime(DATE, TIME,waitingTime.hour,waitingTime.minute   );
//        Log.i(TAG, "Target Time : " + targetTime);
//        textView.setTargetTime(targetTime);
//        textView.setMaxLateTime(600);
//        textView2.setTargetTime(targetTime);
//        textView2.setMaxLateTime(600);
//        textView3.setTargetTime(targetTime);
//        textView3.setMaxLateTime(600);
    }
}
