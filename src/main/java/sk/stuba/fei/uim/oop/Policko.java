package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

@Setter
@Getter
public class Policko {
    private int poradie;
    private int riadok;
    private int stlpec;
    private boolean[] hrany;
    ArrayList<Policko> spojenie;
    ArrayList<Integer> zoznamSusedov;

    public void urciHrany(){
        for(Policko sused : this.spojenie){
            if(sused.getRiadok() == this.riadok -1){
                hrany[0] = false;
            }
            if(sused.getRiadok() == this.riadok +1){
                hrany[1] = false;
            }
            if(sused.getStlpec() == this.stlpec +1){
                hrany[2] = false;
            }
            if(sused.getStlpec() == this.stlpec -1){
                hrany[3] = false;
            }
        }
    }

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
        this.hrany= new boolean[4];
        Arrays.fill(hrany, true);
    }

    public Policko(){
        this.spojenie= new ArrayList<Policko>();
        this.zoznamSusedov= new ArrayList<Integer>();
        this.hrany= new boolean[4];
        Arrays.fill(hrany, true);
    }

}
