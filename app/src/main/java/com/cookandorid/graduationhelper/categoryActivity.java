package com.cookandorid.graduationhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class categoryActivity extends AppCompatActivity {
    Button btnColiberal, btnLiberal, btnBase, btnMajor, btnElective, btnGeneralLiberal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        btnColiberal = (Button)findViewById(R.id.btnColiberal);
        btnLiberal = (Button)findViewById(R.id.btnLiberal);
        btnBase = (Button)findViewById(R.id.btnBase);
        btnElective = (Button)findViewById(R.id.btnElective);
        btnGeneralLiberal = (Button)findViewById(R.id.btnGeneralLiberal);
        btnMajor = (Button)findViewById(R.id.btnMajor);

        Intent inIntent = getIntent();
        Bundle datas = inIntent.getExtras();
        String major = datas.getString("major");
        String studentNum = datas.getString("studentNum");
        System.out.println(major);
        System.out.println(studentNum);


        //공통교양 버튼이 눌렸을 때
        btnColiberal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ColiberalActivity.class);
                intent.putExtra("major", major);
                intent.putExtra("studentNum", studentNum);
                startActivityForResult(intent, 1001);
            }
        });

        //핵심교양이 눌렸을 때
        btnLiberal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LiberalActivity.class);
                intent.putExtra("major", major);
                intent.putExtra("studentNum", studentNum);
                startActivityForResult(intent, 1002);
            }
        });

        //학문 기초가 눌렸을 때
        btnBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
                intent.putExtra("major", major);
                intent.putExtra("studentNum", studentNum);
                startActivityForResult(intent, 1003);
            }
        });

        //전공이 눌렸을 때
        btnMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MajorActivity.class);
                intent.putExtra("major", major);
                intent.putExtra("studentNum", studentNum);
                startActivityForResult(intent, 1004);
            }
        });
    } 
    //돌아가기 버튼 클릭시 호출되는 콜백함수
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1001:
                if(resultCode == RESULT_OK){
                    System.out.println("공통 교양에서 카테고리 확인으로 돌아옴");
                }
                break;
            case 1002:
                if(resultCode == RESULT_OK){
                    System.out.println("핵심 교양에서 카테고리 확인으로 돌아옴");
                }
                break;
            case 1003:
                if(resultCode == RESULT_OK){
                    System.out.println("학문 기초에서 카테고리 확인으로 돌아옴");
                }
                break;
            case 1004:
                if(resultCode == RESULT_OK){
                    System.out.println("전공에서 카테고리 확인으로 돌아옴");
                }
                break;
        }
    }
}
