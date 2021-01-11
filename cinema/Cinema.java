package cinema;
import java.util.Scanner;
public class Cinema {
    private static final Scanner sc = new Scanner(System.in);
    private static int rows;
    private static int seats;
    private static int[][] arr;
    private static final int priceForFront = 10;
    private static final int priceForBack = 8;
    private static int purchasedTickets;
    private static double percentage;
    private static int currentIncome;
    private static int totalIncome;
    private static void printRoom(){
        System.out.println("Cinema:");
        System.out.print("  ");
        for(int i = 0;i < arr[0].length;i++){
            System.out.print(i+1 + " ");
        }
        System.out.println();
        for(int i = 0;i<arr.length;i++){
            System.out.print(i+1 + " ");
            for(int j = 0;j<arr[i].length;j++){
                System.out.print((char)arr[i][j] + " ");
            }
            System.out.println();
        }

    }
    private static int getTotalSeats(){
        return seats * rows;
    }
    private static void buyTicket(){
        System.out.println("Enter a row number:");
        int rowNo = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNo = sc.nextInt();
        while((rowNo > rows || seatNo > seats) || isOccupied(Cinema.arr,rowNo,seatNo)){
            System.out.println((rowNo > rows || seatNo> seats) ? "Wrong input!" : "That ticket has already been purchased!");
            System.out.println();
            System.out.println("Enter a row number:");
            rowNo = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNo = sc.nextInt();
        }
        System.out.println("Ticket price: $" + calcPrice(rowNo,seatNo,rows,seats));
        System.out.println();
        takeASeat(rowNo,seatNo);
        Cinema.purchasedTickets++;
        Cinema.currentIncome += calcPrice(rowNo,seatNo,rows,seats);
    }
    private static int getPurchasedTickets(){
        return Cinema.purchasedTickets;
    }
    private static int getCurrentIncome(){
        return Cinema.currentIncome;
    }
    private static void printStatistics(){
        System.out.println("Number of purchased tickets: " + getPurchasedTickets());
        System.out.println(String.format("Percentage: %.2f",getPercentage()) + "%");
        System.out.println("Current income: $" + getCurrentIncome());
        System.out.println("Total income: $" + getTotalIncome());
    }
    private static int getTotalIncome(){
        if(getTotalSeats() > 60){
            if(rows % 2 == 0){
                return Cinema.totalIncome = (rows / 2) * seats * priceForFront * 2;
            }else
                return Cinema.totalIncome = (rows / 2) * seats * priceForFront + (rows / 2 + 1) * seats * priceForBack;
        }
        return Cinema.totalIncome = rows * seats * priceForFront;
    }
    private static double getPercentage(){
        return Cinema.percentage = getPurchasedTickets() / (double)getTotalSeats() * 100;
    }
    private static boolean isOccupied(int[][] arr,int rowNo,int seatNo){
        return arr[rowNo-1][seatNo-1] != 83;
    }
    private static void takeASeat(int rowNo,int seatNo){
        Cinema.arr[rowNo-1][seatNo-1] = 66;
    }
    private static void populateRoom(int[][] arr){
        for(int i = 0;i<arr.length;i++){
            for(int j = 0;j<arr[i].length;j++){
                arr[i][j] = 83;
            }
        }
    }
    private static int calcPrice(int rowNo,int seatNo,int rows,int seats){
        int noOfSeats = rows * seats;
        if (noOfSeats <= 60) {
            return priceForFront;
        } else {
            if (rows % 2 == 0) {
                if(rowNo <= rows / 2){
                    return priceForFront;
                }
            }
            else {
               if(rowNo <= rows / 2){
                   return priceForFront;
               }
            }
        }
        return priceForBack;
    }
    private static void showMenu(){
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
    private static void init(){
        System.out.println("Enter the number of rows: ");
        Cinema.rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        Cinema.seats = sc.nextInt();
        Cinema.arr = new int[rows][seats];
        populateRoom(Cinema.arr);
    }
    public static void main(String[] args) {
        // Write your code here
        init();
        showMenu();
        int choice = sc.nextInt();
        while(choice != 0 ){
            switch(choice){
                case 1:
                    printRoom();
                    showMenu();
                    break;
                case 2:
                    buyTicket();
                    showMenu();
                    break;
                case 3:
                    printStatistics();
                    showMenu();
                    break;
                default:
                    break;
            }
            choice = sc.nextInt();
        }

    }
}