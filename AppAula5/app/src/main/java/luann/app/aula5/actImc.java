package luann.app.aula5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class actImc extends AppCompatActivity {
    EditText txPeso,txAltura;
    Button calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_imc);
        txAltura = findViewById(R.id.txbAltura);
        txPeso = findViewById(R.id.txbPeso);
        calcular = findViewById(R.id.btCalcular);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),actImcResult.class);
                i.putExtra("imc",Double.parseDouble(txPeso.getText().toString())/Math.pow(Double.parseDouble(txAltura.getText().toString()),2));
                startActivity(i);
            }
        });
    }
}
