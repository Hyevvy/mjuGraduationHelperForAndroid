package com.cookandorid.graduationhelper;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    ImageView imgViewBase;
    Button btnReturnCategory, btnCheckMajorInfo;
    String[] majors= new String[]{"전기공학과", "전자공학과", "화학공학과", "신소재공학과","환경에너지공학과", "토목환경공학과", "교통공학과", "기계공학과", "산업경영공학과", "컴퓨터공학과"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        imgViewBase = (ImageView)findViewById(R.id.imgViewBase);

        Intent inIntent = getIntent();
        Bundle datas = inIntent.getExtras();
        String major = datas.getString("major");
        String studentNum = datas.getString("studentNum");
        String siteUrl = datas.getString("siteUrl");
        String telNumber = datas.getString("telNumber");
        Boolean isAbeek = datas.getBoolean("isAbeek");

        int studentNumInt = Integer.parseInt(studentNum);
        String fileString="";
        System.out.println(" majors.length :" + majors.length );

        if(!isAbeek) fileString = "base_isabeek";
        else {
            for (int i = 0; i < majors.length; i++) {
                System.out.println(" major :" + major);
                System.out.println(" majors" + i +" :" + majors[i]);
                if (majors[i].equals(major)) {
                    System.out.println(" i :" + i);
                    if (studentNumInt >= 18)
                        fileString = "base_" + i + "_18";
                    else {
                        //TODO : 컴공은 예외처리해줘야함.
                        fileString = "base_" + i;
                    }
                }

                System.out.println(fileString);
            } //close - for
        }
        int imageResource = getResources().getIdentifier(fileString, "drawable", getPackageName());

        Drawable image = getResources().getDrawable(imageResource);
        imgViewBase.setImageDrawable(image);


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
