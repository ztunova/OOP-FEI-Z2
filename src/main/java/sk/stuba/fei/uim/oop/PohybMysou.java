package sk.stuba.fei.uim.oop;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PohybMysou implements MouseListener {
    Grafika g;
    ArrayList<Policko> priamaCestaStlpec;
    ArrayList<Policko> priamaCestaRiadok;
    Policko sused;
    int poradieSuseda;

    public void zistiCestuStlpec(){
        this.priamaCestaStlpec.clear();
        System.out.println("zistenie cesty stlpec");
        Policko vychodisko= g.getAktPoziciaVeze();
        Policko akt= vychodisko;

        System.out.println("pred 1.while");

        while(!akt.getHrany()[0]){
            System.out.println("vnutri while1");
            poradieSuseda= akt.getPoradie() - g.getBludisko().getRozmer();
            sused= g.getBludisko().getMapa().get(poradieSuseda);
            this.priamaCestaStlpec.add(sused);
            akt= sused;
            System.out.println("koniec while");
        }

        System.out.println("medzi while 1");
        akt= vychodisko;
        System.out.println("medzi while 2");

        while (!akt.getHrany()[1]){
            poradieSuseda= akt.getPoradie() + g.getBludisko().getRozmer();
            sused= g.getBludisko().getMapa().get(poradieSuseda);
            this.priamaCestaStlpec.add(sused);
            akt= sused;
        }
        System.out.println("koniec zistenia cesty stlpec");
    }

    public void zistiCestuRiadok(){
        this.priamaCestaRiadok.clear();
        System.out.println("zistenie cesty stlpec");
        Policko vychodisko= g.getAktPoziciaVeze();
        Policko akt= vychodisko;

        System.out.println("pred 1.while");

        while(!akt.getHrany()[2]){
            System.out.println("vnutri while1");
            poradieSuseda= akt.getPoradie() +1;
            sused= g.getBludisko().getMapa().get(poradieSuseda);
            this.priamaCestaRiadok.add(sused);
            akt= sused;
            System.out.println("koniec while");
        }

        System.out.println("medzi while 1");
        akt= vychodisko;
        System.out.println("medzi while 2");

        while (!akt.getHrany()[3]){
            poradieSuseda= akt.getPoradie() -1;
            sused= g.getBludisko().getMapa().get(poradieSuseda);
            this.priamaCestaRiadok.add(sused);
            akt= sused;
        }
        System.out.println("koniec zistenia cesty stlpec");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ArrayList<Policko> mapa= g.getBludisko().getMapa();
        int x= e.getX();
        int y= e.getY();
        int riadok= y/30;
        int stlpec= x/30;
        int poradie= riadok*14 + stlpec;

        System.out.println("Klik mysou x: "+ x + " y: " +y +" riadok: "+riadok+" stlpec: "+stlpec +" poradie: "+ poradie);

        Policko vybrane= mapa.get(poradie);

        if(vybrane == g.getAktPoziciaVeze()){
            System.out.println("Klik na vezu");
            g.setKlikNaVezu(true);
            zistiCestuStlpec();
            zistiCestuRiadok();
            System.out.println("koniec if");
        }
        else if(g.isKlikNaVezu()){
            System.out.println("hybanie vezou");
            if(this.priamaCestaStlpec == null){
                System.out.println("null");
            }
            if(this.priamaCestaStlpec.contains(vybrane) || this.priamaCestaRiadok.contains(vybrane)) {
                System.out.println("ma kam");
                g.setPredchadzajucaPoziciaVeze(g.getAktPoziciaVeze());
                g.setAktPoziciaVeze(vybrane);
                g.setKlikNaVezu(false);
                g.repaint();
            }
            else{
                System.out.println("Neprechadza cez steny");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public PohybMysou(Grafika gr){
        this.g= gr;
        this.priamaCestaStlpec= new ArrayList<Policko>();
        this.priamaCestaRiadok= new ArrayList<Policko>();
    }

    public PohybMysou(){
        this.priamaCestaStlpec= new ArrayList<Policko>();
        this.priamaCestaRiadok= new ArrayList<Policko>();
    }
}
