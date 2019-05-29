package luann.app.aula6;


import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.lang.reflect.Field;

public class Main2Activity extends AppCompatActivity {
    ImageButton btNext,btBefore,btStop,btPlay;
    SeekBar baBarra;
    Switch swFavorito;
    TextView txNome;

    //Class used to search resources in the arquive.
    private AssetFileDescriptor arquivo;

    //Class contains methods to play the mp3
    private MediaPlayer player = new MediaPlayer();



    Field[] musicasPastaRaw;
    int[] listaMusicas;
    int indiceMusicaAtual = 0;

    //variable to count the seconds of the mp3
    private int seconds=0;
    private boolean booplay =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btNext = findViewById(R.id.btNext);
        btBefore = findViewById(R.id.btBefore);
        btPlay = findViewById(R.id.btPlay);
        btStop = findViewById(R.id.btStop);
        baBarra = findViewById(R.id.baBarra);
        swFavorito = findViewById(R.id.swFavorito);
        txNome = findViewById(R.id.txNome);

        //Busca todas as músicas da pasta Raw
        musicasPastaRaw = R.raw.class.getFields();
        //Passa as músicas para um vetor de int (porque os IDs são do tipo int)
        listaMusicas = new int[musicasPastaRaw.length];

        //Get the file for MediaPlayer
        //player.setDataSource(arquivo.getFileDescriptor());
        //player = MediaPlayer.create(getApplicationContext(),R.raw.favela);
        try {
            //Atribui a música da posição escolhida ao player
            player = MediaPlayer.create(getApplicationContext(),
                    musicasPastaRaw[indiceMusicaAtual].getInt(musicasPastaRaw[indiceMusicaAtual]));
            baBarra.setMax(player.getDuration());
           verificaFavorito();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try{
            txNome.setText(getIntent().getStringExtra("nome"));
        }catch (Exception e){
            e.printStackTrace();
        }


        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()) { //Se estiver tocando
                    player.pause(); //Interrompe a música
                    player.stop();  //Cancela a música
                    player.reset(); //Reinicia o player

                    //Verifica se o índice atual da música ainda está dentro
                    //da quantidade de músicas disponíveis na pasta
                    if(indiceMusicaAtual < listaMusicas.length-1){
                        //Caso esteja dentro do tamanho, avança
                        indiceMusicaAtual++;
                    }else{
                        //Caso não esteja dentro do tamanho, volta
                        //para a primeira música
                        indiceMusicaAtual = 0;
                    }

                    try {
                        //Atribui a música da posição escolhida ao player
                        player = MediaPlayer.create(getApplicationContext(),
                                musicasPastaRaw[indiceMusicaAtual].getInt(musicasPastaRaw[indiceMusicaAtual]));
                        baBarra.setMax(player.getDuration());
                        verificaFavorito();

                        player.start(); //Inicia a música

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        btBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()) { //Se estiver tocando
                    player.pause(); //Interrompe a música
                    player.stop();  //Cancela a música
                    player.reset(); //Reinicia o player

                    //Verifica se o índice atual da música ainda está dentro
                    //da quantidade de músicas disponíveis na pasta
                    if(indiceMusicaAtual > 0){
                        //Caso esteja dentro do tamanho, avança
                        indiceMusicaAtual--;
                    }else{
                        //Caso não esteja dentro do tamanho, volta
                        //para a primeira música
                        indiceMusicaAtual = listaMusicas.length-1;
                    }

                    try {
                        //Atribui a música da posição escolhida ao player
                        player = MediaPlayer.create(getApplicationContext(),
                                musicasPastaRaw[indiceMusicaAtual].getInt(musicasPastaRaw[indiceMusicaAtual]));
                        baBarra.setMax(player.getDuration());
                        verificaFavorito();


                        player.start(); //Inicia a música

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(booplay){
                    try{
                        //Try if is playing a music
                        if(!player.isPlaying()){
                            //Search the file in the assets
                            //arquivo = getAssets().openFd("raw/senna.mp3");

                            //Start to play the music
                            //player.prepare();//Load the music
                            player.seekTo(seconds);
                            player.start();


                            btPlay.setImageResource(R.drawable.pause);
                            booplay =false;
                        }

                    }catch(Exception exc) {
                        exc.printStackTrace();
                    }
                }else {
                    //Pause
                    if(player.isPlaying()){
                        seconds = player.getCurrentPosition();
                        player.pause();
                        btPlay.setImageResource(R.drawable.play);
                        booplay=true;
                    }else{
                        player.seekTo(seconds);
                        player.start();

                    }
                }
            }
        });
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Stop
                seconds=0;
                player.pause();
                player.seekTo(0);

                btPlay.setImageResource(R.drawable.play);
                booplay =false;

            }
        });
        baBarra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.pause();
                player.seekTo(baBarra.getProgress());
                player.start();
            }
        });
        swFavorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                BancoDeDados db = new BancoDeDados(getApplicationContext(),1);
                if(swFavorito.isChecked()){
                    db.setFavorito(indiceMusicaAtual);
                }else{
                    db.deleteFavorito(indiceMusicaAtual);
                }
                db.close();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    baBarra.setProgress(player.getCurrentPosition());
                    try{
                        Thread.sleep(1000);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void verificaFavorito(){
        BancoDeDados db = new BancoDeDados(getApplicationContext(),1);
        if(db.isFavorito(indiceMusicaAtual)){
            swFavorito.setChecked(true);
        }else{
            swFavorito.setChecked(false);
        }
        db.close();
    }
}
