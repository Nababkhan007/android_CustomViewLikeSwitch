package com.khan.customviewlikeswitch;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayout;
    private TextView textView, clockTv;
    private Button controlBtn;
    private boolean helloWorldVisible = true;
    private static final long TRANSITION_DURATION = 1000;
    private static final long CLOCK_HANDLER_DELAY_TIME = 1000;
    private static final long CLOCK_RUNNABLE_DELAY_TIME = 10;
    private static final long HIDE_VIEW_DELAY_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        showClock();

        onClick();
    }

    private void showClock() {
        final Handler clockHandler = new Handler(getMainLooper());
        clockHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.US);
                String currentTime = format.format(new Date());
                clockTv.setText(currentTime);
                clockHandler.postDelayed(this, CLOCK_HANDLER_DELAY_TIME);
            }
        }, CLOCK_RUNNABLE_DELAY_TIME);
    }

    private void onClick() {
        controlBtn.setOnClickListener(v -> {
            setDisplayTransition();

            showHideWidgets();

            hideView();
        });
    }

    private void showHideWidgets() {
        if (helloWorldVisible) {
            controlBtn.setText(getString(R.string.hide));

        } else {
            controlBtn.setText(getString(R.string.show));
        }

        if (helloWorldVisible) {
            textView.setVisibility(View.VISIBLE);

        } else {
            textView.setVisibility(View.GONE);
        }

        helloWorldVisible = !helloWorldVisible;
    }

    private void setDisplayTransition() {
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.disableTransitionType(LayoutTransition.CHANGING);
        layoutTransition.setDuration(TRANSITION_DURATION);
        constraintLayout.setLayoutTransition(layoutTransition);
    }

    private void hideView() {
        final Handler hideViewHandler = new Handler();
        final Runnable runnable = this::showHideWidgets;
        hideViewHandler.removeCallbacks(runnable);
        hideViewHandler.postDelayed(runnable, HIDE_VIEW_DELAY_TIME);
    }

    private void init() {
        constraintLayout = findViewById(R.id.container);
        textView = findViewById(R.id.text_view);
        clockTv = findViewById(R.id.clock_tv);
        controlBtn = findViewById(R.id.control_btn);
    }
}