package com.example.reflexgame;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Button button1, button2;
    public RelativeLayout relativeLayout;

    // runnable function
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // set the background on the screen
            relativeLayout.setBackgroundResource(R.color.green);

            // get the system time in milli second
            // when the screen background is set
            final long time = System.currentTimeMillis();

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get the system time in milli second
                    // when the stop button is clicked
                    long time1 = System.currentTimeMillis();

                    // display reflex time in toast message
                    Toast.makeText(getApplicationContext(), "Your reflexes takes " + (time1 - time) + " time to work", Toast.LENGTH_LONG).show();

                    // remove the background again
                    relativeLayout.setBackgroundResource(0);
                }
            });
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rlVar1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        relativeLayout = findViewById(R.id.rlVar1);
        button1 = findViewById(R.id.btVar1);
        button2 = findViewById(R.id.btVar2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // generate a random number from 1-10
                Random random = new Random();
                int num = random.nextInt(10);

                // call the runnable function after
                // a post delay of num seconds
                Handler handler = new Handler();
                handler.postDelayed(runnable, num * 1000);
            }
        });

    }
}