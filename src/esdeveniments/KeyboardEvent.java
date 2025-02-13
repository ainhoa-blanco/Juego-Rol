package esdeveniments;

import Personatges.Guerrer;
import Personatges.Mag;
import Personatges.Personatge;
import Personatges.Sacerdot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class KeyboardEvent extends KeyAdapter {
    JLabel labelPersonatge;
    JPanel panelMain;
    Personatge p;
    JPanel mapa;
    JPanel panelPared1;
    JPanel panelPared2;
    JPanel panelPared3;
    JPanel panelPared4;
    JPanel panelPared5;
    JPanel panelPared6;
    JLabel or;
    JLabel pocio;
    JLabel espasa;
    JLabel mitra;
    JLabel cor;
    JPanel panelInfo;
    JLabel orInfo;
    JPanel espaiSortida;
    ArrayList<JLabel> objectes = new ArrayList<>();
    long startTime;
    String nomUsuari;

    public KeyboardEvent(JLabel labelPersonatge, JPanel panelMain, Personatge p, JPanel mapa, JPanel panelPared1, JPanel panelPared2, JPanel panelPared3, JPanel panelPared4, JPanel panelPared5, JPanel panelPared6, JLabel or, JLabel pocio, JLabel espasa, JLabel mitra, JLabel cor, JPanel panelInfo, JLabel orInfo, JPanel espaiSortida, long startTime, String nomUsuari) {
        this.labelPersonatge = labelPersonatge;
        this.panelMain = panelMain;
        this.p = p;
        this.mapa = mapa;
        this.panelPared1=panelPared1;
        this.panelPared2=panelPared2;
        this.panelPared3=panelPared3;
        this.panelPared4=panelPared4;
        this.panelPared5=panelPared5;
        this.panelPared6=panelPared6;
        this.or = or;
        this.pocio = pocio;
        this.espasa = espasa;
        this.mitra = mitra;
        this.cor = cor;
        this.panelInfo = panelInfo;
        this.orInfo = orInfo;
        this.espaiSortida=espaiSortida;
        this.startTime=startTime;
        this.nomUsuari=nomUsuari;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                chocarParet5y6();
                labelPersonatge.setIcon(p.getDreta());
                break;
            case KeyEvent.VK_LEFT:
                chocarParet2y3();
                labelPersonatge.setIcon(p.getEsquerra());
                break;
            case KeyEvent.VK_UP:
                chocarParet1();
                labelPersonatge.setIcon(p.getAdalt());
                break;
            case KeyEvent.VK_DOWN:
                chocarParet4();
                labelPersonatge.setIcon(p.getAbaix());
                break;
        }
        chocarObjecte();
        finalitzarJoc();
    }

    private void chocarObjecte() {
        if (labelPersonatge.getBounds().intersects(or.getBounds())){
            mapa.remove(or);
            p.setOr(p.getOr() + 10);
            int valorOr = p.getOr();
            orInfo.setText(Integer.toString(valorOr));
            posicionarLlocRandom(or);
        } else if (labelPersonatge.getBounds().intersects(pocio.getBounds())) {
            mapa.remove(pocio);
            objectes.add(pocio);
            p.setObjectes(objectes);
            mostrarObjecteInfo();
        } else if (labelPersonatge.getBounds().intersects(espasa.getBounds())) {
            mapa.remove(espasa);
            objectes.add(espasa);
            p.setObjectes(objectes);
            mostrarObjecteInfo();
        } else if (labelPersonatge.getBounds().intersects(mitra.getBounds())) {
            mapa.remove(mitra);
            objectes.add(mitra);
            p.setObjectes(objectes);
            mostrarObjecteInfo();
        }
    }

    private void mostrarObjecteInfo() {
        panelInfo.remove(pocio);
        panelInfo.remove(espasa);
        panelInfo.remove(mitra);

        if (objectes.contains(pocio)){
            pocio.setSize(32,32);
            ImageIcon imageIcon = new ImageIcon("src/images/dungeon/potion.png");
            Icon icon = new ImageIcon(
                    imageIcon.getImage().getScaledInstance(pocio.getWidth(), pocio.getHeight(), Image.SCALE_DEFAULT)
            );
            pocio.setIcon(icon);
            pocio.setLocation(500,50);

            panelInfo.add(pocio);
        }
        if (objectes.contains(espasa)) {
            espasa.setSize(32,32);
            ImageIcon imageIcon = new ImageIcon("src/images/dungeon/sword.png");
            Icon icon = new ImageIcon(
                    imageIcon.getImage().getScaledInstance(espasa.getWidth(), espasa.getHeight(), Image.SCALE_DEFAULT)
            );
            espasa.setIcon(icon);
            espasa.setLocation(600,50);

            panelInfo.add(espasa);
        }
        if (objectes.contains(mitra)) {
            mitra.setSize(32,32);
            ImageIcon imageIcon = new ImageIcon("src/images/dungeon/mitra.png");
            Icon icon = new ImageIcon(
                    imageIcon.getImage().getScaledInstance(mitra.getWidth(), mitra.getHeight(), Image.SCALE_DEFAULT)
            );
            mitra.setIcon(icon);
            mitra.setLocation(700,50);

            panelInfo.add(mitra);
        }

        panelInfo.repaint();
    }

    private void posicionarLlocRandom(JLabel or) {
        Random random = new Random();
        int Y = random.nextInt(300 - 32 + 1) + 32;
        int X = random.nextInt(700 - 32 + 1) + 32;

        or.setLocation(X, Y);

        mapa.add(or);
        mapa.setComponentZOrder(or, 1);
    }

    private void chocarParet1(){
        Rectangle personatge = labelPersonatge.getBounds();
        Rectangle paret1 = panelPared1.getBounds();
        if (personatge.intersects(paret1)){
            labelPersonatge.setLocation(labelPersonatge.getX(), labelPersonatge.getY());
        }else {
            labelPersonatge.setLocation(labelPersonatge.getX(), labelPersonatge.getY() - p.getVelocitat());
        }
    }
    private void chocarParet2y3(){
        Rectangle personatge = labelPersonatge.getBounds();
        Rectangle paret2 = panelPared2.getBounds();
        Rectangle paret3 = panelPared3.getBounds();
        if (personatge.intersects(paret2)|| personatge.intersects(paret3)){
            labelPersonatge.setLocation(labelPersonatge.getX(), labelPersonatge.getY());
        }else {
            labelPersonatge.setLocation(labelPersonatge.getX() - p.getVelocitat(), labelPersonatge.getY());
        }
    }
    private void chocarParet4(){
        Rectangle personatge = labelPersonatge.getBounds();
        Rectangle paret4 = panelPared4.getBounds();
        if (personatge.intersects(paret4)){
            labelPersonatge.setLocation(labelPersonatge.getX(), labelPersonatge.getY());
        }else {
            labelPersonatge.setLocation(labelPersonatge.getX() , labelPersonatge.getY() + p.getVelocitat());
        }
    }
    private void chocarParet5y6(){
        Rectangle personatge = labelPersonatge.getBounds();
        Rectangle paret5 = panelPared5.getBounds();
        Rectangle paret6 = panelPared6.getBounds();
        if (personatge.intersects(paret5)|| personatge.intersects(paret6)){
            labelPersonatge.setLocation(labelPersonatge.getX(), labelPersonatge.getY());
        }else {
            labelPersonatge.setLocation(labelPersonatge.getX()+ p.getVelocitat() , labelPersonatge.getY() );
        }
    }
    public void finalitzarJoc(){
        if (labelPersonatge.getBounds().intersects(espaiSortida.getBounds())&& p.getOr()>=50){
            acabarPartida();
            JOptionPane.showMessageDialog(null,
                    "Has superat el joc!",
                    "Missatge",
                    JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }
    public void acabarPartida() {
        long endTime = System.currentTimeMillis();
        long duradaPartida = endTime - startTime;
        int videsRestants = p.getVidas();
        int monedesOr = Integer.parseInt(orInfo.getText());
        if (p instanceof Mag){
            String tipus ="Mag";
            GameResult resultat = new GameResult(nomUsuari, tipus, duradaPartida, videsRestants, monedesOr);
            resultat.guardarResultat();
        } else if (p instanceof Guerrer) {
            String tipus ="Guerrer";
            GameResult resultat = new GameResult(nomUsuari, tipus, duradaPartida, videsRestants, monedesOr);
            resultat.guardarResultat();
        } else if (p instanceof Sacerdot) {
            String tipus ="Sacerdot";
            GameResult resultat = new GameResult(nomUsuari, tipus, duradaPartida, videsRestants, monedesOr);
            resultat.guardarResultat();
        }
    }

}
