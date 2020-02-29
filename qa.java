package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class qa extends AppCompatActivity {
Button b;
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);
        b=(Button)findViewById(R.id.button4);
        listView=(ListView)findViewById(R.id.list_item);
        AsyncTask asyncTask=new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url("http://192.168.43.79:8000/qa/android/").build();
                Response response=null;
                try {
                    response=client.newCall(request).execute();
                    return response.body().string();

                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
                }


                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                try {
                    ArrayList<HashMap<String,String>> al=new ArrayList<>();
                    JSONArray array= new JSONArray(o.toString());
                    String ln=Integer.toString( array.length());



                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject c= array.getJSONObject(i);
                        String qid=c.getString("qid");
                        String question = c.getString("question");
                        String uid = c.getString("uid");
                        String answer = c.getString("answer");

                        HashMap<String,String> hmap=new HashMap<>();
                        hmap.put("qid",qid);
                        hmap.put("question",question);
                        hmap.put("uid",uid);
                        hmap.put("answer",answer);
                        al.add(hmap);




                    }
                    ListAdapter lis=new SimpleAdapter(qa.this,al,R.layout.answer,new String[]{"question","answer"},new int[]{R.id.textView2,R.id.textView3});
                    listView.setAdapter(lis);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();


                }

            }
        }.execute();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),newquestion.class);
                startActivity(in);
            }
        });
    }
}
