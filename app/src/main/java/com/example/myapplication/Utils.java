package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void handleException(Context context, Exception ex) {
        Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
    }
}
