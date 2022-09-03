package ch.dennymarti.minesweeper;

import ch.dennymarti.minesweeper.spielfeld.Spielfeld;
import ch.dennymarti.minesweeper.userinterface.UserInterface;

public class Minesweeper {

    private UserInterface userInterface;
    private static Spielfeld spielfeld;

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
        minesweeper.runGame();
    }

    public void runGame() {
        userInterface = new UserInterface();
        spielfeld = new Spielfeld(8, 8);

        System.out.println();
        System.out.println(" Minesweeper");
        System.out.println(" Version: 1.0");
        System.out.println(" Author: Denny Marti @Copyright 2022");
        System.out.println();
        System.out.println(" Befehle:");
        System.out.println(" - Feld markieren: M [Spalte] [Reihe] | z.B. 'M 2 4'");
        System.out.println(" - Feld testen / aufdecken: T [Spalte] [Reihe] | Z.B. 'T 6 8'");
        System.out.println();
        System.out.println(" - Du kannst das Spiel mit 'exit' jederzeit beenden.");
        System.out.println();

        while (!userInterface.isGameOver()) {
            spielfeld.printSpielfeld();
            userInterface.readInput();
        }
        System.out.println("-------------------------");

        if (spielfeld.isWon()) {
            System.out.println("Du hast gewonnen! :)");
        } else {
            System.out.println("Du hast verloren! :(");
        }
        System.out.println("- Aufgedecktes Spielfeld:");

        spielfeld.revealAllFields();
        spielfeld.printSpielfeld();

        System.out.println("Ergebnis:");
        System.out.println("- Felder aufgedeckt: " + spielfeld.getTotalRevealed());
        System.out.println("- Felder markiert: " + spielfeld.getTotalMarked());

        System.exit(0);
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }
}
