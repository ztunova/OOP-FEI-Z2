package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Reset extends Button{
    JFrame okno;

    @Override
    public void actionPerformed(ActionEvent e) {
        okno.getContentPane().removeAll();

        g= new Grafika();
        g.setLayout(null);
        g.setSize(500, 500);

        JPanel p= new JPanel();
        p.setBackground(Color.cyan);
        p.setLayout(null);
        p.setSize(200, 500);
        p.setLocation(500, 0);

        p.add(new JLabel("POKUS1"));

        p.add(new Vpravo(g));
        p.add(new Dole(g));
        p.add(new Vlavo(g));
        p.add(new Hore(g));
        p.add(new Reset(g, okno));

        okno.add(p);
        okno.add(g);

        okno.repaint();
    }

    public Reset(Grafika gr, JFrame f){
       // this.p= p;
        this.okno= f;
        this.g= gr;
        this.setSize(70, 30);
        this.setLocation(50, 100);
        this.setText("Reset");
        this.addActionListener(this);
    }
}
