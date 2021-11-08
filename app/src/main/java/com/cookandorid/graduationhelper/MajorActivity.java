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
//    ArrayList<Major> majorList;

//    private String getJsonString(){
//        String json = "";
//        try{
//            InputStream is = getAssets().open("jsons/majorinfo.json");
//
//            int fileSize = is.available();
//            byte[] buffer = new byte[fileSize];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer,"UTF-8");
//            Log.d("-- json = ", json);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return json;
//    }

//    public class Subject {
//        public Subject(String subjectName, boolean required, Long score) {
//            this.subjectName = subjectName;
//            this.required = required;
//            this.score = score;
//        }
//
//        public String getSubjectName() {
//            return subjectName;
//        }
//
//        public Long getScore() {
//            return score;
//        }
//
//        public void setScore(Long score) {
//            this.score = score;
//        }
//
//        public boolean isRequired() {
//            return required;
//        }
//
//        public void setSubjectName(String subjectName) {
//            this.subjectName = subjectName;
//        }
//
//        public void setRequired(boolean required) {
//            this.required = required;
//        }
//
//        private String subjectName;
//        private boolean required;
//        private Long score;
//
//    }
//
//
//    public class Major{
//        private String majorName;
//        private String siteUrl;
//        private String telNumber;
//        private ArrayList<Subject> subjects = new ArrayList<Subject>();
//
//
//        public Major(String siteUrl, String telNumber, String majorName, ArrayList<Subject> subjects) {
//            this.siteUrl = siteUrl;
//            this.telNumber = telNumber;
//            this.majorName = majorName;
//            this.subjects = subjects;
//        }
//        public ArrayList<Subject>getSubjects(){
//            return subjects;
//        }
//
//        public void setSubjects(ArrayList<Subject> subjects) {
//            this.subjects = subjects;
//        }
//
//        public String getMajorName(){
//            return majorName;
//        }
//        public void setMajorName(String majorName){
//            this.majorName = majorName;
//        }
//        public String getSiteUrl(){
//            return siteUrl;
//        }
//        public String getTelNumber(){
//            return telNumber;
//        }
//        public void setSiteUrl(String siteUrl){
//            this.siteUrl =  siteUrl;
//        }
//        public void setTelNumber(String telNumber){
//            this.telNumber = telNumber;
//        }
//    }
//
//    public Major jsonParsing() {
//        try{
//            JSONObject jsonObject = new JSONObject(getJsonString());
//            JSONArray majorArray = jsonObject.getJSONArray("majorinfo");
//
//            for(int i=0; i<majorArray.length(); i++)
//            {
//                JSONObject majorObject = majorArray.getJSONObject(i);
//
//
//                String siteUrl = (String) majorObject.get("siteUrl");
//                String telNumber = (String) majorObject.get("telNumber");
//                String majorName = (String) majorObject.get("majorName");
//
//                JSONArray jSubjects = (JSONArray) jsonObject.get("subject");
//                ArrayList<Subject> tList = new ArrayList<Subject>();
//                for (int j = 0; i < jSubjects.length(); i++) {
//                    JSONObject t = (JSONObject) jSubjects.get(i);
//                    Long score = (Long)t.get("score");
//                    boolean required = (boolean)t.get("required");
//                    String subjectName = (String)t.get("subjectName");
//                    tList.add(new Subject(subjectName, required, score));
//                }
//
//                return new Major(siteUrl, telNumber, majorName, tList);
//            }
//        }catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return new Major(null, null, null, null);
//    }
//
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
                ArrayList<LinearLayout> row = new ArrayList<LinearLayout>();

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
                intent.putExtra("major", major);
                startActivity(intent);
            }
        });

    }
}
