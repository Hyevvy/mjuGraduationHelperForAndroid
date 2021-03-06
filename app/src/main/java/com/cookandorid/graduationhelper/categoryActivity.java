package com.cookandorid.graduationhelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class categoryActivity extends AppCompatActivity {
    Button btnColiberal, btnLiberal, btnBase, btnMajor, btnFree, btnGeneralLiberal;
    JSONObject jsonObject;
    JSONArray Array;
    String siteUrl, telNumber;

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
        setContentView(R.layout.activity_category);

        btnColiberal = (Button)findViewById(R.id.btnColiberal);
        btnLiberal = (Button)findViewById(R.id.btnLiberal);
        btnBase = (Button)findViewById(R.id.btnBase);
        btnFree = (Button)findViewById(R.id.btnFree);
        btnGeneralLiberal = (Button)findViewById(R.id.btnGeneralLiberal);
        btnMajor = (Button)findViewById(R.id.btnMajor);

        Intent inIntent = getIntent();
        Bundle datas = inIntent.getExtras();
        String major = datas.getString("major");
        String studentNum = datas.getString("studentNum");
        Integer idx = datas.getInt("retIdx") + 1;
        Boolean isAbeek = datas.getBoolean("isAbeek");

        JSONObject ret = getJson("jsons/testData" + idx +".json");
        try{
            Array = ret.getJSONArray("subject");//????????? ??????
            telNumber = ret.getString("telNumber");
            siteUrl = ret.getString("siteUrl");
        }catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("-- telNumber is ", telNumber);
        Log.d("-- siteUrl is ", siteUrl);


        //???????????? ????????? ????????? ???
        btnColiberal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ColiberalActivity.class);
                intent.putExtra("major", major);
                intent.putExtra("studentNum", studentNum);
                intent.putExtra("siteUrl",siteUrl);
                intent.putExtra("telNumber", telNumber);
                startActivityForResult(intent, 1001);
            }
        });

        //??????????????? ????????? ???
        btnLiberal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LiberalActivity.class);
                intent.putExtra("major", major);
                intent.putExtra("studentNum", studentNum);
                intent.putExtra("siteUrl",siteUrl);
                intent.putExtra("telNumber", telNumber);
                startActivityForResult(intent, 1002);
            }
        });

        //?????? ????????? ????????? ???
        btnBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
                intent.putExtra("major", major);
                intent.putExtra("studentNum", studentNum);
                intent.putExtra("siteUrl",siteUrl);
                intent.putExtra("telNumber", telNumber);
                intent.putExtra("isAbeek", isAbeek);
                startActivityForResult(intent, 1003);
            }
        });

        //????????? ????????? ???
        btnMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MajorActivity.class);
                intent.putExtra("major", major);
                intent.putExtra("jsonArray", Array.toString());
                intent.putExtra("studentNum", studentNum);
                intent.putExtra("siteUrl",siteUrl);
                intent.putExtra("telNumber", telNumber);
                startActivityForResult(intent, 1004);
            }
        });

        //?????? ????????? ????????? ???
        btnFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FreeActivity.class);
                intent.putExtra("major", major);
                intent.putExtra("jsonArray", Array.toString());
                intent.putExtra("studentNum", studentNum);
                intent.putExtra("siteUrl",siteUrl);
                intent.putExtra("telNumber", telNumber);
                intent.putExtra("isAbeek", isAbeek);
                startActivityForResult(intent, 1005);
            }
        });

        //?????? ????????? ????????? ???
        btnGeneralLiberal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GeneralLiberalActivity.class);
                intent.putExtra("major", major);
                intent.putExtra("jsonArray", Array.toString());
                intent.putExtra("studentNum", studentNum);
                intent.putExtra("siteUrl",siteUrl);
                intent.putExtra("telNumber", telNumber);
                intent.putExtra("isAbeek", isAbeek);
                startActivityForResult(intent, 1006);
            }
        });


    }
    //???????????? ?????? ????????? ???????????? ????????????
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1001:
                if(resultCode == RESULT_OK){
                    System.out.println("?????? ???????????? ???????????? ???????????? ?????????");
                }
                break;
            case 1002:
                if(resultCode == RESULT_OK){
                    System.out.println("?????? ???????????? ???????????? ???????????? ?????????");
                }
                break;
            case 1003:
                if(resultCode == RESULT_OK){
                    System.out.println("?????? ???????????? ???????????? ???????????? ?????????");
                }
                break;
            case 1004:
                if(resultCode == RESULT_OK){
                    System.out.println("???????????? ???????????? ???????????? ?????????");
                }
                break;
            case 1005:
                if(resultCode == RESULT_OK){
                    System.out.println("?????????????????? ???????????? ???????????? ?????????");
                }
                break;
            case 1006:
                if(resultCode == RESULT_OK){
                    System.out.println("?????????????????? ???????????? ???????????? ?????????");
                }
                break;
        }
    }
}
