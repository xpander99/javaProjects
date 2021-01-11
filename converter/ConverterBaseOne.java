package converter;

public class ConverterBaseOne implements Convert{
    public void convert(int sourceRadix, String sourceNumber, int targetRadix) {
        if(targetRadix == 1 && sourceRadix == 1) {
            System.out.println(sourceNumber);
        } else if(targetRadix == 1){
            int decimalNumber = Integer.parseInt(sourceNumber, sourceRadix);
            for(int i = 0;i<decimalNumber;i++){
                System.out.print(1);
            }
        } else if(sourceRadix == 1) {
            System.out.println(Integer.toString(sourceNumber.length(), targetRadix));
        }
    }
}