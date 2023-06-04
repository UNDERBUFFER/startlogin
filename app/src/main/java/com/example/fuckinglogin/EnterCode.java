package com.example.fuckinglogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EnterCode extends BaseCode {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);

        this.sharedData = new SharedData(this);

        this.hello_msg = findViewById(R.id.hello_msg);

        this.addImages();

        this.username = this.sharedData.getName();
        String welcome = this.hello_msg.getText().toString();

        this.swapNames(this.username);
        this.password = "";
    }

    private boolean equalPassword(String password) {
        return this.sharedData.getCode().equals(password);
    }

    protected void showCreatePassword() {
        EnterCode self = this;
        int seconds = 2;

        boolean result = this.equalPassword(this.password);

        if (result) {
            this.delayFill(null, R.drawable.circle_fill_green, seconds++);
            this.delayFill(null, R.drawable.circle_fill, seconds++);

            CustomTimer.createTimer(seconds++, 500, () -> {
                this.sharedData.setData(this.username, this.password);
                Intent intent = new Intent(EnterCode.this, MainScreen.class);
                startActivity(intent);
            });
        }

        else {
            this.delayFill(null, R.drawable.circle_fill_red, seconds++);
            this.delayFill(null, R.drawable.circle_empty, seconds++);
            this.password = "";
        }
    }
}