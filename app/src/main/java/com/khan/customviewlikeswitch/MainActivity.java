package com.khan.customviewlikeswitch;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayout;
    private TextView textView;
    private Button controlBtn;
    private boolean helloWorldVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        onClick();
    }

    private void onClick() {
        controlBtn.setOnClickListener(v -> {
            LayoutTransition lt = new LayoutTransition();
            lt.disableTransitionType(LayoutTransition.CHANGING);
            lt.setDuration(1000);
            constraintLayout.setLayoutTransition(lt);

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
        });
    }

    private void init() {
        constraintLayout = findViewById(R.id.container);
        textView = findViewById(R.id.text_view);
        controlBtn = findViewById(R.id.control_btn);
    }
}