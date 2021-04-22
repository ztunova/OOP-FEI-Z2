package sk.stuba.fei.uim.oop.grafika;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.komponentyBludiska.Bludisko;
import sk.stuba.fei.uim.oop.komponentyBludiska.Policko;
import sk.stuba.fei.uim.oop.ovladaciePrvky.PohybMysou;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Tato trieda sluzi na vykreslovanie bludiska - obsahuje samotne bludisko, Policko start a ciel, ktore reprezentuju
 * vychodzi a koncovy bod a su zvolene fixne - start je v lavom hornom rohu, ciel v pravom dolnom.
 * Policko aktualnaPoziciaVeze je policko, na ktorom je vykreslena veza - reprezentovana oranzovym kruhom
 * PredchadzajucePolickoVeze je policko z ktoreho veza posledne odisla, po presunuti veze musi byt prekreslene aby
 * tam neostal oranzovy kruh
 * metoda vykresliBludisko je pomocna, najskor prejde polom hran jednotlivych policok, tam kde sa nachadza true, vykresli
 * ciaru (hranu) + vykresli farebne odlisene startovacie policko, cielove a vezu.
 * Metoda paint potom vola metodu vykresliBludisko, pricom ak sa veza nachadza na cielovom policku, vygeneruje nove bludisko
 * ktore aj vykresli namiesto povodneho. K tomu este aktualizuje pocitadlo prejdenych bludisk.
 * Metoda resetujMys vynuluje vsetky parametre ktore su pomocne pre pohyb s mysou. Tuto metodu volaju tlacitka a
 * pohyb pomocou klavesnice. Podla toho ako mam implementovanu mys, keby niekto klikol na vezu ale nakoniec sa pohol
 * tlacitkami alebo klavesnicou, ostala by v mysi ulozena cesta po ktorej sa mohla veza hybat (mysou). Lenze tato cesta by
 * uz nebola aktualna, retoze veza by sa nachadzala uz niekde inde, co by jej umoznovalo "teleportovat sa" na miesto
 * kde bola zakliknuta, pricom by presla aj cez hrany, preto tieto argumenty treba resetovat.
 */

@Setter
@Getter
public class GrafikaBludiska extends JPanel {
    private Bludisko bludisko;
    private Policko start;
    private Policko ciel;
    private Policko aktPoziciaVeze;
    private Policko predchadzajucaPoziciaVeze;
    private Policko vybraneMysou;

    private boolean klikNaVezu;
    private int pocetPrejdenych;
    private JLabel pocitadlo;
    private PohybMysou pohybMysou;


    public void paint(Graphics g){
        if(aktPoziciaVeze == ciel){
            this.pocetPrejdenych ++;
            pocitadlo.setText("Pocet vyriesenych bludisk: " + pocetPrejdenych);

            g.setColor(Color.WHITE);
            g.fillRect(0,0, 500, 500);

            this.bludisko= null;
            this.bludisko= new Bludisko(14);

            this.start= bludisko.getMapa().get(0);
            int n= this.bludisko.getRozmer();
            this.ciel= bludisko.getMapa().get(n*n-1);
            this.aktPoziciaVeze= start;
            this.predchadzajucaPoziciaVeze= aktPoziciaVeze;

            vykresliBludisko(g);
        }
        else{
            vykresliBludisko(g);
        }
    }

    private void vykresliBludisko(Graphics g){
        ArrayList<Policko> mapa= bludisko.getMapa();
        boolean[] hrany;
        int riadok, stlpec;
        g.setColor(Color.BLACK);

        for(Policko pol: mapa) {
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

        if(this.predchadzajucaPoziciaVeze != start){
            stlpec= predchadzajucaPoziciaVeze.getStlpec();
            riadok= predchadzajucaPoziciaVeze.getRiadok();
            g.setColor(Color.WHITE);
            g.fillRect(stlpec*30+6, riadok*30+6, 29,29);
        }

        stlpec= start.getStlpec();
        riadok= start.getRiadok();
        g.setColor(Color.MAGENTA);
        g.fillRect(stlpec*30+6, riadok*30+6, 29,29);

        stlpec= ciel.getStlpec();
        riadok= ciel.getRiadok();
        g.setColor(Color.GREEN);
        g.fillRect(stlpec*30+6, riadok*30+6, 29,29);

        stlpec= aktPoziciaVeze.getStlpec();
        riadok= aktPoziciaVeze.getRiadok();
        g.setColor(Color.ORANGE);
        g.fillOval(stlpec*30+8, riadok*30+8,25, 25);

    }

    public void resetujMys(){
        this.setVybraneMysou(null);
        ArrayList<Policko> priamaCesta= this.getPohybMysou().getPriamaCestaRiadok();
        priamaCesta.clear();
        this.getPohybMysou().setPriamaCestaRiadok(priamaCesta);

        priamaCesta= this.getPohybMysou().getPriamaCestaStlpec();
        priamaCesta.clear();
        this.getPohybMysou().setPriamaCestaStlpec(priamaCesta);
    }

    public GrafikaBludiska(){
        this.setLayout(null);
        this.setSize(500, 500);

        this.pocetPrejdenych= 0;

        this.pocitadlo= new JLabel();
        pocitadlo.setText("Pocet vyriesenych bludisk: " + pocetPrejdenych);
        pocitadlo.setSize(200, 50);
        pocitadlo.setLocation(0, 50);

        this.bludisko= new Bludisko(14);

        this.start= bludisko.getMapa().get(0);
        int n= this.bludisko.getRozmer();
        this.ciel= bludisko.getMapa().get(n*n-1);

        this.aktPoziciaVeze= start;
        this.predchadzajucaPoziciaVeze= aktPoziciaVeze;
        this.vybraneMysou= null;

        this.pohybMysou= new PohybMysou(this);

        this.addMouseListener(pohybMysou);
        this.addMouseMotionListener(pohybMysou);
        this.setFocusable(true);
    }

}
