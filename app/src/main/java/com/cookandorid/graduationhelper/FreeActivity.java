package com.cookandorid.graduationhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class FreeActivity extends AppCompatActivity {

    Button btnReturnCategory, btnCheckMajorInfo;
    TextView tvFree;
    //컴공은 예외케이스가 많아서 제외함.
    String[][] free = new String[][]{{"9", "10", "9~10", "9", "8", "10", "8", "10", "10"}, {"7","7","7", "7" ,"7", "14", "7", "4", "7", "7"}, {"5","5","5","5","5","5","5","2","5","5"}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free);


        Intent inIntent = getIntent();
        Bundle datas = inIntent.getExtras();
        String major = datas.getString("major");
        String studentNum = datas.getString("studentNum");
        String siteUrl = datas.getString("siteUrl");
        String telNumber = datas.getString("telNumber");
        Boolean isAbeek = datas.getBoolean("isAbeek");

        tvFree = (TextView)findViewById(R.id.tvFree);

        //전공 idx 구하기
        String[] majors= new String[]{"전기공학과", "전자공학과", "화학공학과", "신소재공학과","환경에너지공학과", "토목환경공학과", "교통공학과", "기계공학과", "산업경영공학과", "컴퓨터공학과"};
        Integer majorIdx = 0;
        for(int i=0; i<majors.length; i++){
            if(major.equals(majors[i])){
                majorIdx = i;
                break;
            }
        }

        System.out.println("major Index : " + majorIdx);

        Integer studentNumInt = Integer.parseInt(studentNum);
        Integer shouldCompleteFree = 0;
        if(isAbeek  == true){
            //공학인증을 하는 경우
            //TODO : 자유 선택 학번 , 학과 별로 출력해야 함.
           if(majorIdx == 9){
               //공학인증을 하는데 컴퓨터 공학과인 경우
               if(studentNumInt >= 9 && studentNumInt <= 11) {
                   shouldCompleteFree = 10;

               }
               else if(studentNumInt >= 12 && studentNumInt<=14) {
                   shouldCompleteFree = 11;

               }
               else if(studentNumInt>=15 && studentNumInt<=18){
                   shouldCompleteFree = 14;
               }
               else if(studentNumInt > 18){
                   shouldCompleteFree = 12;
               }
           }
           else {
               //공학인증을 하는데 컴퓨터공학과가 아닌 경우
               if(studentNumInt >= 9 && studentNumInt <=14){
                    shouldCompleteFree = Integer.parseInt(free[0][majorIdx]);
               } else if (studentNumInt >= 15 && studentNumInt <= 18) {
                   shouldCompleteFree = Integer.parseInt(free[1][majorIdx]);
               }
               else{
                   shouldCompleteFree = Integer.parseInt(free[2][majorIdx]);
               }
           }
        }
        else{
            //공학인증을 하지 않는 경우
            if(studentNumInt>=9 && studentNumInt<=14){
                shouldCompleteFree = 19;
            }
            else if(studentNumInt>=15 && studentNumInt<=18){
                shouldCompleteFree = 12;
            }
            else if(studentNumInt > 18){
                shouldCompleteFree = 10;
            }
        }

        tvFree.setText(shouldCompleteFree.toString());


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
