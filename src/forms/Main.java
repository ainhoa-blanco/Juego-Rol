package forms;

import Personatges.Personatge;
import esdeveniments.MouseClickListener;
import esdeveniments.Rankings; // Importamos la clase Rankings

import javax.swing.*;
import java.awt.*;

public class Main {
    private JPanel panelMain;
    private JPanel panelInfo = new JPanel();;
    private static JFrame frame;
    private JLabel labelMag;
    private JLabel labelGuerrer;
    private JLabel labelSacerdot;
    private JTextField nomUsuari;
    private JLabel labelNom;
    private JButton botoMag;
    private JButton botoGuerrer;
    private JButton botoSacerdot;
    private JButton acceptarButton;
    private JButton rankingButton;
    private Personatge p;
    JPanel panelMapa = new JPanel();
    JPanel mapa = new JPanel();
    private JLabel pared;
    private JLabel terra;
    private JLabel labelPersonatge;
    private JPanel panelPared1 = new JPanel();
    private JPanel panelPared2 = new JPanel();
    private JPanel panelPared3 = new JPanel();
    private JPanel panelPared4 = new JPanel();
    private JPanel panelPared5= new JPanel();
    private JPanel panelPared6 = new JPanel();
    private JPanel espaiEntrada = new JPanel();
    private JPanel espaiSortida = new JPanel();

    public Main() {
        panelMain = new JPanel();
        panelMain.setSize(880,600);
        panelMain.setLayout(null);
        panelMain.setBackground(Color.lightGray);

        demanarNom();
        crearBotonAcceptar();
        mostrarBotoMag();
        mostrarBotoGuerrer();
        mostrarBotoSacerdot();
        mostrarMag();
        mostrarGuerrer();
        mostrarSacerdot();

        crearBotoRanking();

        mostrarMapa();
        crearLabelsPared();
    }

    private void crearLabelsPared() {
        panelPared1.setBounds(0, 0, 16 * 54, 16);
        panelPared2.setBounds(0, 16, 16, 16*3);
        panelPared3.setBounds(0, 128, 16, 16*15);
        panelPared4.setBounds(0, 16 * 23, 16 * 54, 16);
        panelPared5.setBounds(848, 352, 16, 16);
        panelPared6.setBounds(848, 16, 16, 16 * 17);
        espaiEntrada.setBounds(0,16, 16, 16*7);
        espaiSortida.setBounds(848, 16*17, 16, 16*7);

        panelMapa.add(panelPared1);
        panelMapa.add(panelPared2);
        panelMapa.add(panelPared3);
        panelMapa.add(panelPared4);
        panelMapa.add(panelPared5);
        panelMapa.add(panelPared6);
        panelMapa.add(espaiSortida);
        panelMapa.add(espaiEntrada);
    }

    private void mostrarMapa() {
        panelMapa.setSize(880,600);
        panelMapa.setLayout(null);
        panelMapa.add(mapa);

        mapa.setSize(880,400);
        mapa.setLayout(null);

        int maxColumnasTerra = 52;
        int maxFilasTerra = 22;
        int maxColumnasPared = 54;
        int maxFilasPared = 24;
        for (int i = 0; i < maxColumnasPared; i++) {
            for (int j = 0; j < maxFilasPared; j++) {
                if (!((j == 4 && i == 0) || (j == 5 && i == 0) ||(j == 6 && i == 0) || (j == 7 && i == 0) || (j == 18 && i == 53) || (j == 19 && i == 53) || (j == 20 && i == 53) || (j == 21 && i == 53))) {
                    pared = new JLabel();
                    pared.setIcon(new ImageIcon("src/images/dungeon/tile004.png"));
                    pared.setBounds(i*16,j*16,16,16);
                    mapa.add(pared);
                    mapa.setComponentZOrder(pared,0);
                }else {
                    JLabel label = new JLabel();
                    label.setIcon(new ImageIcon("src/images/dungeon/tile001.png"));
                    label.setBounds(i * 16, j * 16, 16, 16);
                    mapa.add(label);
                    mapa.setComponentZOrder(label,1);
                }
            }
        }

        for (int i = 0; i < maxColumnasTerra; i++) {
            for (int j = 0; j < maxFilasTerra; j++) {
                terra = new JLabel();
                terra.setIcon(new ImageIcon("src/images/dungeon/tile001.png"));
                terra.setBounds(16+i*16,16+j*16,16,16);
                mapa.add(terra);
                mapa.setComponentZOrder(terra,1);
            }
        }
        crearPanelInfo();
    }

    private void crearPanelInfo() {
        panelInfo.setSize(880,200);
        panelInfo.setLayout(null);
        panelInfo.setBackground(Color.pink);
        panelInfo.setLocation(0, 384);
        panelMapa.add(panelInfo);
    }

    private void demanarNom() {
        Font font = new Font("Arial", Font.BOLD, 17);
        labelNom = new JLabel("Introdueix el teu nom:");
        labelNom.setSize(180, 30);
        labelNom.setFont(font);
        labelNom.setLocation(panelMain.getWidth() / 2 - labelNom.getWidth() / 2, 90);
        panelMain.add(labelNom);

        nomUsuari = new JTextField();
        nomUsuari.setSize(150, 30);
        nomUsuari.setLocation(panelMain.getWidth() / 2 - nomUsuari.getWidth() / 2, 130);
        panelMain.add(nomUsuari);
    }

