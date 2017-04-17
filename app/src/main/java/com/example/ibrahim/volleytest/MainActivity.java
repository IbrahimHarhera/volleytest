package com.example.ibrahim.volleytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url="http://ws.polarisclm.com/JsonService.asmx/Login";
    String serviceUserName="UserName";
    String servicePassword="Password";

    String userName="1x3m8BjizLyenSDm57mOfEUbb+i3tPlJwzTonR390Ug=";
    String password="0YCgdLHGwsglxsNblNAmCA==";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv=(TextView) findViewById(R.id.tv_response);

        final RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Response: "+response, Toast.LENGTH_SHORT).show();
                        Log.d("Response",response.toString());
                        tv.setText(response.toString());
                        requestQueue.stop();
                    }
                },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Erorr is happened", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                tv.setText(error.toString());
                error.printStackTrace();
                requestQueue.stop();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put(serviceUserName,userName);
                params.put(servicePassword,password);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
