package com.example.webfluxdemo.utill;

import java.io.FileNotFoundException;

public class SleepUtil {
    public static void sleepSeconds(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
