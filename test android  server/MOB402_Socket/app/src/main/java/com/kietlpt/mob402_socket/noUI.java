package com.kietlpt.mob402_socket;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.kiennv.androidbaove.MainActivity;


public class noUI {

    public Context context;

    public noUI(Context context) {
        this.context = context;
    }

    // toast non-ui
    public void toast(final String text) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }
        });
    }


}
