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
import java.util.Timer;
import java.util.TimerTask;

public class CreateCode extends AppCompatActivity {
    private ArrayList<ImageView> images = new ArrayList();
    private String password;
    private TextView hello_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_code);

        this.hello_msg = findViewById(R.id.hello_msg);

        this.addImages();
        this.swapNames();
        this.password = "";
    }

    private void swapNames() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String welcome = this.hello_msg.getText().toString();

        Resources res = getResources();
        this.hello_msg.setText(
                welcome.replaceAll(res.getString(R.string.default_name), name)
        );
    }

    private void addImages() {
        this.images.add( findViewById(R.id.imageView1) );
        this.images.add( findViewById(R.id.imageView2) );
        this.images.add( findViewById(R.id.imageView3) );
        this.images.add( findViewById(R.id.imageView4) );
    }

    private void showCreatePassword() {
        CreateCode self = this;
        int seconds = 2;

        for (ImageView iv : self.images) {
            Timer timer = new Timer();
            int finalSeconds = seconds;
            TimerTask task = new TimerTask() {
                int count = 0;

                @Override
                public void run() {
                    count++;
                    if (count == finalSeconds) {
                        timer.cancel();
                        iv.setImageResource(R.drawable.circle_fill_green);
                    }
                }
            };
            seconds++;
            timer.scheduleAtFixedRate(task, 0, 500);
        }
    }

    public void onButtonClick(View view) {
//        TODO
        if (this.password.length() == 4) {
            return;
        }

        Button button = (Button) view;
        this.password += button.getText().toString();

        ImageView iv = this.images.get(this.password.length() - 1);
        iv.setImageResource(R.drawable.circle_fill);

        if (this.password.length() == 4) {
            this.showCreatePassword();
        }
    }
}