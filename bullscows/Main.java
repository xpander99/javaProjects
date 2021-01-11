package bullscows;
import java.util.Random;
import java.util.Scanner;
public class Main {
    private static String count(String number,String guess){
        int cows = 0;
        int bulls = 0;
        for(int i = 0;i<number.length();i++){
            if(number.charAt(i) == guess.charAt(i)){
                bulls++;
            }
        }
        for(int i = 0;i<number.length();i++){
            for(int j = 0;j<number.length();j++){
                if(number.charAt(i) == guess.charAt(i) && i != j){
                    cows++;
                    break;
                }
            }
        }
        if(cows == 0 && bulls == 0){
            return "Grade: None.";
        }else if(cows > 0 && bulls == 0){
            return "Grade: " + cows + ((cows > 1) ? " cows" : " cow");
        }else if(cows == 0 && bulls > 0){
            return "Grade: " + bulls + ((bulls > 1) ? " bulls" : " bull");
        }else{
            return "Grade: " + bulls + ((bulls > 1) ? " bulls" : " bull")  + " and " + cows + ((cows>1) ? " cows" : " cow");
        }
    }
    private static String computeStars(int n){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<n;i++){
            sb.append("*");
        }
        return sb.toString();
    }
    private static String computeChars(int sizeOfSymbols){
        char first = 'a';
        char last = (char) (first + (sizeOfSymbols - 10) - 1);
        return first + "-" + last;
    }
    private static void print(int n,int sizeOfSymbols){
        System.out.println("The secret is prepared: " + computeStars(n) + " (0-9, " + computeChars(sizeOfSymbols) + ").");
    }
    private static boolean unique(String randomNumber){
        for(int i = 0;i<randomNumber.length()-1;i++){
            for(int j = i+1;j<randomNumber.length();j++){
                if(randomNumber.charAt(i) == randomNumber.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }
    private static int[] generateRandomIndexes(int n){
        Random random = new Random();
        int[] arr = new int[n];
        arr[0] = random.nextInt(n);
        int rand = random.nextInt(n);
        for(int i = 1;i<n;){
            boolean result = false;
            for(int j = i;j>0;j--){
                if(arr[j-1] == rand){
                    result = true;
                    break;
                }
            }
            if(result){
                rand = random.nextInt(n);
            }else{
                arr[i] = rand;
                rand = random.nextInt(n);
                i++;
            }
        }
        return arr;
    }
    private static String generate(int n, int sizeOfSymbols){
        Random random = new Random();
        if(n == 1){
            return "" + random.nextInt(10);
        }
        int[] arr = new int[n];
        int[] randomIndexes = generateRandomIndexes(n);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(i = 0;i<n-1;i++){
            arr[randomIndexes[i]] = random.nextInt(57-48 + 1) + 48;
        }
        int lower = 97;
        int upper = lower + sizeOfSymbols;
        int interval = upper - lower + 1;
        arr[randomIndexes[i]] = random.nextInt(interval) + lower;
        for(int j = 0;j<arr.length;j++){
            sb.append((char)arr[j]);
        }
        return sb.toString();

    }
    private static String generator(String randomNumber,int n,int sizeOfSymbols) {
        if(unique(randomNumber)){
            return randomNumber;
        }
        while(!unique(randomNumber)){
            randomNumber = generate(n,sizeOfSymbols);
        }
        return randomNumber;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Please, enter the secret code's length: ");
        try{
        int n = sc.nextInt();
        sc.nextLine();
        System.out.println("Input the number of possible symbols in the code: ");
        int sizeOfSymbols = sc.nextInt();
            if (sizeOfSymbols >= n && sizeOfSymbols <= 36) {
                sc.nextLine();
                print(n, sizeOfSymbols);
                String randomNumber = generate(n, sizeOfSymbols);
                randomNumber = generator(randomNumber, n, sizeOfSymbols);
                System.out.println("Okay, let's start a game!");
                int count = 1;
                System.out.println("Turn " + count + ":");
                String guess = sc.nextLine();
                while (!guess.equals(randomNumber)) {
                    System.out.println(count(randomNumber, guess));
                    System.out.println("Turn " + ++count + ":");
                    guess = sc.nextLine();
                }
                System.out.println(count(randomNumber, guess));
                System.out.println("Congratulations! You guessed the secret code.");

            } else if (n > sizeOfSymbols && sizeOfSymbols <= 36) {
                System.out.println("Error: it's not possible to generate a code with a length of " + n + " with " + sizeOfSymbols + " unique symbols.");
                return;
            } else if (sizeOfSymbols > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                return;
            }


    }
        catch (Exception e){
            System.out.println("Error");
        }
    }

    }

