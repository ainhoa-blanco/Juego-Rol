package Personatges;

import javax.swing.*;

public class Guerrer extends Personatge {
    public Guerrer() {
        setAbaix(new ImageIcon("src/images/warrior/warrior_down.gif"));
        setAdalt(new ImageIcon("src/images/warrior/warrior_up.gif"));
        setDreta(new ImageIcon("src/images/warrior/warrior_right.gif"));
        setEsquerra(new ImageIcon("src/images/warrior/warrior_left.gif"));
        vidas = 5;
        velocitat = 3;
    }
}
