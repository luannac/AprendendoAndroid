package luann.app.aula6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch switchMusica;
    private EditText campoNome, campoSenha, campoUsuario;
    private Button btCadastro,btAcesso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        switchMusica = findViewById(R.id.switchMusica);

        campoNome = findViewById(R.id.campoNome);
        campoUsuario = findViewById(R.id.campoUsuario);
        campoSenha = findViewById(R.id.campoSenha);

        btAcesso = findViewById(R.id.btLogin);
        btCadastro = findViewById(R.id.btCadastrar);

        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(campoNome.getVisibility()==View.VISIBLE){

                    //Cria objeto do banco de dados
                    BancoDeDados db = new BancoDeDados(getApplicationContext(),1);

                    if(db.cadastraUsuario(campoUsuario.getText().toString(),campoNome.getText().toString(),campoSenha.getText().toString())){
                        Toast.makeText(getApplicationContext(),
                                "Dados Cadastrados",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Erro - Não cadastrado!",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    campoNome.setVisibility(View.VISIBLE);
                }
            }
        });

        btAcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoDeDados db = new BancoDeDados(getApplicationContext(),1);

                if(db.verificaAcesso(campoUsuario.getText().toString(),campoSenha.getText().toString())){
                    Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                    intent.putExtra("nome",db.getNome(campoUsuario.getText().toString()));
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Senha Errada - MotherFucker!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //COnfiguração do evento que será executado quando o estado do switch
        //for alterado, ou seja, quando é clicando para habilitar ou desabilitar.
        switchMusica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 //Utilizar a classe SharePreference para salvar o estado do switch
                SharedPreferences.Editor gravacao = getSharedPreferences("arquivo",MODE_PRIVATE).edit();

                //Gravar dados no arquivo
                //grava em um campo opcaoSwitch se está habilitado ou não
                gravacao.putBoolean("opcaoSwitch",isChecked);

                //Comando para fechar o "arquivo" e salvar as configurações
                gravacao.commit();
            }
        });

        //Recuperar o estado do switch que foi salvo
        SharedPreferences leitura =
                getSharedPreferences("arquivo",MODE_PRIVATE);

        //Atribuir o valor que será em "opcaoSwitch" para um variavel
        boolean opcaoSwitch = leitura.getBoolean("opcaoSwitch",true);

        //Controla o estado do switch de acordo com o que foi gravado
        if(opcaoSwitch){
            switchMusica.setChecked(true);
        }else{
            switchMusica.setChecked(false);
        }
    }
}
