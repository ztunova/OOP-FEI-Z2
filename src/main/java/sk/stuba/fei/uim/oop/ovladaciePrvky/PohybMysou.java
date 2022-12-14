package sk.stuba.fei.uim.oop.ovladaciePrvky;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.grafika.GrafikaBludiska;
import sk.stuba.fei.uim.oop.komponentyBludiska.Policko;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Trieda, ktora implementuje pohyb pomocou mysi.
 * Metody zistiCestuStlpec a zistiCestuRiadok su pomocne, urcia priamu cestu v horizontalnom a zvislom smere - v atributoch
 * priamaCestaStlpec a priamaCestaRiadok ulozia policka, na ktore sa veza moze zo svojej aktualnej pozicie posunut.
 * mouseClicked identifikuje policko na ktore bolo kliknue. Ak je to policko s vezou, zapamata si to v atribute. Nasledne
 * ked sa znovu klikne, podla tohoto atributu vie, ze sa moze hybat vezou (a nebolo to len nahodne kliknutie do plochy).
 * Ked je teda veza zakliknuta a metoda zisti dalsie kliknutie, skontroluje ci policko, na ktore sa teraz kliklo ci je
 * medzi polickami na ktore sa veza moze posunut (priamaCestaStlpec/Riadok). Ak ano, urci novu poziciu veze, oznaci
 * vezu za "odkliknutu" a prekresli plochu.
 * MouseMoved zvyraznuje policka nad ktorymi sa prechadza mysou a veza sa na ne moze posunut. Urci policko nad ktorym
 * sa kurzor zrovna nachadza, skontroluje, ci je to jedno z tych na ktore sa veza moze posunut a ak ano, zvyrazni ho
 * vykreslenim mensieho oranzoveho kruzku. V atribute vybraneMysou sa uklada policko nad ktorym sa nachadza kurzor.
 * Najskor je to policko, nad ktorym bol kurzor predtym (nez sa presunul tam kde je aktualne). Toto predchadzajuce
 * policko musi metoda prekreslit aby neostavalo zvyraznene - oranzovy kruzok prekresli bielym, v atribute ulozi
 * aktualne policko a to zvyrazni mensim oranzovym kruzkom.
 */

@Setter
@Getter
public class PohybMysou implements MouseListener, MouseMotionListener {
    private GrafikaBludiska g;
    private ArrayList<Policko> priamaCestaStlpec;
    private ArrayList<Policko> priamaCestaRiadok;
    private Policko sused;
    private Policko vybrane;
    private int poradieSuseda;

    private void zistiCestuStlpec(){
        this.priamaCestaStlpec.clear();
        Policko vychodisko= g.getAktPoziciaVeze();
        Policko akt= vychodisko;

        while(!akt.getHrany()[0]){
            poradieSuseda= akt.getPoradie() - g.getBludisko().getRozmer();
            sused= g.getBludisko().getMapa().get(poradieSuseda);
            this.priamaCestaStlpec.add(sused);
            akt= sused;
        }

        akt= vychodisko;

        while (!akt.getHrany()[1]){
            poradieSuseda= akt.getPoradie() + g.getBludisko().getRozmer();
            sused= g.getBludisko().getMapa().get(poradieSuseda);
            this.priamaCestaStlpec.add(sused);
            akt= sused;
        }
    }

    private void zistiCestuRiadok(){
        this.priamaCestaRiadok.clear();
        Policko vychodisko= g.getAktPoziciaVeze();
        Policko akt= vychodisko;

        while(!akt.getHrany()[2]){
            poradieSuseda= akt.getPoradie() +1;
            sused= g.getBludisko().getMapa().get(poradieSuseda);
            this.priamaCestaRiadok.add(sused);
            akt= sused;
        }

        akt= vychodisko;

        while (!akt.getHrany()[3]){
            poradieSuseda= akt.getPoradie() -1;
            sused= g.getBludisko().getMapa().get(poradieSuseda);
            this.priamaCestaRiadok.add(sused);
            akt= sused;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        Graphics gr= g.getGraphics();

        ArrayList<Policko> mapa= g.getBludisko().getMapa();
        int x= e.getX();
        int y= e.getY();
        int riadok= y/30;
        int stlpec= x/30;
        int n= g.getBludisko().getRozmer();
        int poradie= riadok*n + stlpec;

        if(poradie < n*n) {
            Policko akt = mapa.get(poradie);

            if (this.priamaCestaStlpec.contains(akt) || this.priamaCestaRiadok.contains(akt)) {
                if (g.getVybraneMysou() == null) {
                    g.setVybraneMysou(akt);
                }

                int predR = g.getVybraneMysou().getRiadok();
                int predS = g.getVybraneMysou().getStlpec();

                gr.setColor(Color.white);
                if (g.getVybraneMysou() != g.getAktPoziciaVeze()) {
                    gr.fillOval(predS * 30 + 15, predR * 30 + 15, 10, 10);
                }
                g.setVybraneMysou(akt);

                gr.setColor(Color.ORANGE);
                gr.fillOval(stlpec * 30 + 15, riadok * 30 + 15, 10, 10);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ArrayList<Policko> mapa= g.getBludisko().getMapa();
        int x= e.getX();
        int y= e.getY();
        int riadok= y/30;
        int stlpec= x/30;
        int n= this.g.getBludisko().getRozmer();
        int poradie= riadok*n + stlpec;

        this.vybrane= mapa.get(poradie);

        if(vybrane == g.getAktPoziciaVeze()){
            g.setKlikNaVezu(true);
            zistiCestuStlpec();
            zistiCestuRiadok();
        }
        else if(g.isKlikNaVezu()){
            if(this.priamaCestaStlpec.contains(vybrane) || this.priamaCestaRiadok.contains(vybrane)) {
                g.setPredchadzajucaPoziciaVeze(g.getAktPoziciaVeze());
                g.setAktPoziciaVeze(vybrane);
                g.setKlikNaVezu(false);
                g.setVybraneMysou(null);
                g.repaint();
                this.priamaCestaStlpec.clear();
                this.priamaCestaRiadok.clear();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public PohybMysou(GrafikaBludiska gr){
        this.g= gr;
        this.priamaCestaStlpec= new ArrayList<Policko>();
        this.priamaCestaRiadok= new ArrayList<Policko>();
    }

    public PohybMysou(){
        this.priamaCestaStlpec= new ArrayList<Policko>();
        this.priamaCestaRiadok= new ArrayList<Policko>();
    }

}
