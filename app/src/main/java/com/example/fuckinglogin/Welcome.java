package com.example.fuckinglogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Welcome extends AppCompatActivity {
    public LinearLayout ll;
    public EditText inputName;
    public ImageButton arrow;
    public SharedData sharedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        this.sharedData = new SharedData(this);
        this.redirecLogginedUser();

        this.ll = findViewById(R.id.ll);
        this.inputName = findViewById(R.id.inputName);
        this.arrow = findViewById(R.id.arrow);

        this.arrow.setEnabled(false);
        this.inputName.setOnTouchListener(this.setupTouchEventListener());
        this.inputName.addTextChangedListener(this.addTextChangedListener());
        this.arrow.setOnClickListener(this.onClickButton());
    }

    public View.OnTouchListener setupTouchEventListener() {
        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) ll.getLayoutParams();
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                lp.bottomMargin = 800;
                return view.onTouchEvent(motionEvent);
            }
        };
    }

    public TextWatcher addTextChangedListener() {
        Welcome self = this;
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                Drawable image = getDrawable(R.drawable.black_arrow);
                self.arrow.setEnabled(false);

                if (s.length() > 0) {
                    self.arrow.setEnabled(true);
                    image = getDrawable(R.drawable.arrow);
                }
                self.arrow.setBackground(image);
            }
        };
    }

    public View.OnClickListener onClickButton() {
        Welcome self = this;
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, CreateCode.class);
                intent.putExtra("name", self.inputName.getText().toString());
                startActivity(intent);
            }
        };
    }

    private void redirecLogginedUser() {
        if (this.sharedData.getIsLoggedIn()) {
            Intent intent = new Intent(Welcome.this, EnterCode.class);
            startActivity(intent);
        }
        Log.i("tag", "No Login");
    }
}