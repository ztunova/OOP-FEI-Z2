package sk.stuba.fei.uim.oop.ovladaciePrvky;

import sk.stuba.fei.uim.oop.grafika.GrafikaBludiska;
import sk.stuba.fei.uim.oop.komponentyBludiska.Policko;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * pohyb pomocou tlacidla smerom vpravo
 */

public class Vpravo extends MojeTlacidlo {
    @Override
    public void actionPerformed(ActionEvent e) {
        g.resetujMys();

        ArrayList<Policko> mapa= this.g.getBludisko().getMapa();
        Policko aktPoz= g.getAktPoziciaVeze();
        boolean[] hrany= aktPoz.getHrany();

        if(!hrany[2]) {
            g.setPredchadzajucaPoziciaVeze(aktPoz);
            int akt = g.getAktPoziciaVeze().getPoradie();
            akt++;
            Policko posunute = mapa.get(akt);
            this.g.setAktPoziciaVeze(posunute);
            g.repaint();
        }
    }

    public Vpravo(GrafikaBludiska gr){
        this.g= gr;
        this.setSize(75, 30);
        this.setLocation(140, 200);
        this.setText("Vpravo");
        this.addActionListener(this);
        this.setFocusable(false);
    }

    public Vpravo(){}
}
