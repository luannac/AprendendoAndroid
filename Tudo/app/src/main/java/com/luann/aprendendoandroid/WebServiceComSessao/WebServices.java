package com.luann.aprendendoandroid.WebServiceComSessao;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class WebServices {
    private Context context;
    final private String urlServidor = "http://iacess.azurewebsites.net/Models/AndroidServices.asmx/";

    public WebServices(Context context){
        this.context = context;
    }

    public void chamarWebService(String url, final Map<String,Object> parametros, final RetornoWS retorno){
        url = urlServidor+url;
        RequestQueue rq = Volley.newRequestQueue(context);

        StringRequest sr = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String resposta) {
                retorno.onSucess(resposta);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError erro) {
                retorno.onError(erro.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //Passagem de parâmetros para o método do WebService
                Map<String, String> parametro =
                        new HashMap<String, String>();
                for ( String key : parametros.keySet() ) {
                    parametro.put(key, String.valueOf(parametros.get(key)));
                }

                return parametro;
            }
        };
        rq.add(sr);
    }
}