package luann.senai.aula4;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private ImageButton ibPlay,ibNext,ibPrevious;

    //Class used to search resources in the arquive.
    private AssetFileDescriptor arquivo;

    //Class contains methods to play the mp3
    private MediaPlayer player = new MediaPlayer();

    //variable to count the seconds of the mp3
    private int seconds=0;
    private boolean booplay =true;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG_IDENTIFICAÇÂO","Entrou no onCreate");

        ibPlay = findViewById(R.id.bPlay);
        ibNext = findViewById(R.id.bNext);
        ibPrevious = findViewById(R.id.bBefore);

        progressBar = findViewById(R.id.progressBar);
        Thread thread = new Thread();


        //Get the file for MediaPlayer
        //player.setDataSource(arquivo.getFileDescriptor());
        player = MediaPlayer.create(getApplicationContext(),R.raw.favela);

        ibPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ibPlay.setOnClickListener(new View.OnClickListener() {
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


                            ibPlay.setImageResource(R.drawable.pause);
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
                        ibPlay.setImageResource(R.drawable.play);
                        booplay=true;
                    }else{
                        player.seekTo(seconds);
                        player.start();

                    }
                }
            }
        });
        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Stop
                seconds=0;
                player.pause();
                player.stop();
                player.reset();
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG_IDENTIFICAÇÂO","Entrou no onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG_IDENTIFICAÇÂO","Entrou no onResume");
        if(seconds!=0){
            player.seekTo(seconds);
            player.start();


            ibPlay.setImageResource(R.drawable.pause);
            booplay =false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG_IDENTIFICAÇÂO","Entrou no onStop");
        seconds = player.getCurrentPosition();
        player.stop();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG_IDENTIFICAÇÂO","Entrou no onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG_IDENTIFICAÇÂO","Entrou no onDestroy");
    }

}

