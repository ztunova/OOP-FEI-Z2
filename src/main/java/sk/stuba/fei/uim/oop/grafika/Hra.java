package sk.stuba.fei.uim.oop.grafika;

import sk.stuba.fei.uim.oop.grafika.GrafikaBludiska;
import sk.stuba.fei.uim.oop.ovladaciePrvky.*;

import javax.swing.*;
import java.awt.*;

/**
 * trieda ktora obsahuje JFrame na ktory sa zobrazia ostatne komponenty, cim sa vytvori cela hra
 */

public class Hra {
    private JFrame okno;
    private JLabel pocitadlo;
    private GrafikaBludiska g;

    public Hra(){
        this.okno = new JFrame();
        okno.setLayout(null);
        okno.setSize(800, 500);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setFocusable(true);

        this.g= new GrafikaBludiska();
        okno.add(g);

        okno.addKeyListener(new PohybKlavesnicou(this.g));

        JPanel p= new JPanel();
        p.setBackground(Color.WHITE);
        p.setLayout(null);
        p.setSize(300, 500);
        p.setLocation(500, 0);

        this.pocitadlo= g.getPocitadlo();
        p.add(pocitadlo);

        p.add(new Vpravo(g));
        p.add(new Dole(g));
        p.add(new Vlavo(g));
        p.add(new Hore(g));
        p.add(new Reset(g, okno));

        okno.add(p);

        okno.setVisible(true);
    }

}
