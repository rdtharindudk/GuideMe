package com.rtlabs.guideme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    Graph graph = new Graph();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
        graph.addNode(gf);
        graph.addNode(f);
        graph.addNode(p);
        graph.addNode(M);
        graph.addNode(TH);
        graph.addNode(PL);
        graph.addNode(K);
        graph.addNode(B);
        graph.addNode(W);
        graph.addNode(P);
        graph.addNode(T);
        graph.addNode(CC);
        graph.addNode(N);
        graph.addNode(Bo);
        graph.addNode(Ki);
        graph.addNode(Nu);
        graph.addNode(Ko);
        graph.addNode(D);
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

        Bundle b = this.getIntent().getExtras();
        String[] nodelist = b.getStringArray("z");
        String text = nodelist[0];
        String text1 = nodelist[1];

        HashMap<String,Node> map = new HashMap<String,Node>();

        map.put("Galle Face",gf);
        map.put("Fort",f);
        map.put("Pettah",P);
        map.put("Maradana",M);
        map.put("Town Hall",TH);
        map.put("Public Library",PL);
        map.put("Kollupitiya",K);
        map.put("Bambalapitiya",B);
        map.put("Wellawaththa",W);
        map.put("Pamankada",P);
        map.put("Thibirigasyaya",T);
        map.put("Colombo Campus",CC);
        map.put("Narahenpita",N);
        map.put("Borella",Bo);
        map.put("Kirulapana",Ki);
        map.put("Nugegoda",Nu);
        map.put("Kohuwala",Ko);
        map.put("Dehiwala",D);

        graph = Dijkstra.calculateShortestPathFromSource(graph, map.get(text));

        TextView sd = findViewById(R.id.sd);
        sd.setText(String.format("%.2f",map.get(text1).getDistance())+ " KM");
        List<Node> path = map.get(text1).getShortestPath();
        ArrayList<String> pathlist = new ArrayList<String>();

        for (Node node:path){
            pathlist.add(node.getName());
        }
        pathlist.add(map.get(text1).getName());


        ListView lv = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pathlist);
        lv.setAdapter(arrayAdapter);
    }


    public void di(Graph graph,Node node){
        Dijkstra.calculateShortestPathFromSource(graph, node);
    }
}
