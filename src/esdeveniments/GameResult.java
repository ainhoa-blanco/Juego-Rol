package esdeveniments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class GameResult {
    private String nomPersonatge;
    private String tipusPersonatge;
    private long duradaPartida;
    private int videsRestants;
    private int monedesOr;

    public GameResult(String nomPersonatge, String tipusPersonatge, long duradaPartida, int videsRestants, int monedesOr) {
        this.nomPersonatge = nomPersonatge;
        this.tipusPersonatge = tipusPersonatge;
        this.duradaPartida = duradaPartida;
        this.videsRestants = videsRestants;
        this.monedesOr = monedesOr;
    }

    public void guardarResultat() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/resultats_partides.txt", true))) {
            writer.write(this.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el resultat");
        }
    }

    @Override
    public String toString() {
        return nomPersonatge + "," + tipusPersonatge + "," + duradaPartida + "," + videsRestants + "," + monedesOr;
    }

    public static ArrayList<GameResult> llegirResultats() {
        ArrayList<GameResult> resultats = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/resultats_partides.txt"))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] parts = linia.split(",");
                if (parts.length == 5) {
                    String nomPersonatge = parts[0];
                    String tipusPersonatge = parts[1];
                    long duradaPartida = Long.parseLong(parts[2]); //Cadena de text convertida a long
                    int videsRestants = Integer.parseInt(parts[3]); //Cadena text convertida a int
                    int monedesOr = Integer.parseInt(parts[4]);
                    resultats.add(new GameResult(nomPersonatge, tipusPersonatge, duradaPartida, videsRestants, monedesOr));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al llegir els resultats");
        }
        return resultats;
    }

    public int calcularPuntuacio() {
        return (monedesOr * 1000) + (videsRestants * 100) - (int) (duradaPartida / 1000); // multiplicar i dividir perque sino m'ho ordena al contrari
    }

    public String getNomPersonatge() {
        return nomPersonatge;
    }

    public String getTipusPersonatge() {
        return tipusPersonatge;
    }

    public long getDuradaPartida() {
        return duradaPartida;
    }

    public int getVidesRestants() {
        return videsRestants;
    }

    public int getMonedesOr() {
        return monedesOr;
    }
}
