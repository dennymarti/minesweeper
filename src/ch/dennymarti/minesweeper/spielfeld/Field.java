package ch.dennymarti.minesweeper.spielfeld;

public class Field {

    private boolean isReavealed;
    private boolean isMarked;
    private boolean isBomb;

    private int neighbours;
    private int column;
    private int row;

    public Field(int column, int row) {
        isReavealed = false;
        isMarked = false;
        isBomb = false;
        neighbours = 0;

        this.column = column;
        this.row = row;
    }

    public boolean isRevealed() {
        return isReavealed;
    }

    public void reveal() {
        isReavealed = true;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void toggleMark() {
        isMarked = !isMarked;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setAsBomb() {
        isBomb = true;
    }

    public Integer getNeighbours() {
        return neighbours;
    }

    public void addNeighbour() {
        neighbours++;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    public String display() {
        if (isBomb && isReavealed) {
            return colorField("B", getRedColor());
        } else if (isMarked) {
            return colorField("M", getCyanColor());
        } else if (isReavealed) {
            if (neighbours < 1) {
                return colorField("" + neighbours, getGreenColor());
            } else {
                return colorField("" + neighbours, getYellowColor());
            }
        } else {
            return colorField("*", getWhiteColor());
        }
    }

    public String colorField(String string, String color) {
        return color + string + getResetColor();
    }

    public String getRedColor() {
        return "\u001B[31m";
    }

    private String getGreenColor() {
        return "\u001B[32m";
    }

    private String getYellowColor() {
        return "\u001B[33m";
    }

    private String getCyanColor() {
        return "\u001B[36m";
    }

    private String getWhiteColor() {
        return "\u001B[37m";
    }

    private String getResetColor() {
        return "\u001B[0m";
    }
}
