package com.logicworms.roundedprogressbarexample;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    private int currentProgress = 320;

    @Test
    public void progressTest() {
        int completedBars = currentProgress;
        int progress = completedBars % 60;
        completedBars = completedBars / 60;
//        println("Progress : " + progress);
//        println("Completed progress : " + completedBars);
        switch (completedBars) {
            case 0:
                println("Set Progress 1 : " + progress);
                break;
            case 1:
                println("Progress 1 completed : " + 60);
                println("Set Progress 2 : " + progress);
                break;
            case 2:
                println("Progress 1 completed : " + 60);
                println("Progress 2 completed : " + 60);
                println("Set Progress 3 : " + progress);
                break;
            case 3:
                println("Progress 1 completed : " + 60);
                println("Progress 2 completed : " + 60);
                println("Progress 3 completed : " + 60);
                println("Set Progress 4 : " + progress);
                break;
            case 4:
                println("Progress 1 completed : " + 60);
                println("Progress 2 completed : " + 60);
                println("Progress 3 completed : " + 60);
                println("Progress 4 completed : " + 60);
                println("Set Progress 5 : " + progress);
                break;
            case 5:
                println("Progress 1 completed : " + 60);
                println("Progress 2 completed : " + 60);
                println("Progress 3 completed : " + 60);
                println("Progress 4 completed : " + 60);
                println("Progress 5 completed : " + 60);
                println("Margin : " + progress);
                break;
        }
    }

    private void println(String message) {
        System.out.println(message);
    }
}