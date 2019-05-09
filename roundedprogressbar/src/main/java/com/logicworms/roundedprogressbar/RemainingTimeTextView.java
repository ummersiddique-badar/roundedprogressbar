package com.logicworms.roundedprogressbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.logicworms.roundedprogressbar.utils.TimeUtils;

public class RemainingTimeTextView extends TextView {

    private static final long DELAY_MILLIS = 1000;
    private static final int DEFAULT_LATE_TIME = 300;
    private static final String TAG = RemainingTimeTextView.class.getSimpleName();
    Handler handler;
    private Context context;
    private int colorFirst;
    private int colorSecond;
    private int targetTime;
    private int maxLateTime = DEFAULT_LATE_TIME;
    private int currentTime;
    private OnMinuteChangeListener minuteListener;
    private String textTooLate;
    private Runnable timer = new Runnable() {
        @Override
        public void run() {
            currentTime++;
            int time = targetTime - currentTime;

            String text = TimeUtils.formatTime(time);
            setText(text);
            if (currentTime <= (targetTime + maxLateTime)) {
                handler.postDelayed(this, DELAY_MILLIS);
                if (minuteListener != null)
                    minuteListener.onMinuteChanged(time);
            } else {
                setText(textTooLate);
            }
            if (time > 5 * 60) {
                Log.i(TAG, this + "Time : " + time);
                Log.i(TAG, "Color  : first color");
                setTextColor(colorFirst);
            } else {
                setTextColor(colorSecond);
                Log.i(TAG, this + "Time : " + time);
                Log.i(TAG, "Color  : Second color");
            }
        }
    };

    public RemainingTimeTextView(Context context) {
        this(context, null);
    }

    public RemainingTimeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RemainingTimeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        init(attrs);
    }

    /**
     * @param targetTime target time in sec
     */
    public void setTargetTime(int targetTime) {
        this.targetTime = targetTime;
        currentTime = 0;
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

    /**
     * @param maxLateTime time in sec
     */
    public void setMaxLateTime(int maxLateTime) {
        this.maxLateTime = maxLateTime;
    }

    private void init(AttributeSet attrs) {
        handler = new Handler();
        Resources res = context.getResources();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RemainingTimeTextView);

        colorFirst = typedArray.getColor(R.styleable.RemainingTimeTextView_firstColor, res.getColor(R.color.colorYellowDark));
        colorSecond = typedArray.getColor(R.styleable.RemainingTimeTextView_secondColor, res.getColor(R.color.colorRed));
        textTooLate = typedArray.getString(R.styleable.RemainingTimeTextView_tooLateText);
        if (textTooLate == null)
            textTooLate = context.getString(R.string.too_much_late);

        typedArray.recycle();
    }

    public void setMinuteChangeListener(OnMinuteChangeListener minuteListener) {
        this.minuteListener = minuteListener;
    }

    public interface OnMinuteChangeListener {
        void onMinuteChanged(int minute);
    }
}
