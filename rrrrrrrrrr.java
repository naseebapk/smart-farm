package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
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

public class rrrrrrrrrr extends AppCompatActivity {
ListView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rrrrrrrrrr);
        v=(ListView)findViewById(R.id.viii);

        AsyncTask asyncTask=
                new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url("http://192.168.43.79:8000/result/android/").build();
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
                        String id=c.getString("id");
                        String uid = c.getString("uid");
                        String result_svm=c.getString("result_svm");
                        String result_kem=c.getString("result_kem");
                        String date = c.getString("date");

                        HashMap<String,String> hmap=new HashMap<>();
                        hmap.put("id",id);
                        hmap.put("uid",uid);
                        hmap.put("result_svm",result_svm);
                        hmap.put("result_kem",result_kem);
                        hmap.put("date",date);


                        if(uid==Login.uid) {
                            al.add(hmap);
                        }





                    }

                    ListAdapter lis=new SimpleAdapter(rrrrrrrrrr.this,al,R.layout.rrrrrr,new String[]{"result_svm","result_kem","date"},new int[]{R.id.tit,R.id.textView18,R.id.textView20});
                    v.setAdapter(lis);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();


                }

            }
        }.execute();
    }

}
