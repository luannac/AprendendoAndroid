package luann.senai.aula2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView numbers ;
    TextView result;
    Button add ;
    Button show;
    EditText receptor;

    ArrayList<Integer> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numbers = findViewById(R.id.textView1);
        result= findViewById(R.id.textView2);
        add = findViewById(R.id.buttonadd);
        show= findViewById(R.id.buttonshow);
        receptor= findViewById(R.id.editText);

        array = new ArrayList<>();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array.add(Integer.parseInt(receptor.getText().toString()));
                //numbers.setText(receptor.getText());
                receptor.setText("");
                numberList();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(avalia());
            }
        });

    }
    private void numberList(){
        if(array.size()!=0) {
            String numers = new String();
            for (int i = 0; i < array.size(); i++) {
                numers += array.get(i)+"  ";
            }
            numbers.setText(numers);
        }else{
            numbers.setText("nada!");
        }
    }
    private String avalia(){
        if(array.size()!=0) {
            int higher=0,lower=array.get(0);
            for (int i = 0; i < array.size(); i++) {
                if(array.get(i)>higher)
                    higher=array.get(i);
                if(array.get(i)<lower)
                    lower = array.get(i);
            }
            return "Higher = "+higher+"\nLower = "+lower;
        }else{
            return "nada!";
        }
    }
}
