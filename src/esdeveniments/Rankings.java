package esdeveniments;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Rankings {
    public void mostrarRanking() {
        ArrayList<GameResult> resultats = GameResult.llegirResultats(); // crido metode llegir resultats de la classe Game
        TreeMap<Integer, GameResult> resultatsOrdenats = new TreeMap<>(Comparator.reverseOrder());

        for (GameResult result : resultats) {
            resultatsOrdenats.put(result.calcularPuntuacio(), result); //crido metode calcularPuntuacio d la classe Game
        }

        List<GameResult> top5 = resultatsOrdenats.values().stream().limit(5).collect(Collectors.toList()); //agafa els 5 millors resultats

        mostrarResultats("Ranking", top5);
    }

    private void mostrarResultats(String titol, List<GameResult> resultats) {
        JFrame frame = new JFrame(titol);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JTextArea text = new JTextArea();
        text.setEditable(false);

        StringBuilder builder = new StringBuilder(); //creem constructor
        builder.append(titol).append("\n\n"); //afegir titol + dos salts de liena
        for (int i = 0; i < resultats.size(); i++) { //mostrar cada resultat
            GameResult result = resultats.get(i);
            builder.append(String.format("%d. %s - %s - Monedes: %d - Vides: %d - Temps: %d s\n",
                    i + 1, result.getNomPersonatge(), result.getTipusPersonatge(), result.getMonedesOr(),
                    result.getVidesRestants(), result.getDuradaPartida() / 1000)); //mostro tota la informacio junta
        }

        text.setText(builder.toString()); //el contingut del builder es el text
        frame.add(text);
        frame.setVisible(true);
    }
}
