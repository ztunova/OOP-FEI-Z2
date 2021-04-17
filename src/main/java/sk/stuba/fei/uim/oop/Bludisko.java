package sk.stuba.fei.uim.oop;

import java.util.*;

public class Bludisko {
    private int rozmer;
    private ArrayList<Policko> mapa;

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

    public void generujBludisko(){
        int index, nahodnySused;
        ArrayList<Integer> vsetciSusedia;
        boolean[] navstivene= new boolean[rozmer*rozmer];
        Arrays.fill(navstivene, false);

        Policko vybrane= mapa.get(0);
        Stack<Policko> stack= new Stack<Policko>();
        stack.push(vybrane);

        Policko v= vyberSuseda(vybrane.getZoznamSusedov(), new Random(), navstivene);
        System.out.println("-----");

 /*       while (!stack.empty()){
            index= vybrane.getPoradie();
            navstivene[index]= true;

            vsetciSusedia= vybrane.getZoznamSusedov();

        }*/

    }

    public Bludisko(int n){
        this.rozmer= n;
        this.mapa= new ArrayList<Policko>();
    }

    public Bludisko(){
        this.mapa= new ArrayList<Policko>();
    }
}
