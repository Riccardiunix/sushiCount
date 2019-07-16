package com.example.sushicount;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Set<Integer> set = new HashSet<Integer>();
    int[] totNum = new int[1000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        TextView sushiNum = findViewById(R.id.sushiNum);

        String[] numSushi = (sushiNum.getText().toString()).split(",");

        sushiNum.setText("");

        for (int i = 0; i < numSushi.length; i++) {
            String[] qtSushi = numSushi[i].split(" ");
            int[] finalData = {0, 1};
            int cont = 0;

            for (int j = 0; cont < 2 && j < qtSushi.length; j++) {
                try {
                    finalData[cont] = Integer.parseInt(qtSushi[j]);
                    cont++;
                } catch (Exception e) {}
            }

            if (cont > 0 & finalData[0] >= 0 & finalData[0] < 1000 & finalData[1] > 0){
                if (totNum[finalData[0]] == 0) set.add(finalData[0]);
                totNum[finalData[0]] += finalData[1];
            }
        }

        String totString = "";
        for (Integer i : set) totString += i + " : " + totNum[i] + "\n";

        TextView totText = findViewById(R.id.totText);
        totText.setText(totString);
        totText.setMovementMethod(new ScrollingMovementMethod());
    }
}
