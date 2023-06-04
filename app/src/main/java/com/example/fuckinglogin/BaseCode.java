package com.example.fuckinglogin;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

abstract public class BaseCode extends AppCompatActivity {
    protected ArrayList<ImageView> images = new ArrayList();
    protected String password;
    protected String username;
    protected TextView hello_msg;
    protected SharedData sharedData;

    protected void swapNames(String username) {
        String welcome = this.hello_msg.getText().toString();

        Resources res = getResources();
        this.hello_msg.setText(
                welcome.replaceAll(res.getString(R.string.default_name), username)
        );
    }

    protected void addImages() {
        this.images.add( findViewById(R.id.imageView1) );
        this.images.add( findViewById(R.id.imageView2) );
        this.images.add( findViewById(R.id.imageView3) );
        this.images.add( findViewById(R.id.imageView4) );
    }

    protected void delayFill(ImageView iv, int image, int delay) {
        ArrayList<ImageView> data = this.images;
        if (iv != null) {
            data = new ArrayList();
            data.add(iv);
        }

        for (ImageView siv : data) {
            CustomTimer.createTimer(delay, 500, () -> {
                siv.setImageResource(image);
            });
        }
    }

    abstract protected void showCreatePassword();

    public void onButtonClick(View view) {
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
