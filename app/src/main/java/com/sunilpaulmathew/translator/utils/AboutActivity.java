/*
 * Copyright (C) 2020-2021 sunilpaulmathew <sunil.kde@gmail.com>
 *
 * This file is part of The Translator, An application to help translate android apps.
 *
 */

package com.sunilpaulmathew.translator.utils;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.sunilpaulmathew.translator.BuildConfig;
import com.sunilpaulmathew.translator.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on September 24, 2020
 */

public class AboutActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        AppCompatImageView mDeveloper = findViewById(R.id.developer);
        AppCompatTextView mAppName = findViewById(R.id.app_title);
        AppCompatTextView mForegroundText = findViewById(R.id.foreground_text);
        AppCompatTextView mCancel = findViewById(R.id.cancel_button);
        mDeveloper.setOnClickListener(v -> {
            if (Utils.isNetworkAvailable(this)) {
                Utils.launchURL("https://github.com/sunilpaulmathew", this);
            } else {
                Utils.showSnackbar(mDeveloper, getString(R.string.no_internet));
            }
        });
        mCancel.setOnClickListener(v -> {
            onBackPressed();
        });

        mAppName.setText(getString(R.string.app_name) + " v" + BuildConfig.VERSION_NAME);
        String change_log = null;
        try {
            change_log = new JSONObject(Objects.requireNonNull(Utils.readAssetFile(
                    this, "changelogs.json"))).getString("releaseNotes");
        } catch (JSONException ignored) {
        }
        mForegroundText.setText(change_log);
        mForegroundText.setVisibility(View.VISIBLE);
        mCancel.setVisibility(View.VISIBLE);
    }

}