package luann.senai.aula3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button bNewScreen;
    private Button bCall,bSite,bCamera;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bNewScreen = findViewById(R.id.newScreen);
        bCall = findViewById(R.id.bCall);
        bSite = findViewById(R.id.bSite);
        bCamera = findViewById(R.id.bCamera);
        image = findViewById(R.id.iPhoto);

        bNewScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Utilização da classe Intent para abrir uma nova tela ou algum
                //app/recurso nativo do android(contato camera etc)

                Intent i = new Intent(getApplicationContext(),SegundaTela.class);

                //Passagem entre as telas
                i.putExtra("putName","Initial Text");
                i.putExtra("putNumber",100);

                //para abrir a tela, usamos o comando startActivity
                startActivity(i);
            }
        });
        bCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Classe Intent para acessar um recurso do Android
                //é necessário indicar qual ação usaremos (DIAL == Discar)
                // e qual o valor usado no formato de recurso, ou seja, Uri.parse()
                //que converte um texto em recurso
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 987654321"));
                startActivity(i);
            }
        });
        bSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
                startActivity(i);
            }
        });
        bCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comand to open the camera cellphone
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //Before open the Camera its possible to test if its already open or in use by another application
                // in case that´s true,its not possible to open the resource and the application would break.

                if(i.resolveActivity(getPackageManager())!=null){
                    //The method "startActivityForResult" will open the camera and wait the answer of  the resource;
                    //There is needed to send a code(any number), in case its number one, to identify the answer of the resource;
                    startActivityForResult(i,1);
                }
            }
        });
    }

    //The  method "onActivityResult" its called anytime that a resource return something to the screen mainActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode,resultCode,data);

        //Test to see if the resource its returning something of the camera
        if(requestCode==1) {//test if the return its of the camera
            if (resultCode == RESULT_OK) {//test if had some return with content

                //The object "data" has the return of the camera, it means that keep the image of camera(and a lot others things)
                Bitmap imagem = (Bitmap) data.getExtras().get("data");

                image.setRotation(90);

                //Put the image on the screen ImageView component
                image.setImageBitmap(imagem);
            }
        }
    }
}
