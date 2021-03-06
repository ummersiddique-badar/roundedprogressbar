package com.logicworms.roundedprogressbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class OrderProgressBar extends LinearLayout {

    public static final int DEF_TIME = 60 * 5;
    public static final int DELAY_MILLIS = 1000;
    private static final int MAX_PROGRESS = 60;
    private static final String TAG = OrderProgressBar.class.getSimpleName();
    final Handler handler;
    int totalProgress;
    int currentProgress = 0;
    private int margin = 0;
    private int colorFirst;
    private int colorSecond;
    private int colorThird;
    private RoundCornerProgressBar progressbar1;
    private RoundCornerProgressBar progressbar2;
    private RoundCornerProgressBar progressbar3;
    private RoundCornerProgressBar progressbar4;
    private RoundCornerProgressBar progressbar5;
    private Context context;
    private TimeOverListener listener;
    Runnable timer = new Runnable() {
        @Override
        public void run() {
            setProgress();
            currentProgress++;
            Log.i(TAG, "Current progress : " + currentProgress);
            if (currentProgress <= totalProgress + margin)
                handler.postDelayed(this, DELAY_MILLIS);
        }
    };

    public OrderProgressBar(Context context) {
        this(context, null);
    }

    public OrderProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OrderProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        handler = new Handler();
        init(attrs);
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    private void init(AttributeSet attrs) {
        Resources res = context.getResources();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OrderProgressBar);

        colorFirst = typedArray.getColor(R.styleable.OrderProgressBar_rcFirstColor, res.getColor(R.color.colorGreen));
        colorSecond = typedArray.getColor(R.styleable.OrderProgressBar_rcSecondColor, res.getColor(R.color.colorYellow));
        colorThird = typedArray.getColor(R.styleable.OrderProgressBar_rcThirdColor, res.getColor(R.color.colorRed));
        totalProgress = typedArray.getInteger(R.styleable.OrderProgressBar_rcTime, DEF_TIME);

        typedArray.recycle();

        setOrientation(HORIZONTAL);
        LayoutInflater.from(getContext()).inflate(R.layout.order_progress_bar, this);
        progressbar1 = findViewById(R.id.roundProgressBar1);
        progressbar2 = findViewById(R.id.roundProgressBar2);
        progressbar3 = findViewById(R.id.roundProgressBar3);
        progressbar4 = findViewById(R.id.roundProgressBar4);
        progressbar5 = findViewById(R.id.roundProgressBar5);
        startTime();
    }

    private void startTime() {
        setMax();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        handler.postDelayed(timer, DELAY_MILLIS);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(timer);
    }

    private void setMax() {
        progressbar1.setMax((float) totalProgress / 5);
        progressbar2.setMax((float) totalProgress / 5);
        progressbar3.setMax((float) totalProgress / 5);
        progressbar4.setMax((float) totalProgress / 5);
        progressbar5.setMax((float) totalProgress / 5);
    }

    private void setProgress() {
        int completedBars = currentProgress;
        int progress = completedBars % 60;
        completedBars = completedBars / 60;
        switch (completedBars) {
            case 0:
                setProgress(progressbar1, progress);
                progressbar2.setProgress(0);
                progressbar3.setProgress(0);
                progressbar4.setProgress(0);
                progressbar5.setProgress(0);
                break;
            case 1:
                setProgress(progressbar1, MAX_PROGRESS);
                setProgress(progressbar2, progress);
                progressbar3.setProgress(0);
                progressbar4.setProgress(0);
                progressbar5.setProgress(0);
                break;
            case 2:
                setProgress(progressbar1, MAX_PROGRESS);
                setProgress(progressbar2, MAX_PROGRESS);
                setProgress(progressbar3, progress);
                progressbar4.setProgress(0);
                progressbar5.setProgress(0);
                break;
            case 3:
                setProgress(progressbar1, MAX_PROGRESS);
                setProgress(progressbar2, MAX_PROGRESS);
                setProgress(progressbar3, MAX_PROGRESS);
                setProgress(progressbar4, progress);
                progressbar5.setProgress(0);
                break;
            case 4:
                setProgress(progressbar1, MAX_PROGRESS);
                setProgress(progressbar2, MAX_PROGRESS);
                setProgress(progressbar3, MAX_PROGRESS);
                setProgress(progressbar4, MAX_PROGRESS);
                setProgress(progressbar5, progress);
                break;
            case 5:
                setProgress(progressbar1, MAX_PROGRESS);
                setProgress(progressbar2, MAX_PROGRESS);
                setProgress(progressbar3, MAX_PROGRESS);
                setProgress(progressbar4, MAX_PROGRESS);
                setProgress(progressbar5, MAX_PROGRESS);
                break;
        }

        if (currentProgress <= 60) {
            setColor(colorFirst);
            if (currentProgress == 60)
                onInterval(1);
        }
        if (currentProgress > 60 && currentProgress <= 120) {
            setColor(colorSecond);
            if (currentProgress == 120)
                onInterval(2);
        }
        if (currentProgress > 120 && currentProgress <= 180) {
            setColor(colorSecond);
            if (currentProgress == 180)
                onInterval(3);
        }
        if (currentProgress > 180 && currentProgress <= 240) {
            setColor(colorThird);
            if (currentProgress == 240)
                onInterval(4);
        }
        if (currentProgress > 240 && currentProgress <= totalProgress + margin) {
            setColor(colorThird);
            if (currentProgress == totalProgress)
                onInterval(5);
        }
        if (currentProgress == totalProgress + margin)
            onTimeFinished();
    }

    private void setProgress(RoundCornerProgressBar progressbar, int progress) {
        progressbar.setProgress(progress);
    }

    private void onInterval(int i) {
        if (listener != null)
            listener.onIntervalCompleted(i);
    }

    private void onTimeFinished() {
        if (listener != null)
            listener.onTimeFinished();
    }

    private void setColor(int colorGreen) {
        progressbar1.setProgressColor(colorGreen);
        progressbar2.setProgressColor(colorGreen);
        progressbar3.setProgressColor(colorGreen);
        progressbar4.setProgressColor(colorGreen);
        progressbar5.setProgressColor(colorGreen);
    }

    public void setTimeOverListener(TimeOverListener listener) {
        this.listener = listener;
    }

    public interface TimeOverListener {
        void onIntervalCompleted(int interval);

        void onTimeFinished();
    }
}
