package com.syss.shree.sensors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    Intent i=new Intent(MainActivity.this,fingerPrint.class);
                    startActivity(i);
                }
            }

        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
