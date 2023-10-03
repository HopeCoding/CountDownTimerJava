package com.hopecoding.countdowntimerjava;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hopecoding.countdowntimerjava.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {


    
    private boolean isRunning;

    private CountDownTimer countDownTimer;

    private static long timeLeft=0;

    private boolean isReset;

    private static long data;

    private boolean isResume;

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        binding.btnReset.setEnabled(false);

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.dataText.getText().toString().equals("") && !isRunning && !isResume && !isReset){
                    Toast.makeText(getApplicationContext(),"You did not enter data",Toast.LENGTH_LONG).show();
                }else
                {
                    if(isRunning){
                        pauseTime();
                    }else if(isResume){
                        resumeTime();
                    }else if(isReset && binding.dataText.getText().toString().equals("")){
                        start2Time();

                    }else{
                        startTime();
                    }

                }
            }
        });

        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTime();
            }
        });




    }


    private void startTime(){
        data=Long.parseLong(binding.dataText.getText().toString());
        timeLeft=data;
        countDownTimer=new CountDownTimer(timeLeft*1000,1000) {
            @Override
            public void onTick(long l) {
                    timeLeft=l;
                    updateText();
            }


            @Override
            public void onFinish() {
                isRunning=false;
                isReset=false;
                isResume=false;
                binding.btnStart.setText("Start");
                binding.btnStart.setEnabled(true);
                binding.btnReset.setEnabled(false);
                binding.timeTxt.setText("Count is over :)");

            }
        }.start();

        binding.dataText.setText("");
        isRunning=true;
        isReset=false;
        isResume=false;
        binding.btnStart.setText("Pause");
        binding.btnReset.setEnabled(false);

    }


    private void pauseTime(){
        countDownTimer.cancel();
        isRunning=false;
        isResume=true;
        isReset=false;
        binding.btnStart.setText("Resume");
        binding.btnReset.setEnabled(true);

    }


    private void resetTime(){
        isResume=false;
        isRunning=false;
        isReset=true;
        timeLeft=data;
        binding.timeTxt.setText("Remaining Second:"+data);
        binding.btnReset.setEnabled(false);
        binding.btnStart.setEnabled(true);
        binding.btnStart.setText("Start");


    }


    private void updateText(){
        int second=(int) (timeLeft/1000);

        binding.timeTxt.setText("Remaining Second:"+second);
    }


    private void resumeTime(){
        countDownTimer=new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long l) {
                timeLeft=l;
                updateText();
            }


            @Override
            public void onFinish() {
                isRunning=false;
                isReset=false;
                isResume=false;
                binding.btnStart.setText("Start");
                binding.btnStart.setEnabled(true);
                binding.btnReset.setEnabled(true);
                binding.timeTxt.setText("Count is over :)");

            }
        }.start();

        isRunning=true;
        binding.btnStart.setText("Pause");
        binding.btnReset.setEnabled(false);
    }


    private void start2Time(){
        countDownTimer=new CountDownTimer(timeLeft*1000,1000) {
            @Override
            public void onTick(long l) {
                timeLeft=l;
                updateText();
            }

            @Override
            public void onFinish() {
                isRunning=false;
                isReset=false;
                isResume=false;
                binding.btnStart.setText("Start");
                binding.btnStart.setEnabled(true);
                binding.btnReset.setEnabled(false);
                binding.timeTxt.setText("Count is over :)");

            }
        }.start();

        binding.dataText.setText("");
        isRunning=true;
        isReset=false;
        isResume=false;
        binding.btnStart.setText("Pause");
        binding.btnReset.setEnabled(false);


    }
}
