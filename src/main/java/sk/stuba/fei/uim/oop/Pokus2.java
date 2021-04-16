package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Pokus2 extends JPanel {
    @Override
    public void paint(Graphics g){
        /*g.setColor(Color.BLUE);
        g.fillRect(0, 0, 50, 50);*/

        for (int i= 0; i< 5; i++){
            for (int j= 0; j< 5; j++){
                g.drawRect(j*20+1, i*20+1, 20, 20);
            }
        }

    }
}
