package esdeveniments;

import Personatges.Guerrer;
import Personatges.Mag;
import Personatges.Personatge;
import Personatges.Sacerdot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class MouseClickListener extends MouseAdapter {
    JButton personatge;
    Personatge p;
    long startTime;
    JPanel panelMain;
    JPanel panelMapa;
    JPanel panelInfo;
    JLabel labelPersonatge;
    JPanel mapa;
    JPanel panelPared1;
    JPanel panelPared2;
    JPanel panelPared3;
    JPanel panelPared4;
    JPanel panelPared5;
    JPanel panelPared6;
    ArrayList<JLabel> esqueletsVerticals;
    ArrayList<JLabel> esqueletsHorizontals;
    ArrayList<Timer> timersEsquelets;
    JPanel espaiEntrada;
    JPanel espaiSortida;
    JLabel or;
    JLabel pocio;
    JLabel espasa;
    JLabel mitra;
    JLabel cor;
    JLabel orInfo;
    JLabel corInfo;
    long ultimCop = 0;
    String nomUsuari;
    private boolean jocAcabat = false;

    public MouseClickListener(JButton personatge, Personatge p, JPanel panelMain, JPanel panelMapa, JPanel panelInfo, JLabel labelPersonatge, JPanel mapa, JPanel panelPared1, JPanel panelPared2, JPanel panelPared3, JPanel panelPared4, JPanel panelPared5, JPanel panelPared6, JPanel espaiEntrada, JPanel espaiSortida, String text) {
        this.personatge = personatge;
        this.p = p;
        this.panelMain = panelMain;
        this.panelMapa = panelMapa;
        this.panelInfo = panelInfo;
        this.labelPersonatge = labelPersonatge;
        this.mapa = mapa;
        this.panelPared1 = panelPared1;
        this.panelPared2 = panelPared2;
        this.panelPared3 = panelPared3;
        this.panelPared4 = panelPared4;
        this.panelPared5 = panelPared5;
        this.panelPared6 = panelPared6;
        this.esqueletsVerticals = new ArrayList<>();
        this.esqueletsHorizontals = new ArrayList<>();
        this.timersEsquelets = new ArrayList<>();
        this.espaiEntrada = espaiEntrada;
        this.espaiSortida = espaiSortida;
        this.nomUsuari = text;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (personatge.getText().equalsIgnoreCase("Mag")) {
            p = new Mag();
            crearMapa(panelMain, panelMapa, panelInfo);
        } else if (personatge.getText().equalsIgnoreCase("Guerrer")) {
            p = new Guerrer();
            crearMapa(panelMain, panelMapa, panelInfo);
        } else if (personatge.getText().equalsIgnoreCase("Sacerdot")) {
            p = new Sacerdot();
            crearMapa(panelMain, panelMapa, panelInfo);
        }
        this.startTime = System.currentTimeMillis();
        mostrarPersonatge();
        panelMain.setFocusable(true);
        panelMain.requestFocusInWindow();
        panelMain.addKeyListener(new KeyboardEvent(labelPersonatge, panelMain, p, mapa, panelPared1, panelPared2, panelPared3, panelPared4, panelPared5, panelPared6, or, pocio, espasa, mitra,cor, panelInfo, orInfo, espaiSortida, startTime, nomUsuari));
    }

    private void mostrarPersonatge() {
        labelPersonatge = new JLabel();
        labelPersonatge.setSize(48, 48);
        ImageIcon imageIcon = new ImageIcon();

        if (p instanceof Mag) {
            imageIcon = new ImageIcon("src/images/wizard/wizard_down.gif");
        } else if (p instanceof Guerrer) {
            imageIcon = new ImageIcon("src/images/warrior/warrior_down.gif");
        } else if (p instanceof Sacerdot) {
            imageIcon = new ImageIcon("src/images/priest/priest_down.gif");
        }

        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(labelPersonatge.getWidth(), labelPersonatge.getHeight(), Image.SCALE_DEFAULT)
        );

        labelPersonatge.setIcon(icon);
        labelPersonatge.setLocation(0, 80);
        mapa.add(labelPersonatge);
        mapa.setComponentZOrder(labelPersonatge, 2);
        mostrarObjectes();
        mostrarEsquelets();
        mostrarCorInfo();
        mostrarOrInfo();
    }

    private void mostrarOrInfo() {
        orInfo = new JLabel();
        orInfo.setSize(32,32);
        ImageIcon imageIcon = new ImageIcon("src/images/dungeon/dollar.png");
        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(orInfo.getWidth(), orInfo.getHeight(), Image.SCALE_DEFAULT)
        );
        orInfo.setIcon(icon);

        Font font = new Font("Arial", Font.BOLD, 32);
        orInfo.setFont(font);
        orInfo.setText("0");
        orInfo.setSize(100,75);
        orInfo.setLocation(16,80);

        panelInfo.add(orInfo);
        panelInfo.repaint();
    }


    private void mostrarCorInfo() {
        corInfo = new JLabel();
        corInfo.setSize(32,32);
        ImageIcon imageIcon = new ImageIcon("src/images/dungeon/heart.png");
        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(corInfo.getWidth(), corInfo.getHeight(), Image.SCALE_DEFAULT)
        );
        corInfo.setIcon(icon);

        Font font = new Font("Arial", Font.BOLD, 32);
        corInfo.setFont(font);
        corInfo.setText(p.getVidas()+"");
        corInfo.setSize(100,75);
        corInfo.setLocation(16,30);

        panelInfo.add(corInfo);
        panelInfo.repaint();
    }

    private void mostrarObjectes() {
        mostrarOr();
        mostrarPocio();
        mostrarEspada();
        mostrarMitra();
    }
    private void mostrarMitra() {
        mitra = new JLabel();
        mitra.setSize(20, 20);
        ImageIcon imageIcon = new ImageIcon("src/images/dungeon/mitra.png");
        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(mitra.getWidth(), mitra.getHeight(), Image.SCALE_DEFAULT)
        );
        mitra.setIcon(icon);

        Random random = new Random();
        int Y = random.nextInt(300 - 32 + 1) + 32;
        int X = random.nextInt(700 - 32 + 1) + 32;

        mitra.setLocation(X, Y);
        mapa.add(mitra);
        mapa.setComponentZOrder(mitra, 1);
    }

    private void mostrarEspada() {
        espasa = new JLabel();
        espasa.setSize(20, 20);
        ImageIcon imageIcon = new ImageIcon("src/images/dungeon/sword.png");
        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(espasa.getWidth(), espasa.getHeight(), Image.SCALE_DEFAULT)
        );
        espasa.setIcon(icon);

        Random random = new Random();
        int Y = random.nextInt(300 - 32 + 1) + 32;
        int X = random.nextInt(700 - 32 + 1) + 32;

        espasa.setLocation(X, Y);
        mapa.add(espasa);
        mapa.setComponentZOrder(espasa, 1);
    }
    private void mostrarPocio() {
        pocio = new JLabel();
        pocio.setSize(20, 20);
        ImageIcon imageIcon = new ImageIcon("src/images/dungeon/potion.png");
        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(pocio.getWidth(), pocio.getHeight(), Image.SCALE_DEFAULT)
        );
        pocio.setIcon(icon);

        Random random = new Random();
        int Y = random.nextInt(300 - 32 + 1) + 32;
        int X = random.nextInt(700 - 32 + 1) + 32;

        pocio.setLocation(X, Y);
        mapa.add(pocio);
        mapa.setComponentZOrder(pocio, 1);
    }
    private void mostrarOr() {
        or = new JLabel();
        or.setSize(20, 20);
        ImageIcon imageIcon = new ImageIcon("src/images/dungeon/dollar.png");
        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(or.getWidth(), or.getHeight(), Image.SCALE_DEFAULT)
        );
        or.setIcon(icon);

        Random random = new Random();
        int Y = random.nextInt(300 - 32 + 1) + 32;
        int X = random.nextInt(700 - 32 + 1) + 32;

        or.setLocation(X, Y);
        mapa.add(or);
        mapa.setComponentZOrder(or, 1);
    }


    private void mostrarEsquelets() {
        mostrarEsqueletsVerticals();
        mostrarEsqueletsHoritzontals();
    }

    private void mostrarEsqueletsVerticals() {
        for (int i = 0; i < 3; i++) {
            JLabel esquelet = new JLabel();
            esquelet.setSize(32, 32);
            String direccion;
            int velocidad = 5;
            if (velocidad > 0) {
                direccion = "up";
            } else {
                direccion = "down";
            }
            ImageIcon imageIcon = new ImageIcon("src/images/skeleton/skeleton_" + direccion + ".gif");
            Icon icon = new ImageIcon(
                    imageIcon.getImage().getScaledInstance(esquelet.getWidth(), esquelet.getHeight(), Image.SCALE_DEFAULT)
            );
            esquelet.setIcon(icon);

            Random random = new Random();
            int Y = random.nextInt(300 - 32 + 1) + 32;
            int X = random.nextInt(700 - 32 + 1) + 32;

            esquelet.setLocation(X, Y);

            mapa.add(esquelet);
            mapa.setComponentZOrder(esquelet, 2);
            esqueletsVerticals.add(esquelet);

            Timer timerSkeleton = new Timer(100, new ActionListener() {
                int velocidad = 5;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int Y = esquelet.getY() + velocidad;
                    esquelet.setLocation(esquelet.getX(), Y);
                    if (esquelet.getBounds().intersects(panelPared1.getBounds()) ||
                            esquelet.getBounds().intersects(panelPared4.getBounds()) ||
                            esquelet.getBounds().intersects(espaiEntrada.getBounds()) ||
                            esquelet.getBounds().intersects(espaiSortida.getBounds())) {

                        velocidad = -velocidad;
                        if (velocidad > 0) {
                            ImageIcon imageIcon = new ImageIcon("src/images/skeleton/skeleton_down.gif");
                            Icon icon = new ImageIcon(
                                    imageIcon.getImage().getScaledInstance(esquelet.getWidth(), esquelet.getHeight(), Image.SCALE_DEFAULT)
                            );
                            esquelet.setIcon(icon);
                        } else {
                            ImageIcon imageIcon = new ImageIcon("src/images/skeleton/skeleton_up.gif");
                            Icon icon = new ImageIcon(
                                    imageIcon.getImage().getScaledInstance(esquelet.getWidth(), esquelet.getHeight(), Image.SCALE_DEFAULT)
                            );
                            esquelet.setIcon(icon);
                        }
                    }
                    chocarPersonatge(esquelet);
                }
            });
            timerSkeleton.start();
            timersEsquelets.add(timerSkeleton);
        }
    }

    private void mostrarEsqueletsHoritzontals() {
        for (int i = 0; i < 3; i++) {
            JLabel esquelet = new JLabel();
            esquelet.setSize(32, 32);
            String direccion;
            int velocidad = 5;
            if (velocidad > 0) {
                direccion = "right";
            } else {
                direccion = "left";
            }
            ImageIcon imageIcon = new ImageIcon("src/images/skeleton/skeleton_" + direccion + ".gif");
            Icon icon = new ImageIcon(
                    imageIcon.getImage().getScaledInstance(esquelet.getWidth(), esquelet.getHeight(), Image.SCALE_DEFAULT)
            );
            esquelet.setIcon(icon);

            Random random = new Random();
            int Y = random.nextInt(300 - 32 + 1) + 32;
            int X = random.nextInt(700 - 32 + 1) + 32;

            esquelet.setLocation(X, Y);

            mapa.add(esquelet);
            mapa.setComponentZOrder(esquelet, 2);
            esqueletsHorizontals.add(esquelet);

            Timer timerSkeleton = new Timer(100, new ActionListener() {
                int velocidad = 5;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int X = esquelet.getX() + velocidad;
                    esquelet.setLocation(X, esquelet.getY());
                    if (esquelet.getBounds().intersects(panelPared2.getBounds()) ||
                            esquelet.getBounds().intersects(panelPared3.getBounds()) ||
                            esquelet.getBounds().intersects(panelPared5.getBounds()) ||
                            esquelet.getBounds().intersects(panelPared6.getBounds()) ||
                            esquelet.getBounds().intersects(espaiEntrada.getBounds()) ||
                            esquelet.getBounds().intersects(espaiSortida.getBounds())) {

                        velocidad = -velocidad;
                        if (velocidad > 0) {
                            ImageIcon imageIcon = new ImageIcon("src/images/skeleton/skeleton_right.gif");
                            Icon icon = new ImageIcon(
                                    imageIcon.getImage().getScaledInstance(esquelet.getWidth(), esquelet.getHeight(), Image.SCALE_DEFAULT)
                            );
                            esquelet.setIcon(icon);
                        } else {
                            ImageIcon imageIcon = new ImageIcon("src/images/skeleton/skeleton_left.gif");
                            Icon icon = new ImageIcon(
                                    imageIcon.getImage().getScaledInstance(esquelet.getWidth(), esquelet.getHeight(), Image.SCALE_DEFAULT)
                            );
                            esquelet.setIcon(icon);
                        }

                    }
                    chocarPersonatge(esquelet);
                }
            });
            timerSkeleton.start();
            timersEsquelets.add(timerSkeleton);

        }
    }

    private void chocarPersonatge(JLabel esquelet) {
        long ara = System.currentTimeMillis();

        if (ara - ultimCop > 1500 && esquelet.getBounds().intersects(labelPersonatge.getBounds())) {
            ultimCop = ara;
            calcularObjectes(esquelet);
        }
    }

    private void calcularObjectes(JLabel esquelet) {
        if (p.getVidas() > 0) {
            if (p instanceof Mag && p.getObjectes().contains(pocio)) {
                p.setVidas(p.getVidas() + 1);
                int valorVida = p.getVidas();
                corInfo.setText(Integer.toString(valorVida));

                p.getObjectes().remove(pocio);
                panelInfo.remove(pocio);
                panelInfo.repaint();
                labelPersonatge.setLocation(0, 80);
            } else if (p instanceof Guerrer && p.getObjectes().contains(espasa)) {
                p.getObjectes().remove(espasa);
                panelInfo.remove(espasa);
                panelInfo.repaint();
                mapa.remove(esquelet);
            } else if (p instanceof Sacerdot && p.getObjectes().contains(mitra)) {
                p.getObjectes().remove(mitra);
                panelInfo.remove(mitra);
                panelInfo.repaint();
                labelPersonatge.setLocation(0, 80);
            } else {
                p.setVidas(p.getVidas() - 1);
                int valorVida = p.getVidas();
                corInfo.setText(Integer.toString(valorVida));
                labelPersonatge.setLocation(0, 80);
            }
            if (p.getVidas() == 0) {
                acabarPartida();
            }
        }
    }

    private void acabarPartida() {
        if (!jocAcabat) {
            jocAcabat = true;
            GameResult resultado = new GameResult(nomUsuari, p.getClass().getSimpleName(), System.currentTimeMillis() - startTime, p.getVidas(), p.getOr());
            resultado.guardarResultat();
            JOptionPane.showMessageDialog(null,
                    "T'has quedat sense vides",
                    "Missatge",
                    JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }




    private void crearMapa(JPanel panelMain, JPanel panelMapa, JPanel panelInfo) {
        panelMain.removeAll();
        panelMain.add(panelInfo);
        panelMain.add(panelMapa);
        panelMain.repaint();
    }
}


