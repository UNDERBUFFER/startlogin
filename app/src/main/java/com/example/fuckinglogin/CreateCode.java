package com.example.fuckinglogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CreateCode extends BaseCode {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_code);

        this.sharedData = new SharedData(this);

        this.hello_msg = findViewById(R.id.hello_msg);

        this.addImages();

        Intent intent = getIntent();
        this.username = intent.getStringExtra("name");

        this.swapNames(this.username);
        this.password = "";
    }


    protected void showCreatePassword() {
        CreateCode self = this;
        int seconds = 2;

        for (ImageView iv : self.images) {
            this.delayFill(iv, R.drawable.circle_fill_green, seconds++);
        }

        this.delayFill(null, R.drawable.circle_fill, seconds++);
        this.delayFill(null, R.drawable.circle_fill_green, seconds++);

        CustomTimer.createTimer(seconds++, 500, () -> {
            this.sharedData.setData(this.username, this.password);
            Intent intent = new Intent(CreateCode.this, MainScreen.class);
            startActivity(intent);
        });
    }
}