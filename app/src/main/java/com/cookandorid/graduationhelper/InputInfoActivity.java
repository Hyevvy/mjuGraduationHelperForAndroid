package com.cookandorid.graduationhelper;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InputInfoActivity extends AppCompatActivity {
    Button btnInputSubmit;
    EditText etInfo;
    String retMajor;
    Integer retIdx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_info);

        etInfo = (EditText)findViewById(R.id.editInfo);
        btnInputSubmit = (Button)findViewById(R.id.btnInfoSubmit);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinnerMajor);
        String[] majors= new String[]{"전기공학과", "전자공학과", "화학공학과", "신소재공학과","환경에너지공학과", "토목환경공학과", "교통공학과", "기계공학과", "산업경영공학과", "컴퓨터공학과"};


        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                majors);


        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override   // position 으로 몇번째 것이 선택됬는지 값을 넘겨준다
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                retMajor = majors[position];
                retIdx = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("선택이 안 되었음.");
            }
        });


        Integer indexValue = mySpinner.getSelectedItemPosition();
        System.out.println(indexValue);

        btnInputSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etInfo.length()==0) {
                    Toast.makeText(getApplicationContext(), "학번을 입력해주세요", Toast.LENGTH_LONG).show();
                }
                else if(Integer.parseInt(etInfo.getText().toString()) <= 8){
                    Toast.makeText(getApplicationContext(), "09년도 이후 입학생만 사용 가능합니다" , Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), categoryActivity.class);
                    intent.putExtra("studentNum", etInfo.getText().toString());
                    intent.putExtra("major", retMajor);
                    intent.putExtra("retIdx", retIdx);
                    startActivity(intent);
                }
            }
        });
    }
}
