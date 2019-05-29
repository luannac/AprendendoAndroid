package luann.app.aula5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class actImcResult extends AppCompatActivity {
    private double resultado;
    private TextView txImc,txTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_imc_result);

        resultado = getIntent().getDoubleExtra("imc",0);
        txImc = findViewById(R.id.txIcm);
        txTexto = findViewById(R.id.txTextRes);

        DecimalFormat df = new DecimalFormat();
        df.applyPattern("##.#");

        txImc.setText("Imc: "+ df.format(resultado));


        if(resultado<18.5) {
            txTexto.setText(" Magreza Grave.");

        }

        if(resultado>=18.5 & resultado<24.4) {
            txTexto.setText( "Saudavel");
        }
        if(resultado>=24.5 & resultado<30) {
            txTexto.setText(" Sobrepeso.");

        }
        if(resultado>=30 & resultado<35) {
            txTexto.setText(" Obesidade Grau 1");

        }
        if(resultado>=35 & resultado<40) {
            txTexto.setText(" Obesidade Grau 2.");
        }
        if(resultado>=40) {
            txTexto.setText(" Obesidade Grau 3.");
        }


    }
}
