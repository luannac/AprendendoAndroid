package luann.senai.aula3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SegundaTela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela);

        //Recuperaçãode dos valores passados pela classe Intent
        String text = getIntent().getStringExtra("putName");

        //Para recuperar números, é necessário colocar um valor padrão, zero,
        //por exemplo (ou qualquer outro número) para que a variavel nao fique nula
        int number = getIntent().getIntExtra("putNumber",0);

        //Utilização da classe toast (Mensagem na Tela) para saber se o comando
        //deu certo, isto é, se os valores extra foram recuperados corretamente
        Toast.makeText(getApplicationContext(),             //Contexto
                "String from other activity: "+text,    //Mensagem
                Toast.LENGTH_SHORT).show();                 //Duração


        Toast.makeText(getApplicationContext(),             //Contexto
                "Number from other Activity: "+number,  //Mensagem
                Toast.LENGTH_SHORT).show();                 //Duração
    }
}
