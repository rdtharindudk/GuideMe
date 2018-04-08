package com.rtlabs.guideme;

import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    Graph graph = new Graph();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

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

        List<Node> path = map.get(text1).getShortestPath();
        path.add(map.get(text1));
        //ArrayList<String> pathlist = new ArrayList<String>();
        ArrayList<BusRoute> busroutes = new ArrayList<BusRoute>();
        for (int i = 0; i<path.size()-1;i++){
            busroutes.add(routecalc(path.get(i),path.get(i+1)));
        }


        BusRouteAdapter adapter = new BusRouteAdapter(this,busroutes);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        sd.setText(String.format("%.2f",map.get(text1).getDistance())+ " KM");
        //Toast.makeText(getApplicationContext(),path.size(),Toast.LENGTH_SHORT).show();
    }

    public BusRoute routecalc(Node first, Node second){

        if (first.getName().equals("Dehiwala")){
            if (second.getName().equals("Wellawaththa")){
                return new BusRoute("Fort - Moratuwa", second.getName(), "100");
            }
            else {
                return new BusRoute("","","");
            }

        }
        else if (first.getName().equals("Wellawaththa")){
            if (second.getName().equals("Dehiwala")){
                return new BusRoute("Fort - Moratuwa", second.getName(),"100");
            }
            else if (second.getName().equals("Pamankada")){
                return new BusRoute("Narahenpita - Wellawaththa",second.getName(),"141");
            }
            else if (second.getName().equals("Bambalapitiya")){
                return new BusRoute("Fort - Moratuwa", second.getName(),"100");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else if (first.getName().equals("Bambalapitiya")){
            if (second.getName().equals("Wellawaththa")){
                return new BusRoute("Fort - Moratuwa", second.getName(), "100");
            }
            else if (second.getName().equals("Kollupitiya")){
                return new BusRoute("Fort - Moratuwa", second.getName(), "100");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else if (first.getName().equals("Kollupitiya")){
            if (second.getName().equals("Bambalapitiya")){
                return new BusRoute("Fort - Moratuwa", second.getName(), "100");
            }
            else if (second.getName().equals("Public Library")){
                return new BusRoute("Kaduwela - Kollupitiya", second.getName(), "177");
            }
            else if (second.getName().equals("Galle Face")){
                return new BusRoute("Fort - Moratuwa", second.getName(), "100");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else if (first.getName().equals("Galle Face")){
            if (second.getName().equals("Fort")){
                return new BusRoute("Fort - Moratuwa", second.getName(), "100");
            }
            else if (second.getName().equals("Kollupitiya")){
                return new BusRoute("Fort - Moratuwa", second.getName(), "100");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else if (first.getName().equals("Fort")){
            if (second.getName().equals("Galle Face")){
                return new BusRoute("Fort - Moratuwa", second.getName(), "100");
            }
            else if (second.getName().equals("Town Hall")){
                return new BusRoute("Fort - Padukka", second.getName(), "125");
            }
            else if (second.getName().equals("Pettah")){
                return new BusRoute("Fort - Moratuwa", second.getName(), "100");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else if (first.getName().equals("Pettah")){
            if (second.getName().equals("Fort")){
                return new BusRoute("Fort - Moratuwa", second.getName(),"100");
            }
            else if (second.getName().equals("Maradana")){
                return new BusRoute("Fort - Narahenpita", second.getName(), "103");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else if (first.getName().equals("Maradana")){
            if (second.getName().equals("Pettah")){
                return new BusRoute("Fort - Narahenpita", second.getName(), "103");
            }
            else if (second.getName().equals("Town Hall")){
                return new BusRoute("Kollupitiya - Wellampitiya", second.getName(), "140");
            }
            else if (second.getName().equals("Borella")){
                return new BusRoute("Fort - Narahenpita", second.getName(), "103");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else if (first.getName().equals("Borella")){
            if (second.getName().equals("Maradana")){
                return new BusRoute("Fort - Narahenpita", second.getName(), "103");
            }
            else if (second.getName().equals("Narahenpita")){
                return new BusRoute("Fort - Narahenpita", second.getName(), "103");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else if (first.getName().equals("Narahenpita")){
            if (second.getName().equals("Borella")){
                return new BusRoute("Fort - Narahenpita", second.getName(),"103");
            }
            else if (second.getName().equals("Thibirigasyaya")){
                return new BusRoute("Kelaniya - Kohuwala", second.getName(), "135");
            }
            else {
                return new BusRoute("","","");
            }

        }
        else if (first.getName().equals("Thibirigasyaya")){
            if (second.getName().equals("Narahenpita")){
                return new BusRoute("Kelaniya - Kohuwala", second.getName(), "135");
            }
            else if (second.getName().equals("Colombo Campus")){
                return new BusRoute("Fort - Kottawa",second.getName(),"138");
            }
            else if (second.getName().equals("Pamankada")){
                return new BusRoute("Fort - Pitakotuwa", second.getName(), "120");
            }
            else if (second.getName().equals("Kirulapana")){
                return new BusRoute("Fort - Kottawa", second.getName(), "138");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else if (first.getName().equals("Colombo Campus")){
            if (second.getName().equals("Thibirigasyaya")){
                return new BusRoute("Fort - Kottawa", second.getName(), "138");
            }
            else if (second.getName().equals("Public Library")){
                return new BusRoute("Fort - Kottawa", second.getName(), "138");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else if (first.getName().equals("Public Library")){
            if (second.getName().equals("Kollupitiya")){
                return new BusRoute("Kaduwela - Kollupitiya", second.getName(), "177");
            }
            else if (second.getName().equals("Town Hall")){
                return new BusRoute("Fort - Kottawa", second.getName(), "138");
            }
            else if (second.getName().equals("Colombo Campus")){
                return new BusRoute("Fort - Kottawa", second.getName(), "138");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else if (first.getName().equals("Town Hall")){
            if (second.getName().equals("Public Library")){
                return new BusRoute("Fort - Kottawa", second.getName(), "138");
            }
            else if (second.getName().equals("Fort")){
                return new BusRoute("Fort - Kottawa", second.getName(), "138");
            }
            else if (second.getName().equals("Maradana")){
                return new BusRoute("Colombo - Kotahena", second.getName(), "175");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else if (first.getName().equals("Pamankada")){
            if (second.getName().equals("Thibirigasyaya")){
                return new BusRoute("Fort - Horana", second.getName(), "120");
            }
            else if (second.getName().equals("Kirulapana")){
                return new BusRoute("Narahenpita - Wellawaththa", second.getName(), "141");
            }
            else if (second.getName().equals("Kohuwala")){
                return new BusRoute("Kelaniya - Kohuwala", second.getName(), "135");
            }
            else if (second.getName().equals("Wellawaththa")){
                return new BusRoute("Narahenpita - Wellawaththa", second.getName(), "141");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else if (first.getName().equals("Kirulapana")){
            if (second.getName().equals("Pamankada")){
                return new BusRoute("Narahenpita - Wellawaththa", second.getName(), "141");
            }
            else if (second.getName().equals("Nugegoda")){
                return new BusRoute("Fort - Kottawa", second.getName(), "138");
            }
            else if (second.getName().equals("Thibirigasyaya")){
                return new BusRoute("Fort - Kottawa", second.getName(), "138");
            }
            else {
                return new BusRoute("","","");
            }
        }
        else {
            return new BusRoute("","","");
        }
    }

}
