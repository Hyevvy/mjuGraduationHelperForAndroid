package com.cookandorid.graduationhelper;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InputInfoActivity extends AppCompatActivity {
    Button btnInputSubmit;
    EditText etInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_info);

        etInfo = (EditText)findViewById(R.id.editInfo);
        btnInputSubmit = (Button)findViewById(R.id.btnInfoSubmit);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinnerMajor);
        String[] majors= new String[]{"컴퓨터공학과", "전기공학과", "정보통신공학과"};


        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                majors);

        mySpinner.setAdapter(myAdapter);
        String text = mySpinner.getSelectedItem().toString();
        System.out.println(text);

        btnInputSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etInfo.length()==0){
                    Toast.makeText(getApplicationContext(), "학번을 입력해주세요" , Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), categoryActivity.class);
                    intent.putExtra("studentNum", etInfo.getText().toString());
                    intent.putExtra("major", text);
                    startActivity(intent);
                }
            }
        });
    }
}
