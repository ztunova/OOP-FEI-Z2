package sk.stuba.fei.uim.oop;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Dole extends Button{
    @Override
    public void actionPerformed(ActionEvent e) {
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
    }

    public Dole(Grafika gr){
        this.g= gr;
        this.setSize(70, 30);
        this.setLocation(50, 250);
        this.setText("Dole");
        this.addActionListener(this);
        this.setFocusable(false);
    }
}
