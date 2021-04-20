package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Hra /*implements ActionListener */{
    int vyrisenychBludisk;
    int klikov;

    JFrame okno;
    JButton vpravo;
    JPanel pokus;
    JLabel pocitadlo;

    Grafika g;
    Bludisko bludisko;
    Pokus2 pokus2;

    public Hra(){
        this.okno = new JFrame();
        okno.setLayout(null);
        okno.setSize(700, 500);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setFocusable(true);

        this.g= new Grafika();
        g.setLayout(null);
        g.setSize(500, 500);
        okno.add(g);
        g.setFocusable(true);

        okno.addKeyListener(new PohybKlavesnicou(this.g));

        JPanel p= new JPanel();
        p.setBackground(Color.cyan);
        p.setLayout(null);
        p.setSize(200, 500);
        p.setLocation(500, 0);

        p.add(new JLabel("POKUS1"));

        /*this.pokus2= new Pokus2(g);
        pokus2.addActionListener(pokus2);*/

        this.vpravo= new Vpravo(g);
        p.add(vpravo);
        p.add(new Dole(g));
        p.add(new Vlavo(g));
        p.add(new Hore(g));
        p.add(new Reset(g, okno));

        //p.add(pokus2);
        okno.add(p);

        okno.setVisible(true);
    }

}
