package com.luann.webservice;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    EditText etNome,etEmail,etCpf;
    Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        etCpf = findViewById(R.id.etCpf);
        etEmail = findViewById(R.id.etEmail);
        btCadastrar = findViewById(R.id.btCadastrar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //The url requisition object
                RequestQueue rq = Volley.newRequestQueue(MainActivity.this);
                String urlWebService = "" +
                        "http://esn509d1159010/LuannWebService/WebService.asmx/cadastraCliente";

                //WebService call
                StringRequest sr =  new StringRequest(Request.Method.POST, urlWebService, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //When connect without any error

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //When happens some error

                    }
                });

                //Execute the command
            }
        });

    }
}
