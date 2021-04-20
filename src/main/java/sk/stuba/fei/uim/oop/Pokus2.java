package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pokus2 extends JButton implements ActionListener {
    int pocitadlo;
    JLabel l;
    Grafika g;

    @Override
    public void actionPerformed(ActionEvent e) {
        //g.repaint();
        g.setPredchadzajucaPoziciaVeze(g.getAktPoziciaVeze());
        int akt= g.getAktPoziciaVeze().getPoradie();
        akt++;
        Policko posunute= g.getBludisko().getMapa().get(akt);
        this.g.setAktPoziciaVeze(posunute);
        g.repaint();
    }

    public Pokus2(/*JLabel l, int poc,*/ Grafika gr){
       // this.l= l;
       // this.pocitadlo= poc;
        this.g= gr;
        this.setSize(70, 50);
        this.setLocation(50, 300);
        this.setText("POMOC");
    }

    public Pokus2(){}
   /* @Override
    public void paint(Graphics g){
        int x= 0;
        int y= 0;
        for(int i= 0; i< 5; i++){
            g.drawLine(x, y, x+30, y);
            y= y+30;
        }

    }*/
}
