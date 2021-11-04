package com.cookandorid.graduationhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MajorActivity extends AppCompatActivity {
    ImageView imgViewMajor;
    Button btnReturnCategory, btnCheckMajorInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        imgViewMajor = (ImageView)findViewById(R.id.imgViewMajor);
        btnReturnCategory = (Button)findViewById(R.id.btnReturnCategory);

        Intent inIntent = getIntent();
        Bundle datas = inIntent.getExtras();
        String major = datas.getString("major");
        String studentNum = datas.getString("studentNum");
        int studentNumInt = Integer.parseInt(studentNum);
        Toast.makeText(getApplicationContext(),major,Toast.LENGTH_LONG).show();
        if(major=="컴퓨터공학과"){
            Toast.makeText(getApplicationContext(),"컴공입니디",Toast.LENGTH_LONG).show();
            System.out.println("컴공임");
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
