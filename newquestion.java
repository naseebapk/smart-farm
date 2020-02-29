package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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

public class newquestion extends AppCompatActivity {
EditText question;
Button post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newquestion);
        question=(EditText)findViewById(R.id.question);
        post=(Button) findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaType MEDIA_TYPE = MediaType.parse("application/json");
                String url = "http://192.168.43.79:8000/qa/android/";
                OkHttpClient client = new OkHttpClient();

                JSONObject postdata = new JSONObject();
                try {
                    postdata.put("question", question.getText().toString());

                    postdata.put("uid", "1");


                } catch (JSONException e) {
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
                        //Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
                        Log.e("Tag", response.body().string());
//                        Intent i=new Intent(getApplicationContext(),UserH.class);
//                       startActivity(i);
                    }

                });
            }
        });
    }
}
