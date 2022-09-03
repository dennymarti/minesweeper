package ch.dennymarti.minesweeper.userinterface;

import ch.dennymarti.minesweeper.Minesweeper;
import ch.dennymarti.minesweeper.spielfeld.Spielfeld;

import java.util.Scanner;

public class UserInterface {

    private Scanner scanner = new Scanner(System.in);

    private Validator validator = new Validator();
    private Minesweeper minesweeper = new Minesweeper();

    private boolean gameOver = false;

    public void readInput() {
        System.out.println();
        System.out.println("Gib die Spalte und Reihe ein, die du markieren oder aufdecken willst.");

        String input = scanner.nextLine();
        validator.validate(input);

        while (!validator.isValid()) {
            System.out.print("Versuchs nochmal: ");
            input = scanner.nextLine();
            validator.validate(input);
        }
        executeInput();
    }

    public void executeInput() {
        Spielfeld spielfeld = minesweeper.getSpielfeld();

        switch (validator.getType()) {
            case "T":
                if (spielfeld.revealField(validator.getColumn(), validator.getRow()) || spielfeld.isWon()) {
                    gameOver = true;
                }
                break;
            case "M":
                spielfeld.markField(validator.getColumn(), validator.getRow());
                break;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
