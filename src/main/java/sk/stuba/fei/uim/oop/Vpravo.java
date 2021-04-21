package sk.stuba.fei.uim.oop;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Vpravo extends Button{
    @Override
    public void actionPerformed(ActionEvent e) {
        resetujMys();

        ArrayList<Policko> mapa= this.g.getBludisko().getMapa();
        Policko aktPoz= g.getAktPoziciaVeze();
        boolean[] hrany= aktPoz.getHrany();

        if(!hrany[2]) {
            g.setPredchadzajucaPoziciaVeze(aktPoz);
            int akt = g.getAktPoziciaVeze().getPoradie();
            akt++;
            Policko posunute = g.getBludisko().getMapa().get(akt);
            this.g.setAktPoziciaVeze(posunute);
            g.repaint();
        }
        else{
            System.out.println("Neviem prejst cez hranu vpravo");
        }
    }

    public Vpravo(Grafika gr){
        this.g= gr;
        this.setSize(70, 30);
        this.setLocation(50, 300);
        this.setText("Vpravo");
        this.addActionListener(this);
        this.setFocusable(false);
    }
}
