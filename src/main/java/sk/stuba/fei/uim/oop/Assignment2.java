package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Assignment2 {
    public static void main(String[] args) {

        JFrame f= new JFrame();
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //f.add(new Pokus2());

        Bludisko b= new Bludisko(13);
        b.generujBludisko();
       // b.vykresliBludisko(f);

        f.add(b);

        System.out.println("-----");

        /*JFrame f= new JFrame();
        f.setSize(300, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Pokus2 p2= new Pokus2();
        //p2.setSize(101, 101);
        f.add(p2);*/

        /*JPanel p= new JPanel();
        f.add(p);

        //p.setLayout(new GridLayout(3,3));
        p.setLayout(null);
        p.setSize(150, 150);
        p.add(new Pokus2());
        p.add(new Pokus2());*/

        /*Policko pol = new Policko();
        pol.setSize(251, 251);
        p.add(pol);*/

        /*for (int i= 0; i< 5; i++) {
            for (int j= 0; j< 5; j++) {
                Policko pol = new Policko();
                p.add(pol);
                pol.setSize(51, 51);
                pol.setLocation(i*50, j*50);
            }
        }*/


        //p.add(new Policko());

        /*JLabel l= new JLabel("X");
        int i;
        for (i= 0; i< 9; i++) {
            p.add(new JLabel(i+"X"));
        }

        //f.add(new JLabel("B"));*/

        f.setVisible(true);
        
    }
}
