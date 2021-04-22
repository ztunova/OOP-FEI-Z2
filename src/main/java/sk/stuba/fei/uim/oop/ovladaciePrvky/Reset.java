package sk.stuba.fei.uim.oop.ovladaciePrvky;

import sk.stuba.fei.uim.oop.grafika.GrafikaBludiska;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Reset extends MojeTlacidlo {
    JFrame okno;

    @Override
    public void actionPerformed(ActionEvent e) {
        okno.getContentPane().removeAll();

        g= new GrafikaBludiska();

        okno.addKeyListener(new PohybKlavesnicou(this.g));

        JPanel p= new JPanel();
        p.setBackground(Color.cyan);
        p.setLayout(null);
        p.setSize(300, 500);
        p.setLocation(500, 0);

        JLabel pocitadlo= g.getPocitadlo();
        p.add(pocitadlo);

        p.add(new Vpravo(g));
        p.add(new Dole(g));
        p.add(new Vlavo(g));
        p.add(new Hore(g));
        p.add(new Reset(g, okno));

        okno.add(p);
        okno.add(g);

        okno.repaint();
    }

    public Reset(GrafikaBludiska gr, JFrame f){
        this.okno= f;
        this.g= gr;
        this.setSize(70, 30);
        this.setLocation(0, 100);
        this.setText("Reset");
        this.addActionListener(this);
        this.setFocusable(false);
    }

    public Reset(){}
}
