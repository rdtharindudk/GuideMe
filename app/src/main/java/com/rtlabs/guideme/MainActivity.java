package com.rtlabs.guideme;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLES10;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list = new ArrayList<String>();

        final Node gf = new Node("Galle Face");
        final Node f = new Node("Fort");
        final Node p = new Node("Pettah");
        final Node M = new Node("Maradana");
        final Node TH = new Node("Town Hall");
        final Node PL = new Node("Public Library");
        final Node K = new Node("Kollupitiya");
        final Node B = new Node("Bambalapitiya");
        final Node W = new Node("Wellawaththa");
        final Node P = new Node("Pamankada");
        final Node T = new Node("Thibirigasyaya");
        final Node CC = new Node("Colombo Campus");
        final Node N = new Node("Narahenpita");
        final Node Bo = new Node("Borella");
        final Node Ki = new Node("Kirulapana");
        final Node Nu = new Node("Nugegoda");
        final Node Ko = new Node("Kohuwala");
        final Node D = new Node("Dehiwala");

        list.add(gf.getName());
        list.add(f.getName());
        list.add(p.getName());
        list.add(M.getName());
        list.add(TH.getName());
        list.add(PL.getName());
        list.add(K.getName());
        list.add(B.getName());
        list.add(W.getName());
        list.add(P.getName());
        list.add(T.getName());
        list.add(CC.getName());
        list.add(N.getName());
        list.add(Bo.getName());
        list.add(Ki.getName());
        list.add(Nu.getName());
        list.add(Ko.getName());
        list.add(D.getName());


        gf.addDestination(f, 2.2);
        gf.addDestination(K, 5.2);

        K.addDestination(PL, 3.0);
        K.addDestination(B, 2.1);
        K.addDestination(gf, 5.2);

        B.addDestination(K, 2.1);
        B.addDestination(W, 1.6);

        W.addDestination(D, 4.2);
        W.addDestination(B, 1.6);
        W.addDestination(P, 1.1);

        f.addDestination(gf, 2.2);
        f.addDestination(p, 1.2);
        f.addDestination(TH, 4.8);

        TH.addDestination(f, 4.8);
        TH.addDestination(PL, 1.5);
        TH.addDestination(M, 2.1);

        PL.addDestination(K, 3.0);
        PL.addDestination(CC, 2.1);
        PL.addDestination(TH, 1.5);

        CC.addDestination(PL, 2.1);
        CC.addDestination(T, 2.3);

        T.addDestination(CC, 2.3);
        T.addDestination(N, 2.5);
        T.addDestination(Ki, 4.0);
        T.addDestination(P, 2.9);

        P.addDestination(W, 1.1);
        P.addDestination(Ko, 3.2);
        P.addDestination(Ki, 1.5);
        P.addDestination(T, 2.9);

        Ki.addDestination(T, 4.0);
        Ki.addDestination(Nu, 4.5);
        Ki.addDestination(P, 1.5);

        P.addDestination(f, 1.2);
        p.addDestination(M, 3.6);

        M.addDestination(p, 3.6);
        M.addDestination(TH, 1.5);
        M.addDestination(Bo, 2.1);

        Bo.addDestination(M, 2.1);
        Bo.addDestination(N, 2.5);

        N.addDestination(Bo, 2.5);
        N.addDestination(T, 2.5);


        final Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);
        final Spinner spinner2 = findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter);



        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = spinner.getSelectedItem().toString();
                String t = spinner2.getSelectedItem().toString();
                String[] nodelist = new String[2];
                nodelist[0]=text;
                nodelist[1]=t;
                send(nodelist);

            }
        });
    }

    public void send(String[] nodelist){
        Bundle b = new Bundle();
        b.putStringArray("z",nodelist);
        Intent intent = new Intent(this, Main3Activity.class);
        intent.putExtras(b);
        startActivity(intent);
    }


    public void di(Graph graph,Node node){
        Dijkstra.calculateShortestPathFromSource(graph, node);
    }


}
