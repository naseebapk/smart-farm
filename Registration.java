package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Registration extends AppCompatActivity {
    EditText name,email,age,phone,address,password,passwordd;
    Button b;
    String NameHolder, EmailHolder,AgeHolder,PhoneHolder,AddressHolder, PasswordHolder,PassworddHolder;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Boolean EditTextEmptyHolder,f,g,k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        age=(EditText)findViewById(R.id.age);
        phone=(EditText)findViewById(R.id.phone);
        address=(EditText)findViewById(R.id.address);
        password=(EditText)findViewById(R.id.password);
        passwordd=(EditText)findViewById(R.id.passwordd);
        b=(Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextStatus();
                CheckEmailValidation();
                PhoneValidation();

                passwordvalidation();
                if ((EditTextEmptyHolder == true) && (f)&&(k) && (g)) {
                    MediaType MEDIA_TYPE = MediaType.parse("application/json");
                    String url = "http://192.168.43.79:8000/registration/android/";
                    OkHttpClient client = new OkHttpClient();

                    JSONObject postdata = new JSONObject();
                    try {
                        postdata.put("name", name.getText().toString());
                        postdata.put("email", email.getText().toString());
                        postdata.put("age", age.getText().toString());
                        postdata.put("ph", phone.getText().toString());
                        postdata.put("address", address.getText().toString());
                        postdata.put("password", password.getText().toString());


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
                    Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),UserH.class);
                    startActivity(i);
                } else {
                    if (EditTextEmptyHolder == false) {


                        // Printing toast message if any of EditText is empty.
                        Toast.makeText(Registration.this, "Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

                    } else if (f == false) {
                        Toast.makeText(Registration.this, "Invalid email.", Toast.LENGTH_LONG).show();
                    } else if (k == false) {
                        Toast.makeText(Registration.this, "Invalid Phone number.", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(Registration.this, " Password Mismatch.", Toast.LENGTH_LONG).show();
                    }
                }


            }});
        

    }
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        NameHolder = name.getText().toString() ;
        EmailHolder = email.getText().toString();
        AgeHolder=age.getText().toString();
        PhoneHolder=phone.getText().toString();
        AddressHolder=address.getText().toString();
        PasswordHolder = password.getText().toString();
        PassworddHolder=passwordd.getText().toString();




        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(AgeHolder)|| TextUtils.isEmpty(PhoneHolder)|| TextUtils.isEmpty(AddressHolder)|| TextUtils.isEmpty(PasswordHolder)|| TextUtils.isEmpty(PassworddHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }
    public void CheckEmailValidation()
    {
        if (EmailHolder.matches(emailPattern))
        {
            f=true;
        }
        else
        {
            f=false;


        }
    }
    public void passwordvalidation()
    {

        if(PasswordHolder.equals(PassworddHolder))
        {
            g=true;
        }
        else
        {
            g=false;
        }
    }
    public void PhoneValidation()
    {

        if(PhoneHolder.length()!=(10))
        {


            k=false;
        }
        else
        {
            k=true;
        }
    }

}
