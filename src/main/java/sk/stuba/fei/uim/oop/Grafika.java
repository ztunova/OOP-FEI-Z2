package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Setter
@Getter
public class Grafika extends JPanel {
    Bludisko bludisko;
    private Policko start;
    private Policko ciel;
    private Policko aktPoziciaVeze;
    private Policko predchadzajucaPoziciaVeze;

    public void paint(Graphics g){
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

    public Grafika(){
        this.setBackground(Color.WHITE);
        this.bludisko= new Bludisko(14);
        bludisko.generujBludisko();
        this.start= bludisko.getMapa().get(0);
        this.ciel= bludisko.getMapa().get(14*14-1);
        this.aktPoziciaVeze= start;
        this.predchadzajucaPoziciaVeze= aktPoziciaVeze;
    }

}
