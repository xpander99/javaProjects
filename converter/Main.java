package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            int sourceRadix = sc.nextInt();
            sc.nextLine();
            String sourceNumber = sc.nextLine();
            int targetRadix = sc.nextInt();
            if(targetRadix == 0 || targetRadix > 36 || targetRadix < 0){
                throw new Exception("Error");
            }
            ConverterFactory converterFactory = new ConverterFactory();
            Convert converter = converterFactory.getConvert(sourceRadix, sourceNumber, targetRadix);
            converter.convert(sourceRadix, sourceNumber, targetRadix);
        }
        catch (Exception e){
            System.out.println("Error");
        }
    }
}