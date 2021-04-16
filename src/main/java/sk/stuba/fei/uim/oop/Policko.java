package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Setter
@Getter
public class Policko {
    private int riadok;
    private int stlpec;
    //ArrayList<Policko> zoznamSusedov;
    ArrayList<Integer> zoznamSusedov;

    public void setZoznamSusedov(ArrayList<Integer> zoznamSusedov) {
        this.zoznamSusedov.addAll(zoznamSusedov);
    }

    public Policko(int riadok, int stlpec){
        this.riadok= riadok;
        this.stlpec= stlpec;
        //this.zoznamSusedov= new ArrayList<Policko>();
        this.zoznamSusedov= new ArrayList<Integer>();
    }

    public Policko(){
        //this.zoznamSusedov= new ArrayList<Policko>();
        this.zoznamSusedov= new ArrayList<Integer>();
    }

}
