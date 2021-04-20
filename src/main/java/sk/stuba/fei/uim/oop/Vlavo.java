package sk.stuba.fei.uim.oop;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Vlavo extends Button{

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Policko> mapa= this.g.getBludisko().getMapa();
        Policko aktPoz= g.getAktPoziciaVeze();
        boolean[] hrany= aktPoz.getHrany();

        if(!hrany[3]) {
            g.setPredchadzajucaPoziciaVeze(aktPoz);
            int akt = g.getAktPoziciaVeze().getPoradie();
            akt--;
            Policko posunute = g.getBludisko().getMapa().get(akt);
            this.g.setAktPoziciaVeze(posunute);
            g.repaint();
        }
        else{
            System.out.println("Neviem prejst cez hranu vlavo");
        }
    }

    public Vlavo(Grafika gr){
        this.g= gr;
        this.setSize(70, 30);
        this.setLocation(50, 200);
        this.setText("Vlavo");
        this.addActionListener(this);
    }
}
