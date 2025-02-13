package Personatges;

import javax.swing.*;

public class Sacerdot extends Personatge {
    public Sacerdot() {
        setAbaix(new ImageIcon("src/images/priest/priest_down.gif"));
        setAdalt(new ImageIcon("src/images/priest/priest_up.gif"));
        setDreta(new ImageIcon("src/images/priest/priest_right.gif"));
        setEsquerra(new ImageIcon("src/images/priest/priest_left.gif"));
        vidas = 4;
        velocitat = 5;
    }

}
