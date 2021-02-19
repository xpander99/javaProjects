package battleship;

import java.util.Arrays;

public abstract class Ship {
    private final char SHIP_SYMBOL = 'O';
    private int size;
    private String name;
    private char[] cells;
    private boolean isPlaced;
    private int rowBegin;
    private int rowEnd;
    private int colBegin;
    private int colEnd;

    public Ship(int size, String name) {
        this.size = size;
        this.name = name;
        this.cells = new char[size];
        Arrays.fill(this.cells, SHIP_SYMBOL);
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }

    public boolean setCoordinates(int rowBegin, int colBegin, int rowEnd, int colEnd) {
        if (rowBegin == rowEnd || colBegin == colEnd) {
            if (rowEnd - rowBegin != this.size - 1 && colEnd - colBegin != this.size - 1) {
                System.out.printf("Error! Wrong length of the %s! Try again:\n", this.name);
                return false;
            }
        } else {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
        this.rowBegin = rowBegin;
        this.rowEnd = rowBegin;
        this.colBegin = colBegin;
        this.colEnd = colEnd;
        this.isPlaced = true;

        return true;
    }

    public char[] getCells() {
        return cells;
    }

    public int getRowBegin() {
        return rowBegin;
    }

    public int getRowEnd() {
        return rowEnd;
    }

    public int getColumnBegin() {
        return colBegin;
    }

    public int getColumnEnd() {
        return colEnd;
    }

    public boolean isPlaced() {
        return isPlaced;
    }
}

