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
        int studentNumInt = Integer.parseInt(studentNum);
        String fileString="";
        for(int i=0; i<majors.length; i++){
            System.out.println("major :" + major );
            System.out.println(" majors[" + i +"] :" + majors[i] );

            if(majors[i].equals(major)){
                System.out.println(" i :" + i );
                if(studentNumInt >= 18)
                    fileString = "base_" + i;
                else{
                    if(i == 9) { //컴공일때
                        if (studentNumInt >= 18) {
                            fileString = "base_" + i;
                        } else if (studentNumInt >= 15 && studentNumInt <= 17) {
                            imgViewBase.setImageResource(R.drawable.base_fifteen_to_seventeen_cs);
                        } else if (studentNumInt >= 12 && studentNumInt <= 14) {
                            imgViewBase.setImageResource(R.drawable.base_twelve_to_fourteen_cs);
                        } else if (studentNumInt >= 10 && studentNumInt <= 11) {
                            imgViewBase.setImageResource(R.drawable.base_ten_to_eleven_cs);
                        } else if (studentNumInt == 9) {
                            imgViewBase.setImageResource(R.drawable.base_nine_cs);
                        }
                    }
                }
            }
            System.out.println(fileString);
        } //close - for

        //학번 별로 다른 공통 교양 이미지 출력
        if(studentNumInt >= 18) {
            imgViewBase.setImageResource(R.drawable.base_aftereighteen);
        }
        else if(studentNumInt>=15 && studentNumInt<=17 ){
            imgViewBase.setImageResource(R.drawable.base_fifteen_to_seventeen_cs);
        }
        else if(studentNumInt>=12 && studentNumInt<=14){
            imgViewBase.setImageResource(R.drawable.base_twelve_to_fourteen_cs);
        }
        else if(studentNumInt>=10 && studentNumInt<=11 ){
            imgViewBase.setImageResource(R.drawable.base_ten_to_eleven_cs);
        }

        else if(studentNumInt == 9){
            imgViewBase.setImageResource(R.drawable.base_nine_cs);
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
