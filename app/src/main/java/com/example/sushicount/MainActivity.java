package com.example.sushicount;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    final int MAXORDER = 1000;
    SortedSet<Integer> set = new TreeSet<Integer>();
    int[] totNum = new int[MAXORDER];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addSushi(View view) {
        TextView sushiNum = findViewById(R.id.sushiNum);
        String[] numSushi = (sushiNum.getText().toString()).split(",");
        sushiNum.setText("");

        for (String order : numSushi) {
            int start = 0, end = order.length() - 1;
            while (start < order.length() && end > start && end < order.length() && order.charAt(start) == ' ' & order.charAt(end) == ' ') {
                if (order.charAt(start) == ' ') start++;
                if (order.charAt(end) == ' ') end--;
            }

            String[] qtSushi = (order.substring(start, end + 1)).split(" ");
            int[] finalData = {-1, 1};
            int cont = 0;

            for (int i = 0; cont < 2 & i < qtSushi.length; i++) {
                try {
                    finalData[cont] = Integer.parseInt(qtSushi[i]);
                    cont++;
                } catch (Exception e) {
                }
            }

            if (cont > 0 & finalData[0] > -1 & finalData[0] < MAXORDER & finalData[1] > 0) {
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
