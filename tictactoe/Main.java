package tictactoe;     

import java.util.Scanner;

public class Main { 
    private static int SIZE = 3;
    private static char[][] map = new char[SIZE][SIZE];
    private static final char DOT_EMPTY = ' ';  
    private static final char DOT_X = 'X';      
    private static final char DOT_O = 'O';      

    private static final Scanner scanner = new Scanner(System.in);

    

    public static void main(String[] args) {
        initMap();
        if(GameNotFinished() && !checkWinO() && !checkWinX() && !impossible()){
            System.out.println("Game not finished");
        }
        if(!GameNotFinished() && !checkWinO() && !checkWinX()) {
            System.out.println("Draw");
        }
        if(checkWinX() && !checkWinO()){
            System.out.println("X wins");
        }
        if(checkWinO() && !checkWinX()){
            System.out.println("O wins");
        }
        if( (GameNotFinished() && impossible()) || (checkWinO() && checkWinX())){
            System.out.println("Impossible");
        }


    }

    
    private static void initMap(){
        for (int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                map[i][j] = ' ';
            }
        }
        printTable();
        System.out.println("Enter the coordinates: ");
        String x = scanner.next();
        String y = scanner.next();
        int count = 0;
        while(true) {
        	int countElse = 0;
        	   char xC = x.charAt(0);
               char yC = y.charAt(0);
        	if(checkIfDigit(x,y)) {
        		if((xC>'0' && xC<='3') && (yC>'0' && yC<='3')) {
        		int xCoord = Integer.parseInt(x);
        		int yCoord = Integer.parseInt(y);
        		if(cellCheck(xCoord,yCoord) && count % 2 == 0) {
        			placeX(xCoord,yCoord);
        			count++;
        		
        		}else if(cellCheck(xCoord,yCoord) && count % 2 !=0) {
        			placeO(xCoord,yCoord);
        			count++;
        		}
        		else {
        			System.out.println("This cell is occupied! Choose another one!");
        			System.out.println("Enter the coordinates:");
        			x = scanner.next();
        			y = scanner.next();
        			countElse++;
        		}}
        		
        		else {
        		System.out.println("Coordinates should be from 1 to 3!");
        		System.out.println("Enter coordinates again:");
        		x = scanner.next();
        		y = scanner.next();
        		countElse++;
        	}
        }else {
    		System.out.println("You should enter numbers!");
    		System.out.println("Enter coordinates again:");
    		x = scanner.next();
    		y = scanner.next();
    		countElse++;
    	}
        if(countElse==0) {
        	if(checkWinO() || checkWinX() || !GameNotFinished()) {
        		printTable();
        		break;
        	}else {
        	printTable();
        	System.out.println("Enter the coordinates:");
        	x = scanner.next();
        	y = scanner.next();}
        }
       }
    }
    private static void placeX(int xCoord,int yCoord) {
    	map[SIZE-yCoord][xCoord-1] = DOT_X;
    }
    private static void placeO(int xCoord,int yCoord) {
    	map[SIZE-yCoord][xCoord-1] = DOT_O;
    }
    private static boolean checkIfDigit(String xCoord,String yCoord) {
    	return  xCoord.length()==1 && yCoord.length()==1 && Character.isDigit(xCoord.charAt(0)) && Character.isDigit(yCoord.charAt(0));
    }
    private static boolean cellCheck(int xCoord,int yCoord) {
    		return (map[SIZE-yCoord][xCoord-1] == DOT_EMPTY);
    	}
    private static void printTable() {
        System.out.println("---------");
        for(int i = 0; i < SIZE; i++){
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    private static boolean GameNotFinished() {
        boolean GameNotFinish = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    GameNotFinish = true;
                    return GameNotFinish;

                }
            }
        }
        return GameNotFinish;
    }

   
    private static boolean checkWinO(){
        boolean winO = false;
        int stringWin = 0, rawWin = 0, diagOneWin = 0, diagTwoWin = 0;

        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(map[i][j] == DOT_O){ 
                    stringWin++;
                }
                if (map[j][i] == DOT_O){ 
                    rawWin++;
                }
            }
            if (map[i][i] == DOT_O){ 
                diagOneWin++;
            }
            if (map[i][(SIZE-1) - i] == DOT_O){ 
                diagTwoWin++;
            }
            if (stringWin == SIZE || rawWin == SIZE){ 
                winO = true;                        
                break;
            } else {                                  
                stringWin = 0;
                rawWin = 0;
            }
        }
        if (diagOneWin == SIZE || diagTwoWin == SIZE){ 
            winO = true;                             
            return winO;
        }
        return winO;                                  
    }

    
    private static boolean checkWinX(){
        boolean winX = false;
        int stringWin = 0, rawWin = 0, diagOneWin = 0, diagTwoWin = 0;

        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(map[i][j] == DOT_X){ 
                    stringWin++;
                }
                if (map[j][i] == DOT_X){ 
                    rawWin++;
                }
            }
            if (map[i][i] == DOT_X){ 
                diagOneWin++;
            }
            if (map[i][(SIZE-1) - i] == DOT_X){ 
                diagTwoWin++;
            }
            if (stringWin == SIZE || rawWin == SIZE){ 
                winX = true;                        
                break;
            } else {                                  
                stringWin = 0;
                rawWin = 0;
            }
        }
        if (diagOneWin == SIZE || diagTwoWin == SIZE){ 
            winX = true;                             
            return winX;
        }
        return winX;                                  
    }

   
    public static boolean impossible() {
        int countX = 0;
        int countO = 0;
        if (checkWinX() && checkWinO()) {
            return true;
        } else {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] == DOT_X) {
                        countX++;
                    }
                    if (map[i][j] == DOT_O) {
                        countO++;
                    }
                }
            }
            return Math.abs(countO - countX) > 1;
        }

    }
}