package com.example.fuckinglogin;

import java.util.Timer;
import java.util.TimerTask;

public class CustomTimer {
    static public void createTimer(int seconds, int period, TimerCallBack func) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int count = 0;
            @Override
            public void run() {
                count++;
                if (count == seconds) {
                    timer.cancel();
                    func.myMethod();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, period);
    }
}
