package com.cookandorid.graduationhelper;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
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
    JSONArray Array;
    public JSONObject getJson(String fileName){
        String json = "";
        System.out.println(fileName);
        try{
            InputStream is = getAssets().open(fileName);

            int fileSize = is.available();
            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();
            json = new String(buffer,"UTF-8");
//            Log.d("-- json = ", json);

            jsonObject = new JSONObject(json);

            return jsonObject;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        imgViewMajor = (ImageView)findViewById(R.id.imgViewMajor);
        btnReturnCategory = (Button)findViewById(R.id.btnReturnCategory);

        LinearLayout layout = (LinearLayout)findViewById(R.id.majorSubjectBtn0);

        int count  = 0; //생성된 버튼의 개수
        Intent inIntent = getIntent();
        Bundle datas = inIntent.getExtras();
        String major = datas.getString("major");
        String studentNum = datas.getString("studentNum");
        int studentNumInt = Integer.parseInt(studentNum);
        System.out.println(major);
        Toast.makeText(getApplicationContext(),major,Toast.LENGTH_LONG).show();
        LayoutParams params = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);


        Button.OnClickListener btnSubjectOnclick = new Button.OnClickListener(){
            public void onClick(View view) {

                System.out.println("과목눌림");
            }
        };

        if(major.equals("컴퓨터공학과")){
            System.out.println(123);
            Toast.makeText(getApplicationContext(),"컴공입니디",Toast.LENGTH_LONG).show();
            JSONObject ret = getJson("jsons/majorinfo.json");

            //배열로된 자료를 가져올때
            try {
                Array = ret.getJSONArray("subject");//배열의 이름
                telNumber = ret.getString("telNumber");
                siteUrl = ret.getString("siteUrl");

                Button btn;
                for(int i=0; i<Array.length(); i++) {
                        JSONObject subjects = Array.getJSONObject(i);

                        btn = new Button(this);
                        btn.setText(subjects.getString("subjectName"));
                        btn.setId(count);
                        count++;
                        btn.setLayoutParams(params);
                        btn.setOnClickListener(btnSubjectOnclick);
                    if(btn.getParent() != null) {
                        ((ViewGroup)btn.getParent()).removeView(btn); // <- fix
                    }
                    layout.addView(btn);

                        Log.d("-- Subject is ", subjects.getString("subjectName"));
                        Log.d("-- required is ", subjects.getString("required"));
                        Log.d("-- score is ", subjects.getString("score"));


                }

            } catch (JSONException e) {
                e.printStackTrace();
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
                intent.putExtra("telNumber", telNumber);
                intent.putExtra("siteUrl", siteUrl);
                startActivity(intent);
            }
        });

    }
}
