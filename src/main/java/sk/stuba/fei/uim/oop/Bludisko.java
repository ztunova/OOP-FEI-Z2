package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.*;

@Setter
@Getter
public class Bludisko /*extends JPanel*/ {
    private int rozmer;
    public ArrayList<Policko> mapa;
    private Policko start;
    private Policko ciel;

   /* public void paint(Graphics g){
        boolean[] hrany;
        int riadok, stlpec;
        g.setColor(Color.BLACK);
        for(Policko pol: this.mapa) {
            hrany= pol.getHrany();
            riadok= pol.getRiadok();
            stlpec= pol.getStlpec();
            if (hrany[0]) {
                g.drawLine(stlpec * 30 + 5, riadok * 30 + 5, stlpec * 30 + 30 + 5, riadok * 30 + 5);
            }
            if (hrany[1]) {
                g.drawLine(stlpec * 30 + 5, riadok * 30 + 30 + 5, stlpec * 30 + 30 + 5, riadok * 30 + 30 + 5);
            }
            if (hrany[2]) {
                g.drawLine(stlpec * 30 + 30 + 5, riadok* 30 + 5, stlpec * 30 + 30 + 5, riadok * 30 + 30 + 5);
            }
            if (hrany[3]) {
                g.drawLine(stlpec * 30 + 5, riadok * 30 + 5, stlpec * 30 + 5, riadok * 30 + 30 + 5);
            }
        }
        stlpec= start.getStlpec();
        riadok= start.getRiadok();
        g.setColor(Color.MAGENTA);
        g.fillRect(stlpec*30+6, riadok*30+6, 29,29);
        stlpec= ciel.getStlpec();
        riadok= ciel.getRiadok();
        g.setColor(Color.GREEN);
        g.fillRect(stlpec*30+6, riadok*30+6, 29,29);
    }*/

    public void initBludisko(){
        int pocitadlo= 0;
        //ArrayList<Policko> mapa= new ArrayList<Policko>(rozmer*rozmer);
        ArrayList<Integer> susedia= new ArrayList<Integer>();

        for (int riadok= 0; riadok< rozmer; riadok++){
            for (int stlpec= 0; stlpec< rozmer; stlpec++){
                susedia.clear();
                Policko novePolicko= new Policko(pocitadlo, riadok, stlpec);
                //pocitadlo++;
                this.mapa.add(novePolicko);

                if(riadok == 0){
                    if (stlpec == 0) {
                        susedia.add(pocitadlo + 1);
                        susedia.add(pocitadlo + rozmer);
                    }
                    else if (stlpec == rozmer-1){
                        susedia.add(pocitadlo-1);
                        susedia.add(pocitadlo+rozmer);
                    }
                    else{
                        susedia.add(pocitadlo-1);
                        susedia.add(pocitadlo + 1);
                        susedia.add(pocitadlo+ rozmer);
                    }
                }
                else if(riadok == rozmer-1){
                    if (stlpec == 0) {
                        susedia.add(pocitadlo + 1);
                        susedia.add(pocitadlo - rozmer);
                    }
                    else if (stlpec == rozmer-1){
                        susedia.add(pocitadlo -1);
                        susedia.add(pocitadlo -rozmer);
                    }
                    else{
                        susedia.add(pocitadlo-1);
                        susedia.add(pocitadlo + 1);
                        susedia.add(pocitadlo -rozmer);
                    }
                }
                else{
                    if (stlpec == 0) {
                        susedia.add(pocitadlo -rozmer);
                        susedia.add(pocitadlo + 1);
                        susedia.add(pocitadlo + rozmer);
                    }
                    else if (stlpec == rozmer -1){
                        susedia.add(pocitadlo -rozmer);
                        susedia.add(pocitadlo -1);
                        susedia.add(pocitadlo +rozmer);
                    }
                    else{
                        susedia.add(pocitadlo -rozmer);
                        susedia.add(pocitadlo -1);
                        susedia.add(pocitadlo + 1);
                        susedia.add(pocitadlo +rozmer);
                    }
                }
                Collections.sort(susedia);
                novePolicko.setZoznamSusedov(susedia);
                pocitadlo++;
            }
        }
    }

    public Policko vyberSuseda(ArrayList<Integer> vsetciSusedia, Random rand, boolean[] navstivene){
        int pocetVsetkychSusedov= vsetciSusedia.size();
        int vybraneID;
        ArrayList<Integer> skusane= new ArrayList<Integer>();
        Policko vybranySused;

        while(true){
            vybraneID= vsetciSusedia.get(rand.nextInt(pocetVsetkychSusedov));
            if(!skusane.contains(vybraneID)){
                if(!navstivene[vybraneID]){
                    vybranySused= mapa.get(vybraneID);
                    return vybranySused;
                }
                skusane.add(vybraneID);
                Collections.sort(skusane);
            }
            if(vsetciSusedia.equals(skusane)){
                return null;
            }
        }
    }

    public void randomDFS(){
        Random rand= new Random();
        int index;
        Policko nahodnySused;
        ArrayList<Integer> vsetciSusedia;
        boolean[] navstivene= new boolean[rozmer*rozmer];
        Arrays.fill(navstivene, false);

        Policko vybrane= mapa.get(0);
        Stack<Policko> stack= new Stack<Policko>();
        stack.push(vybrane);

        while (!stack.empty()){
            index= vybrane.getPoradie();
            navstivene[index]= true;

            vsetciSusedia= vybrane.getZoznamSusedov();
            nahodnySused= vyberSuseda(vsetciSusedia, rand, navstivene);

            if(nahodnySused != null){
                vybrane.pridajSpojenie(nahodnySused);
                nahodnySused.pridajSpojenie(vybrane);
                vybrane= nahodnySused;
                stack.push(vybrane);
            }
            else{
                vybrane= stack.peek();
                stack.pop();
            }
        }
    }

    public void generujBludisko(){
        initBludisko();
        randomDFS();
        for (Policko policko : this.mapa){
            //System.out.println(policko.getPoradie());
            policko.urciHrany();
        }
        this.start= mapa.get(0);
        Random r= new Random();
        //nahodne policko v druhej polovici bludiska
        int indexCiel= r.nextInt(14*14-(14*14)/2)+(14*14)/2;
        this.ciel= mapa.get(indexCiel);
    }

    public Bludisko(int n){
        this.rozmer= n;
        this.mapa= new ArrayList<Policko>();
    }

    public Bludisko(){
        this.mapa= new ArrayList<Policko>();
    }
}
