package com.dlshare.dldebuglog;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dlshare.debuglog.DlLog;

//通过DlLog注解打印日志
@DlLog
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //正常操作
        testHugo("jake", "wharson");
        //UI耗时操作
        testDl("dl", "share");
        //子线程执行耗时操作
        new Thread() {
            @Override
            public void run() {
                super.run();
                testDl("张", "贝贝");
            }
        }.start();
    }

    //提供的方法，默认都在主线程执行
    private String testDl(String dl, String share) {
        SystemClock.sleep(580);
        return dl + share;
    }

    private String testHugo(String jake, String wharson) {
        SystemClock.sleep(28);
        return jake + wharson;
    }
}
