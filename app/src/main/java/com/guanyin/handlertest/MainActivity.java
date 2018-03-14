package com.guanyin.handlertest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    TextView txt1;
    public final static int UPDATE_TXT=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=findViewById(R.id.change_btn);
        txt1=findViewById(R.id.change_txt);

        /*
        開啟子線程無法更新主線程UI 會導致錯誤
         */
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myThread myThread=new myThread();
//                new Thread(myThread).start();
//            }
//        });

        final Handler handler=new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what){
                    case UPDATE_TXT:
                        txt1.setText("sqqq");
                        break;
                    default:
                        break;

                }

            }
        };

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=UPDATE_TXT;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });






    }

    class myThread implements Runnable{

        @Override
        public void run() {

            txt1.setText("sss");
        }
    }
}
