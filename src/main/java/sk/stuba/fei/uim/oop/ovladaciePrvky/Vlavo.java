package sk.stuba.fei.uim.oop.ovladaciePrvky;

import sk.stuba.fei.uim.oop.grafika.GrafikaBludiska;
import sk.stuba.fei.uim.oop.komponentyBludiska.Policko;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * pohyb pomocou tlacidla smerom vlavo
 */

public class Vlavo extends MojeTlacidlo {

    @Override
    public void actionPerformed(ActionEvent e) {
        g.resetujMys();

        ArrayList<Policko> mapa= this.g.getBludisko().getMapa();
        Policko aktPoz= g.getAktPoziciaVeze();
        boolean[] hrany= aktPoz.getHrany();

        if(!hrany[3]) {
            g.setPredchadzajucaPoziciaVeze(aktPoz);
            int akt = g.getAktPoziciaVeze().getPoradie();
            akt--;
            Policko posunute = mapa.get(akt);
            this.g.setAktPoziciaVeze(posunute);
            g.repaint();
        }
    }

    public Vlavo(GrafikaBludiska gr){
        this.g= gr;
        this.setSize(70, 30);
        this.setLocation(0, 200);
        this.setText("Vlavo");
        this.addActionListener(this);
        this.setFocusable(false);
    }

    public Vlavo(){}
}
