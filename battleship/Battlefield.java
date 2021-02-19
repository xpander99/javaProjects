package battleship;

import java.util.Scanner;


public class Battlefield {
    private final int SIZE = 10;
    private final int AIRCRAFT_SIZE = 5;
    private final int BATTLESHIP_SIZE = 4;
    private final int SUBMARINE_SIZE = 3;
    private final int CRUISER_SIZE = 3;
    private final int DESTROYER_SIZE = 2;
    private final char EMPTY = '~';
    private char[][] filed = new char[SIZE][SIZE];
    private Ship[] ships;


    public Battlefield() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                filed[i][j] = EMPTY;
            }
        }
    }

    public void initField() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(this.toString());
        ships = new Ship[5];
        ships[0] = new AircraftCarrier(AIRCRAFT_SIZE, "Aircraft Carrier");
        ships[1] = new BattleShip(BATTLESHIP_SIZE, "Battleship");
        ships[2] = new Submarine(SUBMARINE_SIZE, "Submarine");
        ships[3] = new Cruiser(CRUISER_SIZE, "Cruiser");
        ships[4] = new Destroyer(DESTROYER_SIZE, "Destroyer");
        for (Ship ship : ships) {
            System.out.printf("Enter the coordinates of the %s (%d cells):\n", ship.getName(), ship.getSize());
            while (true) {
                String[] coordinates = scanner.nextLine().split(" ");
                int rowBegin = coordinates[0].charAt(0) - 65;
                int columnBegin = Integer.parseInt(coordinates[0].substring(1));
                int rowEnd = coordinates[1].charAt(0) - 65;
                int columnEnd = Integer.parseInt(coordinates[1].substring(1));
                if (rowBegin > rowEnd) {
                    int tmp = rowEnd;
                    rowEnd = rowBegin;
                    rowBegin = tmp;
                }
                if (columnBegin > columnEnd) {
                    int tmp = columnEnd;
                    columnEnd = columnBegin;
                    columnBegin = tmp;
                }
                if (ship.setCoordinates(rowBegin, columnBegin, rowEnd, columnEnd)) {
                    if (putShipOnField(rowBegin, columnBegin, rowEnd, columnEnd, ship)) {
                        System.out.println(this.toString());
                        break;
                    }
                }
            }
        }
    }

    public boolean putShipOnField(int rowBegin, int columnBegin, int rowEnd, int columnEnd, Ship compShip) {
        for (Ship ship : ships) {
            if (ship != compShip && ship.isPlaced()) {
                for (int i = rowBegin - 1; i <= rowEnd + 1; i++) {
                    for (int j = columnBegin - 1; j <= columnEnd + 1; j++) {
                        if ((i == ship.getRowBegin() && j == ship.getColumnBegin()) ||
                                (i == ship.getRowEnd() && j == ship.getColumnEnd())) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }
                }
            }
        }

        if (rowBegin == rowEnd) {
            for (int i = columnBegin; i <= columnEnd; i++) {
                this.filed[rowBegin][i - 1] = compShip.getCells()[i - columnBegin];
            }
        } else {
            for (int i = rowBegin; i <= rowEnd; i++) {
                this.filed[i][columnBegin - 1] = compShip.getCells()[i - rowBegin];
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("  1 2 3 4 5 6 7 8 9 10\n");
        for (int i = 0; i < SIZE; i++) {
            result.append(Character.toChars(65 + i));
            for (int j = 0; j < SIZE; j++) {
                result.append(" ").append(filed[i][j]);
            }
            result.append("\n");
        }
        return String.valueOf(result);
    }
}