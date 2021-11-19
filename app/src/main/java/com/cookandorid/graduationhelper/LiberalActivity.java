package com.cookandorid.graduationhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LiberalActivity extends AppCompatActivity {
//핵심교양 선택시 나타나는 화면
    ImageView imgViewLiberal;
    Button btnReturnCategory, btnCheckMajorInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liberal);

        Intent inIntent = getIntent();
        Bundle datas = inIntent.getExtras();
        String major = datas.getString("major");
        String studentNum = datas.getString("studentNum");
        String siteUrl = datas.getString("siteUrl");
        String telNumber = datas.getString("telNumber");
        int studentNumInt = Integer.parseInt(studentNum);
        imgViewLiberal = (ImageView)findViewById(R.id.imgViewLiberal);

        //학번 별로 다른 공통 교양 이미지 출력
        if(studentNumInt >= 18) {
            imgViewLiberal.setImageResource(R.drawable.liberal_aftereigtheen);
        }
        else if(studentNumInt>=15 && studentNumInt<=17 ){
            imgViewLiberal.setImageResource(R.drawable.liberal_fifteen_to_seventeen);
        }
        else if(studentNumInt>=9 && studentNumInt<=14 ){
            imgViewLiberal.setImageResource(R.drawable.liberal_nine_to_fourteen);
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
