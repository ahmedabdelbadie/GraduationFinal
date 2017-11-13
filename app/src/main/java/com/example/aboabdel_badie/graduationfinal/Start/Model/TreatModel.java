package com.example.aboabdel_badie.graduationfinal.Start.Model;

/**
 * Created by Abo Abdel-Badie on 6/18/2017.
 */

public class TreatModel {
    private String id ;
    private  String  name   ;
    private String  time ;

    public TreatModel(String id, String name, String time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

}
