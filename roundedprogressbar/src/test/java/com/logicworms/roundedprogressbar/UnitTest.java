package com.logicworms.roundedprogressbar;

import com.logicworms.roundedprogressbar.utils.TimeUtils;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    int time = 3800;

    @Test
    public void timeTest() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (time > -1200) {
                    time -= 60;
                    startTest();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startTest() {
        String DATE = "18/Apr/2019";
        String TIME = "06:30 PM";
        int targetTime = TimeUtils.getDifferenceFromTargetTime(DATE, TIME);
        println("Difference : " + targetTime);
        String formatted = TimeUtils.formatTime(targetTime);
        println(formatted);
    }

    private void println(String difference) {
        System.out.println(difference);
    }
}