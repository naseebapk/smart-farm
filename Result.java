package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Result extends AppCompatActivity {
EditText mio,temp,rain,hum;
Button res,view;
String humid,illumi,inspe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        temp=(EditText)findViewById(R.id.temp);
        mio=(EditText)findViewById(R.id.moi);
        rain=(EditText)findViewById(R.id.rain);
        hum=(EditText)findViewById(R.id.hum);
        res=(Button)findViewById(R.id.button5);
        view=(Button)findViewById(R.id.button3);

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reg_db();
            }

            });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),rrrrrrrrrr.class);
                startActivity(i);

            }
        });
    }

    public  void reg_db()
    {


        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "http://192.168.43.79:8000/result/android/";
        OkHttpClient client = new OkHttpClient();

        JSONObject postdata = new JSONObject();
        try {
                    postdata.put("temp", temp.getText().toString());
                    postdata.put("mio", mio.getText().toString());
                    postdata.put("rain", rain.getText().toString());
                    postdata.put("hum", hum.getText().toString());
                    postdata.put("uid",Login.uid);





        } catch(JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String mMessage = "tagggg";

                Log.e("Tag", mMessage);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                //Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
                Log.e("Tag", response.body().string());
                Intent i=new Intent(getApplicationContext(),Result.class);
                startActivity(i);
            }
        });



    }
}

