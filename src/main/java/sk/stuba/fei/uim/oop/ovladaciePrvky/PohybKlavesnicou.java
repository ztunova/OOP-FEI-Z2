package sk.stuba.fei.uim.oop.ovladaciePrvky;

import sk.stuba.fei.uim.oop.grafika.GrafikaBludiska;
import sk.stuba.fei.uim.oop.komponentyBludiska.Policko;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PohybKlavesnicou implements KeyListener {
    private GrafikaBludiska g;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        g.resetujMys();

        int n= this.g.getBludisko().getRozmer();
        ArrayList<Policko> mapa= this.g.getBludisko().getMapa();
        Policko aktPoz= g.getAktPoziciaVeze();
        boolean[] hrany= aktPoz.getHrany();

        int vstup= e.getKeyCode();
        switch(vstup){
            case KeyEvent.VK_UP: {
                if(!hrany[0]) {
                    g.setPredchadzajucaPoziciaVeze(aktPoz);
                    int akt = g.getAktPoziciaVeze().getPoradie();
                    akt= akt -n;
                    Policko posunute = mapa.get(akt);
                    this.g.setAktPoziciaVeze(posunute);
                    g.repaint();
                }
                break;
            }
            case KeyEvent.VK_DOWN: {
                if(!hrany[1]) {
                    g.setPredchadzajucaPoziciaVeze(aktPoz);
                    int akt = g.getAktPoziciaVeze().getPoradie();
                    akt= akt +n;
                    Policko posunute = mapa.get(akt);
                    this.g.setAktPoziciaVeze(posunute);
                    g.repaint();
                }
                break;
            }
            case KeyEvent.VK_LEFT: {
                if (!hrany[3]) {
                    g.setPredchadzajucaPoziciaVeze(aktPoz);
                    int akt = g.getAktPoziciaVeze().getPoradie();
                    akt--;
                    Policko posunute = mapa.get(akt);
                    this.g.setAktPoziciaVeze(posunute);
                    g.repaint();
                }
                break;
            }
            case KeyEvent.VK_RIGHT: {
                if (!hrany[2]) {
                    g.setPredchadzajucaPoziciaVeze(aktPoz);
                    int akt = g.getAktPoziciaVeze().getPoradie();
                    akt++;
                    Policko posunute = mapa.get(akt);
                    this.g.setAktPoziciaVeze(posunute);
                    g.repaint();
                }
                break;
            }
            default:
                System.out.println("Pouzivaj sipky");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public PohybKlavesnicou(GrafikaBludiska gr){
        this.g= gr;
    }

    public PohybKlavesnicou(){}
}
