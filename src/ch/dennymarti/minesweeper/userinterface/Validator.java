package ch.dennymarti.minesweeper.userinterface;

public class Validator {

    private String[] parts;

    private String type;
    private int column;
    private int row;

    private boolean valid;

    public void validate(String input) {
        valid = false;

        parts = input.split(" ");

        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Du hast das Spiel beendet! Bye:)");
            System.exit(0);
        }
        if (parts.length != 3) {
            System.out.println("Deine Eingabe ist ungültig.");
            return;
        }
        type = parts[0].toUpperCase();

        if (!type.equals("M") && !type.equals("T")) {
            System.out.println("Der Eingabetyp ist ungültig.");
            return;
        }
        try {
            column = Integer.parseInt(parts[1]);
        } catch (NumberFormatException exception) {
            System.out.println("Die Spaltenangabe muss numerisch sein.");
            return;
        }
        try {
            row = Integer.parseInt(parts[2]);
        } catch (NumberFormatException exception) {
            System.out.println("Die Reihenangabe muss numerisch sein.");
            return;
        }
        if (column < 1 || column > 8) {
            System.out.println("Die Spaltenangabe entspricht nicht im Spielfeld.");
            return;
        }
        if (row < 1 || row > 8) {
            System.out.println("Die Reihenangabe entspricht nicht im Spielfeld.");
            return;
        }
        valid = true;
    }

    public boolean isValid() {
        return valid;
    }

    public String getType() {
        return type;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }
}