    private void crearBotonAcceptar() {
        acceptarButton = new JButton("Acceptar");
        acceptarButton.setSize(100, 30);
        acceptarButton.setLocation(panelMain.getWidth() / 2 - acceptarButton.getWidth() / 2, 170);
        acceptarButton.setBackground(new Color(129, 196, 220, 230));
        acceptarButton.setFocusPainted(false);
        panelMain.add(acceptarButton);

        //llegir nom usuari
        acceptarButton.addActionListener(e -> {
            String nom = nomUsuari.getText();
            if (nom.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Si us plau, introdueix el teu nom.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                botoMag.setEnabled(true);
                botoGuerrer.setEnabled(true);
                botoSacerdot.setEnabled(true);
                agregarMouseListenerBotones(nom);
            }
        });
    }

    private void agregarMouseListenerBotones(String nom) {
        botoMag.addMouseListener(new MouseClickListener(botoMag, p, panelMain, panelMapa, panelInfo, labelPersonatge, mapa, panelPared1, panelPared2, panelPared3, panelPared4, panelPared5, panelPared6, espaiEntrada, espaiSortida, nom));
        botoGuerrer.addMouseListener(new MouseClickListener(botoGuerrer, p, panelMain, panelMapa, panelInfo, labelPersonatge, mapa, panelPared1, panelPared2, panelPared3, panelPared4, panelPared5, panelPared6, espaiEntrada, espaiSortida, nom));
        botoSacerdot.addMouseListener(new MouseClickListener(botoSacerdot, p, panelMain, panelMapa, panelInfo, labelPersonatge, mapa, panelPared1, panelPared2, panelPared3, panelPared4, panelPared5, panelPared6, espaiEntrada, espaiSortida, nom));
    }

    private void mostrarSacerdot() {
        labelSacerdot = new JLabel();
        labelSacerdot.setSize(200,200);
        ImageIcon imageIcon = new ImageIcon("src/images/priest/priest_down.gif");
        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(labelSacerdot.getWidth(),labelSacerdot.getHeight(), Image.SCALE_DEFAULT)
        );
        labelSacerdot.setIcon(icon);
        labelSacerdot.setLocation(600,270);
        panelMain.add(labelSacerdot);
    }

    private void mostrarGuerrer() {
        labelGuerrer = new JLabel();
        labelGuerrer.setSize(200,200);
        ImageIcon imageIcon = new ImageIcon("src/images/warrior/warrior_down.gif");
        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(labelGuerrer.getWidth(),labelGuerrer.getHeight(), Image.SCALE_DEFAULT)
        );
        labelGuerrer.setIcon(icon);
        labelGuerrer.setLocation(450 - labelGuerrer.getHeight()/2,270);
        panelMain.add(labelGuerrer);
    }

    private void mostrarMag() {
        labelMag = new JLabel();
        labelMag.setSize(200,200);
        ImageIcon imageIcon = new ImageIcon("src/images/wizard/wizard_down.gif");
        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(labelMag.getWidth(),labelMag.getHeight(), Image.SCALE_DEFAULT)
        );
        labelMag.setIcon(icon);
        labelMag.setLocation(100,270);
        panelMain.add(labelMag);
    }

    private void mostrarBotoSacerdot() {
        botoSacerdot = new JButton();
        botoSacerdot.setText("Sacerdot");
        botoSacerdot.setFocusPainted(false);
        botoSacerdot.setBackground(new Color(206, 197, 197));
        botoSacerdot.setForeground(Color.BLACK);
        botoSacerdot.setEnabled(false);
        panelMain.add(botoSacerdot);
        botoSacerdot.setSize(130,30);
        botoSacerdot.setLocation(635,225);
    }

    private void mostrarBotoGuerrer() {
        botoGuerrer = new JButton();
        botoGuerrer.setText("Guerrer");
        botoGuerrer.setFocusPainted(false);
        botoGuerrer.setBackground(new Color(206, 197, 197));
        botoGuerrer.setForeground(Color.BLACK);
        botoGuerrer.setEnabled(false);
        panelMain.add(botoGuerrer);
        botoGuerrer.setSize(130,30);
        botoGuerrer.setLocation(panelMain.getWidth() / 2 - botoGuerrer.getWidth() / 2,225);
    }

    private void mostrarBotoMag() {
        botoMag = new JButton();
        botoMag.setText("Mag");
        botoMag.setFocusPainted(false);
        botoMag.setBackground(new Color(206, 197, 197));
        botoMag.setForeground(Color.BLACK);
        botoMag.setEnabled(false);
        panelMain.add(botoMag);
        botoMag.setSize(130,30);
        botoMag.setLocation(150 - botoMag.getHeight()/2,225);
    }

    private void crearBotoRanking() {
        rankingButton = new JButton("Ranking");
        rankingButton.setSize(130, 30);
        rankingButton.setLocation(acceptarButton.getX(), acceptarButton.getY() + 300);
        rankingButton.setBackground(new Color(129, 196, 220, 230));
        rankingButton.setFocusPainted(false);
        panelMain.add(rankingButton);

        rankingButton.addActionListener(e -> {
            Rankings rankings = new Rankings(); // Crear a la clase Rankings
            rankings.mostrarRanking(); // Trucar al metode per mostrar ranking
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Main");
        frame.setContentPane(new Main().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(880,600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image icono = toolkit.getImage("src/images/politecnics.png");
        frame.setIconImage(icono);
    }
}
