package senai.luann.aula1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Textwiew (Hello World)
    TextView campoSaida;
    //Button Change
    Button button;

    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Vincular objeto a componente da Tela
        campoSaida = findViewById(R.id.campoSaida);
        button = findViewById(R.id.botao);
        editText = findViewById(R.id.newText);

        //Adicionar ação /evento ao botao
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campoSaida.setText(editText.getText());
            }
        });
    }
}
