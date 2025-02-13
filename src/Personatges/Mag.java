package Personatges;

import javax.swing.*;

public class Mag extends Personatge {
    public Mag() {
        setAbaix(new ImageIcon("src/images/wizard/wizard_down.gif"));
        setAdalt(new ImageIcon("src/images/wizard/wizard_up.gif"));
        setDreta(new ImageIcon("src/images/wizard/wizard_right.gif"));
        setEsquerra(new ImageIcon("src/images/wizard/wizard_left.gif"));
        vidas = 3;
        velocitat = 7;
    }
}
