package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Button extends JButton implements ActionListener {
    Grafika g;

    protected void resetujMys(){
        g.setVybraneMysou(null);
        ArrayList<Policko> priamaCesta= g.getPohybMysou().getPriamaCestaRiadok();
        priamaCesta.clear();
        g.getPohybMysou().setPriamaCestaRiadok(priamaCesta);

        priamaCesta= g.getPohybMysou().getPriamaCestaStlpec();
        priamaCesta.clear();
        g.getPohybMysou().setPriamaCestaStlpec(priamaCesta);
    }
}
