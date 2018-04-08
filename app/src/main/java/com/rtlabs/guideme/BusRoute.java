package com.rtlabs.guideme;

/**
 * Created by thari on 2/6/2018.
 */

public class BusRoute {

    private String busroutename;
    private String Destination;
    private String busroute;

    public BusRoute(String bn,String d, String bnu){
        busroute = bnu;
        busroutename = bn;
        Destination = d;
    }

    public String getBusroutename(){
        return busroutename;
    }

    public String getDestination(){
        return Destination;
    }

    public String getBusroute(){
        return busroute;
    }
}
