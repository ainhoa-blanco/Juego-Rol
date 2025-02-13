package Personatges;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Personatge {
    protected ImageIcon adalt;
    protected ImageIcon abaix;
    protected ImageIcon dreta;
    protected ImageIcon esquerra;
    protected String nom;
    protected int vidas;
    protected int or;
    protected int velocitat;
    protected ArrayList<JLabel> objectes;
    protected Point posicio;

    public Personatge() {
        this.objectes = new ArrayList<>();
        this.posicio = new Point();
    }

    protected Icon escalaImatge(ImageIcon imageIcon, int amplada, int alçada) {
        return new ImageIcon(imageIcon.getImage().getScaledInstance(amplada, alçada, Image.SCALE_DEFAULT));
    }

    public Icon getDreta() {
        return escalaImatge(dreta, 48, 48);
    }

    public Icon getEsquerra() {
        return escalaImatge(esquerra, 48, 48);
    }

    public Icon getAdalt() {
        return escalaImatge(adalt, 48, 48);
    }

    public Icon getAbaix() {
        return escalaImatge(abaix, 48, 48);
    }

    public void setAdalt(ImageIcon adalt) {
        this.adalt = adalt;
    }

    public void setAbaix(ImageIcon abaix) {
        this.abaix = abaix;
    }

    public void setDreta(ImageIcon dreta) {
        this.dreta = dreta;
    }

    public void setEsquerra(ImageIcon esquerra) {
        this.esquerra = esquerra;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getOr() {
        return or;
    }

    public void setOr(int or) {
        this.or = or;
    }

    public int getVelocitat() {
        return velocitat;
    }

    public ArrayList<JLabel> getObjectes() {
        return objectes;
    }

    public void setObjectes(ArrayList<JLabel> objectes) {
        this.objectes = objectes;
    }

}
