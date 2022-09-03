package ch.dennymarti.minesweeper.spielfeld;

import ch.dennymarti.minesweeper.userinterface.UserInterface;

import java.util.ArrayList;
import java.util.Random;

public class Spielfeld {

    private final Field[][] fields;

    private int width;
    private int height;
    private int revealed;
    private int marked;
    private int bombs = 8;

    private UserInterface userInterface = new UserInterface();

    public Spielfeld(int width, int height) {
        this.width = width;
        this.height = height;

        fields = new Field[width][height];
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                fields[column][row] = new Field(column, row);
            }
        }
        spawnBombs(bombs);

        revealed = 0;
        marked = 0;
    }

    public void printSpielfeld() {
        System.out.println();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                System.out.print(fields[column][row].display() + "  ");
            }
            System.out.println("| " + (row + 1));
        }
        for (int column = 0; column < width; column++) {
            System.out.print("_  ");
        }
        System.out.println();

        for (int column = 0; column < width; column++) {
            System.out.print((column + 1) + "  ");
        }
        System.out.println();
    }

    public Field getField(int column, int row) {
        return fields[column][row];
    }

    public boolean revealField(int column, int row) {
        Field field = getField(column - 1, row - 1);

        if (field.isRevealed()) {
            System.out.println("Dieses Feld wurde bereits aufgedeckt!");
            return false;
        }
        if (field.isMarked()) {
            System.out.println("Du hast dieses Feld bereits markiert! Entferne die Markierung zuerst.");
            return false;
        }
        if (field.isBomb()) {
            System.out.println("BOOM! Du bist leider auf eine Bombe getreten!");
            unMarkAllFields();
            return true;
        }
        revealed++;
        field.reveal();
        revealNeighbourFields(field);

        System.out.println("Du hast das Feld (Spalte " + column + ", Reihe " + row + ") aufgedeckt.");
        return false;
    }

    public void markField(int column, int row) {
        Field field = getField(column - 1, row - 1);

        if (field.isRevealed()) {
            System.out.println("Dieses Feld wurde bereits aufgedeckt!");
        } else {
            if (!field.isMarked()) {
                marked++;
            }
            field.toggleMark();

            System.out.println("Du hast das Feld (Spalte " + column + ", Reihe " + row + ") markiert.");
        }
    }

    public void revealNeighbourFields(Field field) {
        for (Field neighbourField : getNeighbourFields(field)) {
            if (!neighbourField.isRevealed() && !neighbourField.isBomb()) {
                neighbourField.reveal();
                revealed++;

                if (neighbourField.getNeighbours() == 0) {
                    revealNeighbourFields(neighbourField);
                }
            }
        }
    }

    public void revealAllFields() {
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                fields[column][row].reveal();
            }
        }
    }

    public void unMarkAllFields() {
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                if (fields[column][row].isMarked()) {
                    fields[column][row].toggleMark();
                }
            }
        }
    }

    public void spawnBombs(int amount) {
        Random random = new Random();

        for (int index = 0; index < amount; index++) {
            setBomb(random.nextInt(width), random.nextInt(height));
        }
    }

    public void setBomb(int column, int row) {
        Field field = getField(column, row);

        if (!field.isBomb()) {
            for (Field surroundingFields : getNeighbourFields(field)) {
                surroundingFields.addNeighbour();
            }
            field.setAsBomb();
        }
    }

    public ArrayList<Field> getNeighbourFields(Field field) {
        ArrayList<Field> neighbourFields = new ArrayList<>();

        int minOffsetColumn = Math.max(0, field.getColumn() - 1);
        int maxOffsetColumn = Math.min(width - 1, field.getColumn() + 1);
        int minOffsetRow = Math.max(0, field.getRow() - 1);
        int maxOffsetRow = Math.min(height - 1, field.getRow() + 1);

        for (int column = minOffsetColumn; column <= maxOffsetColumn; column++) {
            for (int row = minOffsetRow; row <= maxOffsetRow; row++) {
                neighbourFields.add(getField(column, row));
            }
        }
        return neighbourFields;
    }

    public Integer getTotalRevealed() {
        return revealed;
    }

    public Integer getTotalMarked() {
        return marked;
    }

    public boolean isWon() {
        return (revealed + bombs) == (width*height);
    }
}
