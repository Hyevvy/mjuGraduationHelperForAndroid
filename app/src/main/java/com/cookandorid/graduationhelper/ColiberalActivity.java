package com.cookandorid.graduationhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ColiberalActivity extends AppCompatActivity {
    Button btnReturnCategory;
    ImageView imgViewColiberal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coliberal);

        Intent inIntent = getIntent();
        Bundle datas = inIntent.getExtras();
        String major = datas.getString("major");
        String studentNum = datas.getString("studentNum");
        int studentNumInt = Integer.parseInt(studentNum);
        imgViewColiberal = (ImageView)findViewById(R.id.imgViewColiberal);

        //학번 별로 다른 공통 교양 이미지 출력
        if(studentNumInt >= 20) {
            imgViewColiberal.setImageResource(R.drawable.coliberal_aftertwenty);
        }
        else if(studentNumInt>=18 && studentNumInt<=19 ){
            imgViewColiberal.setImageResource(R.drawable.coliberal_eighteen_to_nineteen);
        }
        else if(studentNumInt>=15 && studentNumInt<=17 ){
            imgViewColiberal.setImageResource(R.drawable.coliberal_fifteen_to_seventeen);
        }
        else if(studentNumInt==14){
            imgViewColiberal.setImageResource(R.drawable.coliberal_fourteen);
        }
        else if(studentNumInt >= 9 && studentNumInt <= 13){
            System.out.println("9 - 13");
            imgViewColiberal.setImageResource(R.drawable.coliberal_nine_to_thirteen);
        }

        btnReturnCategory = (Button)findViewById(R.id.btnReturnCategory);

        btnReturnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
