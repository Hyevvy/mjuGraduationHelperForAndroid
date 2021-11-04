package com.cookandorid.graduationhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    ImageView imgViewBase;
    Button btnReturnCategory, btnCheckMajorInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        imgViewBase = (ImageView)findViewById(R.id.imgViewBase);

        Intent inIntent = getIntent();
        Bundle datas = inIntent.getExtras();
        String major = datas.getString("major");
        String studentNum = datas.getString("studentNum");
        int studentNumInt = Integer.parseInt(studentNum);

        //학번 별로 다른 공통 교양 이미지 출력
        if(studentNumInt >= 18) {
            imgViewBase.setImageResource(R.drawable.base_aftereighteen);
        }
        else if(studentNumInt>=15 && studentNumInt<=17 ){
            imgViewBase.setImageResource(R.drawable.base_fifteen_to_seventeen);
        }
        else if(studentNumInt>=12 && studentNumInt<=14){
            imgViewBase.setImageResource(R.drawable.base_twelve_to_fourteen);
        }
        else if(studentNumInt>=10 && studentNumInt<=11 ){
            imgViewBase.setImageResource(R.drawable.base_ten_to_eleven);
        }

        else if(studentNumInt == 9){
            imgViewBase.setImageResource(R.drawable.base_nine);
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
                startActivity(intent);
            }
        });

    }
}
