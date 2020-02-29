package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
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

public class Notification extends AppCompatActivity {
ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        listview=(ListView)findViewById(R.id.list_item);
        AsyncTask asyncTask=new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url("http://192.168.43.79:8000/notification/android/").build();
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
                        String status = c.getString("status");
                        String title = c.getString("title");
                        String date = c.getString("date");
                        String description = c.getString("description");

                        HashMap<String,String> hmap=new HashMap<>();
                        hmap.put("id",id);
                        hmap.put("status",status);
                        hmap.put("title",title);
                        hmap.put("date",date);
                        hmap.put("description",description);
                        al.add(hmap);




                    }

                    ListAdapter lis=new SimpleAdapter(Notification.this,al,R.layout.notification,new String[]{"title","description","date"},new int[]{R.id.textView4,R.id.textView7,R.id.textView8});
                    listview.setAdapter(lis);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();


                }

            }
        }.execute();
    }
}
