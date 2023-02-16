package com.example.movies;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class language extends AppCompatActivity {

    Button en_btn;
    Button ar_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        en_btn = (Button) findViewById(R.id.btn_english_id);
        ar_btn = (Button) findViewById(R.id.btn_arabic_id);

        en_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");
            }
        });

        ar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("ar");
            }
        });

    }

    @SuppressWarnings("deprecation")
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        Configuration conf = getResources().getConfiguration();
        conf.locale = myLocale;
        getResources().updateConfiguration(conf, dm);
        Intent refresh = new Intent(this,first_Activity.class);
        startActivity(refresh);
    }

/*         "en" = English
            "hi" =Hindi
            "fr" =French
            "it" =Italian
            "de" =German
            "es" =Spanish
            "ja" =Japanese
            "ko" =Korean
            "nl" =Dutch
            "pt" =Portuguese
            "ru" =Russian
            "zh" =Chinese
            "ar" = arabic

   */
}