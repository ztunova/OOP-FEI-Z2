package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Setter
@Getter
public class Policko {
    private int poradie;
    private int riadok;
    private int stlpec;
    ArrayList<Policko> spojenie;
    ArrayList<Integer> zoznamSusedov;

    public void pridajSpojenie(Policko spoj){
        this.spojenie.add(spoj);
    }

    public void setZoznamSusedov(ArrayList<Integer> zoznamSusedov) {
        this.zoznamSusedov.addAll(zoznamSusedov);
    }

    public Policko(int poradie, int riadok, int stlpec){
        this.poradie= poradie;
        this.riadok= riadok;
        this.stlpec= stlpec;
        this.spojenie= new ArrayList<Policko>();
        this.zoznamSusedov= new ArrayList<Integer>();
    }

    public Policko(){
        this.spojenie= new ArrayList<Policko>();
        this.zoznamSusedov= new ArrayList<Integer>();
    }

}
