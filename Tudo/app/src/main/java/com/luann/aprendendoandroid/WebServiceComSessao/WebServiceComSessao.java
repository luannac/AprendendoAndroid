package com.luann.aprendendoandroid.WebServiceComSessao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.luann.aprendendoandroid.R;

public class WebServiceComSessao extends AppCompatActivity {
    public FrameLayout frame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_service_sessao_activity_web_service_com_sessao);

        frame = findViewById(R.id.web_service_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.web_service_frame,new Web_service_sessao_menu()).commit();
    }
}
