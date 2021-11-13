package com.cookandorid.graduationhelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MajorInfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_info);

        TextView tvSiteUrl, tvTelNumber;
        tvSiteUrl = (TextView)findViewById(R.id.tvSiteUrl);
        tvTelNumber = (TextView)findViewById(R.id.tvTelNumber);

        Intent inIntent = getIntent();
        Bundle datas = inIntent.getExtras();
        String siteUrl = datas.getString("siteUrl");
        String telNumber = datas.getString("telNumber");

        tvSiteUrl.setText(siteUrl);
        tvTelNumber.setText(telNumber);

        Log.d("-- Subject is ", siteUrl);
        Log.d("-- telNumber is ", telNumber);
    }
}
