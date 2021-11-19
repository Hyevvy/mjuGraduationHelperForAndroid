package com.cookandorid.graduationhelper;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.view.ViewGroup.*;
import static android.view.ViewGroup.LayoutParams.*;
import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MajorActivity extends AppCompatActivity {
    ImageView imgViewMajor;
    Button btnReturnCategory, btnCheckMajorInfo;
    String siteUrl, telNumber;
    JSONObject jsonObject;
    JSONArray Array, array;
    TextView tvMent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        imgViewMajor = (ImageView)findViewById(R.id.imgViewMajor);
        btnReturnCategory = (Button)findViewById(R.id.btnReturnCategory);
        tvMent = (TextView)findViewById(R.id.tvMent);
        LinearLayout layout = (LinearLayout)findViewById(R.id.majorSubjectBtn0);

        int count  = 0; //생성된 버튼의 개수
        Intent inIntent = getIntent();
        Bundle datas = inIntent.getExtras();
        String jsonArray = inIntent.getStringExtra("jsonArray");
        try {
            array = new JSONArray(jsonArray);
            System.out.println(array.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String major = datas.getString("major");
        String studentNum = datas.getString("studentNum");
        String telNumber = datas.getString("telNumber");
        String siteUrl = datas.getString("siteUrl");
        int studentNumInt = Integer.parseInt(studentNum);
        Toast.makeText(getApplicationContext(),major,Toast.LENGTH_LONG).show();
        LayoutParams params = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            //배열로된 자료를 가져올때
            try {
                Array = array;
                Button btn;
                for(int i=0; i<Array.length(); i++) {
                        JSONObject subjects = Array.getJSONObject(i);

                        btn = new Button(this);
                        btn.setText(subjects.getString("subjectName"));
                    Log.d("-- Subject is ", subjects.getString("subjectName"));
                        btn.setId(count);
                        count++;
                        btn.setLayoutParams(params);

                    if(btn.getParent() != null) {
                        ((ViewGroup)btn.getParent()).removeView(btn); // <- fix
                    }
                    layout.addView(btn);
                    Button finalBtn = btn;
                    btn.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v){
                                Drawable cor = tvMent.getBackground();
                                Drawable btnColor = finalBtn.getBackground();
                                Drawable layerColor = layout.getBackground();

                                if(cor == btnColor){
                                    finalBtn.setBackground(layerColor);
                                }
                                else finalBtn.setBackground(cor);

                        }
                    });
                        Log.d("-- Subject is ", subjects.getString("subjectName"));
                        Log.d("-- required is ", subjects.getString("required"));
                        Log.d("-- score is ", subjects.getString("score"));

                }

            } catch (JSONException e) {
                e.printStackTrace();
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
                intent.putExtra("telNumber", telNumber);
                intent.putExtra("siteUrl", siteUrl);
                startActivity(intent);
            }
        });

    }
}
