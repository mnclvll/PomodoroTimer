package com.example.pomodorotimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class PomodoroTimer extends AppCompatActivity {
    //sets time for work, break and long break
    private static final long START_WORK_TIME_MILLIS = 1500000;
    private static final long START_BREAK_TIME_MILLIS = 300000;
    private static final long START_LONG_BREAK_MILLIS = 1200000;

    //variable declarations for work timer
    private TextView txtWorkTimer;
    private Button btnStartWork, btnPauseWork, btnResetWork;

    //variable declarations for break timer
    private TextView txtBreakTimer;
    private Button btnStartBreak, btnPauseBreak, btnResetBreak;

    //variable declarations for long break timer
    private TextView txtLongBreakTimer;
    private Button btnStartLongBreak, btnPauseLongBreak, btnResetLongBreak;

    private CountDownTimer mCountdownTimer;
    private boolean mTimerRunning;
    private long mEndTime;
    private long mWorkTimeLeftInMilli = START_WORK_TIME_MILLIS;
    private long mBreakTimeLeftInMilli = START_BREAK_TIME_MILLIS;
    private long mLongBreakTimeLeftInMilli = START_LONG_BREAK_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.PomodoroTimer);

        //work timer objects
        txtWorkTimer = findViewById(R.id.txtWorkTimer);
        btnStartWork = findViewById(R.id.btnStartWork);
        btnPauseWork = findViewById(R.id.btnPauseWork);
        btnResetWork = findViewById(R.id.btnResetWork);

        //Work Timer
        btnStartWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimerRunning) {
                    PauseWorkTimer();
                }
                else {
                    StartWorkTimer();
                    btnStartWork.setText("Start");
                }
            }
        });

        btnPauseWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimerRunning) {
                    PauseWorkTimer();
                    btnStartWork.setText("Resume");
                }
            }
        });

        btnResetWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ResetWorkTimer();
                btnStartWork.setText("Start");

            }
        });

        //break timer objects
        txtBreakTimer = findViewById(R.id.txtBreakTimer);
        btnStartBreak = findViewById(R.id.btnStartBreak);
        btnPauseBreak = findViewById(R.id.btnPauseBreak);
        btnResetBreak = findViewById(R.id.btnResetBreak);

        //Break Timer
        btnStartBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimerRunning) {
                    PauseBreakTimer();
                }
                else{
                    StartBreakTimer();
                    btnStartBreak.setText("Start");
                }
            }
        });

        btnPauseBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimerRunning) {
                    PauseBreakTimer();
                    btnStartBreak.setText("Resume");
                }
            }
        });

        btnResetBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ResetBreakTimer();
                btnStartBreak.setText("Start");

            }
        });

        //long break timer objects
        txtLongBreakTimer = findViewById(R.id.txtLongBreakTimer);
        btnStartLongBreak = findViewById(R.id.btnStartLongBreak);
        btnPauseLongBreak = findViewById(R.id.btnPauseLongBreak);
        btnResetLongBreak = findViewById(R.id.btnResetLongBreak);

        //Long Break Timer
        btnStartLongBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning) {
                    PauseLongBreakTimer();
                }
                else {
                    StartLongBreakTimer();
                    btnStartLongBreak.setText("Start");
                }

            }
        });

        btnPauseLongBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning) {
                    PauseLongBreakTimer();
                    btnStartLongBreak.setText("Resume");
                }

            }
        });

        btnResetLongBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ResetLongBreakTimer();
                btnStartLongBreak.setText("Start");

            }
        });
    }

    //work timer
    private void StartWorkTimer() {
        mEndTime = System.currentTimeMillis() + mWorkTimeLeftInMilli;

        mCountdownTimer = new CountDownTimer(mWorkTimeLeftInMilli, 1000) {
            @Override
            public void onTick(long l) {
                mWorkTimeLeftInMilli = l;
                updateStartCountdownText();
            }

            @Override
            public void onFinish() {

                mTimerRunning = false;
                btnResetWork.setVisibility(View.VISIBLE);
            }

        }.start();

        mTimerRunning = true;
        btnResetWork.setVisibility(View.INVISIBLE);
    }

    private void PauseWorkTimer() {
        mCountdownTimer.cancel();
        mTimerRunning=false;
        btnResetWork.setVisibility(View.VISIBLE);
    }

    private void ResetWorkTimer() {
        mWorkTimeLeftInMilli = START_WORK_TIME_MILLIS;
        updateStartCountdownText();
    }

    private void updateStartCountdownText() {
        int minutes = (int) (mWorkTimeLeftInMilli / 1000) / 60;
        int seconds = (int) (mWorkTimeLeftInMilli /1000) % 60;

        String WorkTimeLeftFormat = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        txtWorkTimer.setText(WorkTimeLeftFormat);
    }

    //break timer
    private void StartBreakTimer() {
        mEndTime = System.currentTimeMillis() + mBreakTimeLeftInMilli;

        mCountdownTimer = new CountDownTimer(mBreakTimeLeftInMilli, 1000) {
            @Override
            public void onTick(long l) {
                mBreakTimeLeftInMilli = l;
                updateBreakCountdownText();
            }

            @Override
            public void onFinish() {

                mTimerRunning = false;
                btnResetBreak.setVisibility(View.VISIBLE);

            }
        }.start();

        mTimerRunning = true;
        btnResetBreak.setVisibility(View.INVISIBLE);
    }

    private void PauseBreakTimer() {
        mCountdownTimer.cancel();
        mTimerRunning=false;
        btnResetBreak.setVisibility(View.VISIBLE);
    }

    private void ResetBreakTimer() {
        mBreakTimeLeftInMilli = START_BREAK_TIME_MILLIS;
        updateBreakCountdownText();
    }

    private void updateBreakCountdownText() {
        int minutes = (int) (mBreakTimeLeftInMilli / 1000) / 60;
        int seconds = (int) (mBreakTimeLeftInMilli /1000) % 60;

        String BreakTimeLeftFormat = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        txtBreakTimer.setText(BreakTimeLeftFormat);
    }

    //long break timer
    private void StartLongBreakTimer() {
        mEndTime = System.currentTimeMillis() + mLongBreakTimeLeftInMilli;

        mCountdownTimer = new CountDownTimer(mLongBreakTimeLeftInMilli, 1000) {
            @Override
            public void onTick(long l) {
                mLongBreakTimeLeftInMilli = l;
                updateLongBreakCountdownText();
            }

            @Override
            public void onFinish() {

                mTimerRunning = false;
                btnResetLongBreak.setVisibility(View.VISIBLE);

            }
        }.start();

        mTimerRunning = true;
        btnResetLongBreak.setVisibility(View.INVISIBLE);
    }

    private void PauseLongBreakTimer() {
        mCountdownTimer.cancel();
        mTimerRunning = false;
        btnResetLongBreak.setVisibility(View.VISIBLE);
    }

    private void ResetLongBreakTimer() {
        mLongBreakTimeLeftInMilli = START_LONG_BREAK_MILLIS;
        updateLongBreakCountdownText();
    }

    private void updateLongBreakCountdownText() {
        int minutes = (int) (mLongBreakTimeLeftInMilli / 1000) /60;
        int seconds = (int) (mLongBreakTimeLeftInMilli / 1000) % 60;

        String LongBreakTimeLeftFormat = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        txtLongBreakTimer.setText(LongBreakTimeLeftFormat);
    }
}