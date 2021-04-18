package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Pokus2 extends Canvas {
    @Override
    public void paint(Graphics g){
        int x= 0;
        int y= 0;
        for(int i= 0; i< 5; i++){
            g.drawLine(x, y, x+30, y);
            y= y+30;
        }

    }
}
