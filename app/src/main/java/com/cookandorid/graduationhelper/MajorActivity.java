package com.cookandorid.graduationhelper;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
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
    Button btnReturnCategory, btnCheckMajorInfo, btnMajorInit, btnMajorSubmit;
    String siteUrl, telNumber;
    JSONObject jsonObject;
    JSONArray Array, array;
    TextView tvMent, tvAdditionalScore, tvAdditionalSubjects;
    Integer completedScore = 0;//총 이수학점
    LinearLayout  majorResultLayout, subjectLayout;
    boolean isResultPage = false;
    Integer isRequiredScore = 0;
    String[] shouldTake;
    Integer[] listened;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);




       // subjectLayout = (LinearLayout)findViewById(R.id.subjectLayout);
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
        if(studentNumInt>=9 && studentNumInt<=14){
            if(studentNumInt == 12 && major.equals("컴퓨터공학과")){
                isRequiredScore = 84;
            }
            else isRequiredScore = 70;
        }
        else if(studentNumInt>=15 && studentNumInt<=18){
            if(major.equals("컴퓨터공학과")) isRequiredScore = 84;
            else isRequiredScore = 70;
        }
        else if(studentNumInt >= 18)  {
            if(major.equals("컴퓨터공학과")) isRequiredScore = 74;
            else isRequiredScore = 70;
        }


        int j = 0;
        Toast.makeText(getApplicationContext(),major,Toast.LENGTH_LONG).show();
        LayoutParams params = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
            //배열로된 자료를 가져올때
            try {
                Array = array;
                Button btn;
                listened = new Integer[Array.length()];
                shouldTake = new String[Array.length()];
                for(int i=0; i<Array.length(); i++) listened[i] = 0;
                for(int i=0; i<Array.length(); i++) {
                        JSONObject subjects = Array.getJSONObject(i);
                        String subjectName = subjects.getString("subjectName");
                        if(subjects.getString("required").equals("true")){
                            shouldTake[i] = subjectName;
                        }
                        btn = new Button(this);
                        btn.setText(subjectName);

                        Log.d("-- Subject is ", subjects.getString("subjectName"));
                        btn.setId(count);
                        count++;
                        btn.setLayoutParams(params);

                    if(btn.getParent() != null) {
                        ((ViewGroup)btn.getParent()).removeView(btn); // <- fix
                    }
                    layout.addView(btn);
                    Button finalBtn = btn;
                    int finalI = i;
                    btn.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v){
                                Drawable cor = tvMent.getBackground();
                                Drawable btnColor = finalBtn.getBackground();
                                Drawable layerColor = layout.getBackground();
                            try {
                                Integer thisScore = Integer.parseInt(subjects.getString("score"));
                                if(cor == btnColor){
                                    finalBtn.setBackground(layerColor);
                                    completedScore -= thisScore;
                                    listened[finalI] = 0;
                                }
                                else {
                                    finalBtn.setBackground(cor);
                                    completedScore += thisScore;
                                    listened[finalI] = 1;
                                }
                                Log.d("-- completedScore is ", completedScore.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


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

        btnMajorInit = (Button)findViewById(R.id.btnMajorInit);
        btnMajorSubmit = (Button)findViewById(R.id.btnMajorSubmit);

        //초기화 버튼 누름
        Drawable layerColor = layout.getBackground();

        btnMajorInit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                completedScore = 0; //들었던 학점 초기화
                
                //check했던 과목 다 해제하기
                LinearLayout parentLayout = (LinearLayout)findViewById(R.id.majorSubjectBtn0);
                int childs = parentLayout.getChildCount();
                View v = null;
                for(int i=0; i<childs; i++) {
                    v = parentLayout.getChildAt(i);
                    v.setBackground(layerColor);
                }
            }
        });


        tvAdditionalScore=(TextView)findViewById(R.id.tvAdditionalScore);
        tvAdditionalSubjects=(TextView)findViewById(R.id.tvAdditionalSubjects);
        majorResultLayout = (LinearLayout)findViewById(R.id.majorResultLayout);


        btnMajorSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //추가로 들어야하는 학점, 추가로 들어야하는 과목 출력

                if(isResultPage == false){
                    isResultPage = true;
                    tvMent.setVisibility(GONE);
                    btnMajorInit.setVisibility(GONE);
                    layout.setVisibility(GONE);
                    majorResultLayout.setVisibility(VISIBLE);
                  //  ((LinearLayout.LayoutParams) majorResultLayout.getLayoutParams()).weight = 1;
                    btnMajorSubmit.setText("뒤로 가기");
                    String resultScore = String.valueOf(isRequiredScore - completedScore);
                    tvAdditionalScore.setText(resultScore);

                    String result = "";
                    for(int i=0; i<listened.length; i++) {
                        if(listened[i] == 0 && shouldTake[i]!=null){
                            result += shouldTake[i] + "\n";
                        }
                    }
                    tvAdditionalSubjects.setText(result);
                }
                else {
                    //다시 원래 전공페이지로 돌려놓음
                    isResultPage = false;
                    tvMent.setVisibility(VISIBLE);
                    btnMajorInit.setVisibility(VISIBLE);
                    layout.setVisibility(VISIBLE);
                    majorResultLayout.setVisibility(GONE);
                    btnMajorSubmit.setText("선택 완료");
                }

            }
        });
    }
}
