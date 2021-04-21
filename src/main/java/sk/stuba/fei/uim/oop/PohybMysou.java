package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

@Setter
@Getter
public class PohybMysou implements MouseListener, MouseMotionListener {
    Grafika g;
    ArrayList<Policko> priamaCestaStlpec;
    ArrayList<Policko> priamaCestaRiadok;
    Policko sused;
    Policko vybrane;
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
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Graphics gr= g.getGraphics();

        /*if(g.getVybraneMysou() == null){
            gr.setColor(Color.WHITE);
        }*/

        ArrayList<Policko> mapa= g.getBludisko().getMapa();
        int x= e.getX();
        int y= e.getY();
        int riadok= y/30;
        int stlpec= x/30;
        int poradie= riadok*14 + stlpec;

        //System.out.println("Tahanie mysou x: "+ x + " y: " +y +" riadok: "+riadok+" stlpec: "+stlpec +" poradie: "+ poradie);
        Policko akt= mapa.get(poradie);

        if(this.priamaCestaStlpec.contains(akt) || this.priamaCestaRiadok.contains(akt)){
            if(g.getVybraneMysou() == null){
                g.setVybraneMysou(akt);
            }
           //if(g.getVybraneMysou() != null) {
               System.out.println("Sem moze");
               int predR = g.getVybraneMysou().getRiadok();
               int predS = g.getVybraneMysou().getStlpec();
               gr.setColor(Color.white);
               if(g.getVybraneMysou() != g.getAktPoziciaVeze()) {
                   gr.fillOval(predS * 30 + 10, predR * 30 + 10, 10, 10);
               }
               g.setVybraneMysou(akt);
               //g.repaint();

               gr.setColor(Color.ORANGE);
               gr.fillOval(stlpec*30+10, riadok*30+10,10, 10);
          // }

            /*gr.setColor(Color.RED);
            gr.fillOval(stlpec*30+8, riadok*30+8,10, 10);*/
        }
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

        this.vybrane= mapa.get(poradie);

        if(vybrane == g.getAktPoziciaVeze()){
            System.out.println("Klik na vezu");
            g.setKlikNaVezu(true);
            zistiCestuStlpec();
            zistiCestuRiadok();
            System.out.println("koniec if");
        }
        else if(g.isKlikNaVezu()){
            System.out.println("hybanie vezou");
            //mouseMoved(e);
            if(this.priamaCestaStlpec == null){
                System.out.println("null");
            }
            if(this.priamaCestaStlpec.contains(vybrane) || this.priamaCestaRiadok.contains(vybrane)) {
                System.out.println("ma kam");
               // mouseMoved(e);
                g.setPredchadzajucaPoziciaVeze(g.getAktPoziciaVeze());
                g.setAktPoziciaVeze(vybrane);
                g.setKlikNaVezu(false);
                g.setVybraneMysou(null);
                g.repaint();
                this.priamaCestaStlpec.clear();
                this.priamaCestaRiadok.clear();
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
