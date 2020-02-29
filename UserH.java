package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserH extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_h);
        listView = (ListView) findViewById(R.id.list_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        vw_mach();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_h, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.sensor) {

            Intent i = new Intent(this, Result.class);
            startActivity(i);
        } else if (id == R.id.qa) {

            Intent i = new Intent(this, qa.class);
            startActivity(i);

        } else if (id == R.id.method) {
            Intent i = new Intent(this, viewposts.class);
            startActivity(i);
        } else if (id == R.id.profile) {
            Intent i = new Intent(this, profile.class);
            startActivity(i);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public  void vw_mach()
    {

        AsyncTask asyncTask=new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url("http://192.168.43.79:8000/machines/android/").build();
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
                        String date = c.getString("date");
                        String name = c.getString("name");
                        String image = c.getString("image");
                        String description = c.getString("description");

                        HashMap<String,String> hmap=new HashMap<>();
                        hmap.put("id",id);
                        hmap.put("date",date);
                        hmap.put("name",name);
                        hmap.put("image",image);
                        hmap.put("description",description);
                        al.add(hmap);




                    }
                    ListAdapter lis=new SimpleAdapter(UserH.this,al,R.layout.machine,new String[]{"image","name","description"},new int[]{R.id.imageView2,R.id.textView5,R.id.textView6});
                    listView.setAdapter(lis);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();


                }

            }
        }.execute();


}



}
