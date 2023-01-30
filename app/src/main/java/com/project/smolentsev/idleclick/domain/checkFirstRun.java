package com.project.smolentsev.idleclick.domain;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageButton;

import com.project.smolentsev.idleclick.BuildConfig;
import com.project.smolentsev.idleclick.R;

public class checkFirstRun {
    Dialog dialog;
    private void openTutorial1(Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_tutorial_1);
        dialog.setCancelable(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        ImageButton cancel_del = dialog.findViewById(R.id.btn_cancel);
        cancel_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                openTutorial2(context);

            }
        });
        dialog.show();
    }

    private void openTutorial2(Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_tutorial_2);
        dialog.setCancelable(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        ImageButton cancel_del = dialog.findViewById(R.id.btn_cancel);
        cancel_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    //Проверка на первую установку приложения
    public void checkFirstRun(Context context) {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            // This is just a normal run
            return;

        } else if (savedVersionCode == DOESNT_EXIST) {
            openTutorial1(context);
            // TODO This is a new install (or the user cleared the shared preferences)

        } else if (currentVersionCode > savedVersionCode) {

            // TODO This is an upgrade
        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }

}
