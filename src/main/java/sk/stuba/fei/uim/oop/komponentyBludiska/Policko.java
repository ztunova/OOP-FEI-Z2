package sk.stuba.fei.uim.oop.komponentyBludiska;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Trieda Policko reprezentuje jednu bunku bludiska. Kazde policko ma ciselne oznacenie riadku a stlpca v ktorom sa
 * nachadza (ak si bludisko predstavime ako mriezku), kolke v poradi bolo vytvorene (cisluje sa po riadkoch, zhora dole,
 * zlava do prava) - toto poradie potom reprezentuje index v mape bludiska (ta je ArrayList Policok).
 * zoznamSusedov obsahuje poradove cisla vsetkych moznych susedov (max 4: horny/dolny/lavy/pravy)
 * spojenie obsahuje policka s ktorymi je dane policko spojene -> v bludisku medzi nimi nie je hrana, da sa medzi nimi
 * prechadzat
 * hrany je pole boolean hodnot (na zaciatku vsetky true), ktore reprezentuje z ktorej strany ma policko hranu -
 * fixne poradie je [horna, dolna, prava, lava] - podla toho na ktorych poziciach je true, tam ma policko hranu, cez
 * ktoru sa neda prejst. Kde je false, tam hrana nie je
 * metoda urciHrany toto pole naplna - prejde zoznamom policok ktore su s danym polickom spojene, identifikuje ci je tento
 * sused z hornej/dolnej/pravej/ lavej strany a podla toho nastavi hodnotu na false v poli hran
 */

@Setter
@Getter
public class Policko{
    private int poradie;
    private int riadok;
    private int stlpec;
    private boolean[] hrany;
    private ArrayList<Policko> spojenie;
    private ArrayList<Integer> zoznamSusedov;

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
