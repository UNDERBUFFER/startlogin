package com.example.fuckinglogin;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class SharedData {
    public SharedPreferences preferences;

    public SharedData(AppCompatActivity app) {
        this.preferences = app.getSharedPreferences("Login", Context.MODE_PRIVATE);
    }

    public String getCode() {
        return this.preferences.getString("code", "");
    }

    public String getName() {
        return this.preferences.getString("name", "");
    }

    public boolean getIsLoggedIn() {
        return this.preferences.getBoolean("loggedIn", false);
    }

    public void setData(String name, String code) {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putString("name", name);
        editor.putString("code", code);
        editor.putBoolean("loggedIn", true);
        editor.apply();
    }
}
