package luann.app.aula5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btImc,btVogal,btSoma;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        btImc = findViewById(R.id.btImc);
        btSoma = findViewById(R.id.btSoma);
        btVogal = findViewById(R.id.btVogal);

        btVogal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String frase = editText.getText().toString();
                char sChar;
                int iVogal=0,iConsoante=0;
                for(int i=0;i<frase.length();i++){
                    sChar = frase.charAt(i);
                    if(sChar == 'a' ||sChar == 'e' ||sChar == 'i' ||sChar == 'o' ||sChar == 'u'){
                        iVogal++;
                    }else{
                        iConsoante++;
                    }
                }
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                build.setTitle("Vogal");
                build.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO: handle the OK
                    }
                });
                build.setMessage("Vogais:"+iVogal+"\nConsoantes:"+iConsoante);
                AlertDialog alertDialog = build.create();
                alertDialog.show();

            }
        });

        btSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean _bConver = false;
                int _iNumero = 0;
                int _iResposta=0;

                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                build.setTitle("Soma")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // TODO: handle the OK
                            }
                        });
                try {
                    _iNumero = Integer.parseInt(editText.getText().toString());
                    _bConver = true;
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),             //Contexto
                            e.toString(),  //Mensagem
                            Toast.LENGTH_SHORT).show();                 //Duração
                }
                if (!_bConver){
                    build.setMessage("Não é numero");
                }else{
                    for(int i=0;i<=_iNumero;i++){
                        _iResposta+=i;
                    }
                    build.setMessage("O resultado de todos o numeros anteriores é:"+_iResposta);
                }

                    AlertDialog alertDialog = build.create();
                alertDialog.show();
            }
        });

        btImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),actImc.class);
                startActivity(i);
            }
        });

    }
}
