package sk.stuba.fei.uim.oop;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PohybKlavesnicou implements KeyListener {
    Grafika g;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        g.setVybraneMysou(null);
        ArrayList<Policko> priamaCesta= g.getPohybMysou().getPriamaCestaRiadok();
        priamaCesta.clear();
        g.getPohybMysou().setPriamaCestaRiadok(priamaCesta);

        priamaCesta= g.getPohybMysou().getPriamaCestaStlpec();
        priamaCesta.clear();
        g.getPohybMysou().setPriamaCestaStlpec(priamaCesta);

        int vstup= e.getKeyCode();
        switch(vstup){
            case KeyEvent.VK_UP: {
                System.out.println("Sipka hore");

                int n= this.g.getBludisko().getRozmer();
                ArrayList<Policko> mapa= this.g.getBludisko().getMapa();
                Policko aktPoz= g.getAktPoziciaVeze();
                boolean[] hrany= aktPoz.getHrany();

                if(!hrany[0]) {
                    g.setPredchadzajucaPoziciaVeze(aktPoz);
                    int akt = g.getAktPoziciaVeze().getPoradie();
                    akt= akt -n;
                    Policko posunute = g.getBludisko().getMapa().get(akt);
                    this.g.setAktPoziciaVeze(posunute);
                    g.repaint();
                }
                else{
                    System.out.println("Neviem prejst cez hranu hore");
                }

                break;
            }
            case KeyEvent.VK_DOWN: {
                System.out.println("Sipka dole");

                int n= this.g.getBludisko().getRozmer();
                ArrayList<Policko> mapa= this.g.getBludisko().getMapa();
                Policko aktPoz= g.getAktPoziciaVeze();
                boolean[] hrany= aktPoz.getHrany();

                if(!hrany[1]) {
                    g.setPredchadzajucaPoziciaVeze(aktPoz);
                    int akt = g.getAktPoziciaVeze().getPoradie();
                    akt= akt +n;
                    Policko posunute = g.getBludisko().getMapa().get(akt);
                    this.g.setAktPoziciaVeze(posunute);
                    g.repaint();
                }
                else{
                    System.out.println("Neviem prejst cez hranu dole");
                }

                break;
            }
            case KeyEvent.VK_LEFT: {
                System.out.println("Sipka vlavo");

                ArrayList<Policko> mapa = this.g.getBludisko().getMapa();
                Policko aktPoz = g.getAktPoziciaVeze();
                boolean[] hrany = aktPoz.getHrany();

                if (!hrany[3]) {
                    g.setPredchadzajucaPoziciaVeze(aktPoz);
                    int akt = g.getAktPoziciaVeze().getPoradie();
                    akt--;
                    Policko posunute = g.getBludisko().getMapa().get(akt);
                    this.g.setAktPoziciaVeze(posunute);
                    g.repaint();
                } else {
                    System.out.println("Neviem prejst cez hranu vlavo");
                }

                break;
            }
            case KeyEvent.VK_RIGHT: {
                System.out.println("Sipka vpravo");

                ArrayList<Policko> mapa = this.g.getBludisko().getMapa();
                Policko aktPoz = g.getAktPoziciaVeze();
                boolean[] hrany = aktPoz.getHrany();

                if (!hrany[2]) {
                    g.setPredchadzajucaPoziciaVeze(aktPoz);
                    int akt = g.getAktPoziciaVeze().getPoradie();
                    akt++;
                    Policko posunute = g.getBludisko().getMapa().get(akt);
                    this.g.setAktPoziciaVeze(posunute);
                    g.repaint();
                } else {
                    System.out.println("Neviem prejst cez hranu vpravo");
                }

                break;
            }
            default:
                System.out.println("Pouzivaj sipky");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public PohybKlavesnicou(Grafika gr){
        this.g= gr;
    }

    public PohybKlavesnicou(){}
}
