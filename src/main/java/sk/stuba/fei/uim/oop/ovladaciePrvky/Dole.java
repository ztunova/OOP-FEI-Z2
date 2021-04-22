package sk.stuba.fei.uim.oop.ovladaciePrvky;

import sk.stuba.fei.uim.oop.grafika.GrafikaBludiska;
import sk.stuba.fei.uim.oop.komponentyBludiska.Policko;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Dole extends MojeTlacidlo {
    @Override
    public void actionPerformed(ActionEvent e) {
        g.resetujMys();

        int n= this.g.getBludisko().getRozmer();
        ArrayList<Policko> mapa= this.g.getBludisko().getMapa();
        Policko aktPoz= g.getAktPoziciaVeze();
        boolean[] hrany= aktPoz.getHrany();

        if(!hrany[1]) {
            g.setPredchadzajucaPoziciaVeze(aktPoz);
            int akt = g.getAktPoziciaVeze().getPoradie();
            akt= akt +n;
            Policko posunute = mapa.get(akt);
            this.g.setAktPoziciaVeze(posunute);
            g.repaint();
        }

    }

    public Dole(GrafikaBludiska gr){
        this.g= gr;
        this.setSize(70, 30);
        this.setLocation(70, 230);
        this.setText("Dole");
        this.addActionListener(this);
        this.setFocusable(false);
    }

    public Dole(){}
}
