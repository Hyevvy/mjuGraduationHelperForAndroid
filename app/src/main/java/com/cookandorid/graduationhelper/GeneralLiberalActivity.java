package com.cookandorid.graduationhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GeneralLiberalActivity extends AppCompatActivity {
    TextView tvGeneral;
    Button btnReturnCategory, btnCheckMajorInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generalliberal);

        Intent inIntent = getIntent();
        Bundle datas = inIntent.getExtras();
        String major = datas.getString("major");
        String studentNum = datas.getString("studentNum");
        String siteUrl = datas.getString("siteUrl");
        String telNumber = datas.getString("telNumber");
        Boolean isAbeek = datas.getBoolean("isAbeek");

        tvGeneral = (TextView)findViewById(R.id.tvGeneral);
        Integer studentNumInt = Integer.parseInt(studentNum);
        if(studentNumInt < 15){
            //15학번 이전의 일반 교양은 졸업 조건과 관계가 없음
            tvGeneral.setText("졸업조건과 관계 없는 ");
        }
        else{
            if(isAbeek) tvGeneral.setText("0 ");
            else tvGeneral.setText("10 ");
        }




        btnReturnCategory = (Button)findViewById(R.id.btnReturnCategory);

        btnReturnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCheckMajorInfo = (Button)findViewById(R.id.btnCheckMajorInfo);
        btnCheckMajorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MajorInfoActivity.class);
                intent.putExtra("major", major);
                intent.putExtra("siteUrl",siteUrl);
                intent.putExtra("telNumber", telNumber);
                startActivity(intent);
            }
        });


    }
}
