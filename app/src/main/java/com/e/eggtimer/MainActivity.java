package com.e.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar s1;
    TextView t1;
    boolean a = false;
    Button b1;
    CountDownTimer c1;
    public void calle(int secondsLeft) {

        int m1 = (int) (secondsLeft / 60);
        int s1 = secondsLeft - (m1 * 60);

        String s2 = Integer.toString(s1);
        String m2 = Integer.toString(m1);

        if (s1 <= 9) { s2 = "0" + s2; }
        if (m1 <= 9) { m2 = "0" + m2; }
        t1.setText(m2 + ":" + s2);
    }

    public void reset()
    {
        t1.setText("01:00");
        s1.setProgress(60);
        c1.cancel();
        b1.setText("Start");
        s1.setEnabled(true);
        a=false;
    }
    public void click(View view)
    {
        if(a == false) {
            a = true;
            s1.setEnabled(false);
            b1.setText("STOP");
            c1 = new CountDownTimer(s1.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    calle((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    reset();
                    t1.setText("01:00");
                    MediaPlayer m3 = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    m3.start();
                }
            }.start();
        }
        else
        {
            reset();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s1 = (SeekBar) findViewById(R.id.s1);
        t1 = (TextView) findViewById(R.id.t1);
        b1 = (Button) findViewById(R.id.b1);
        s1.setMax(600);
        s1.setProgress(60);
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                calle(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
