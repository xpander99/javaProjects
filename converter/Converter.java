package converter;

public class Converter implements Convert{

    public void convert(int sourceRadix, String sourceNumber,int targetRadix) {
        int decimalNumber = Integer.parseInt(sourceNumber, sourceRadix);
        String targetNumber = Integer.toString(decimalNumber, targetRadix);
        System.out.println(targetNumber);
    }
}
