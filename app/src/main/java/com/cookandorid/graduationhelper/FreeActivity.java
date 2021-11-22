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

        Integer studentNumInt = Integer.parseInt(studentNum);
        if(isAbeek  == true){
            //TODO : 자유 선택 학번 , 학과 별로 출력해야 함.
           // tvFree.setText();
        }
        else{
            if(studentNumInt>=9 && studentNumInt<=14){
                tvFree.setText("자유 선택에서 들어야 할 학점은 : 19");
            }
            else if(studentNumInt>=15 && studentNumInt<=18){
                tvFree.setText("자유 선택에서 들어야 할 학점은 : 12");
            }
            else if(studentNumInt > 18){
                tvFree.setText("자유 선택에서 들어야 할 학점은 : 10");
            }
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
