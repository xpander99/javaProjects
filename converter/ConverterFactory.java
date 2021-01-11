package converter;

public class ConverterFactory {
    public Convert getConvert(int sourceRadix, String sourceNumber, int targetRadix){
        if (sourceRadix == 1 || targetRadix == 1) {
            return new ConverterBaseOne();
        } else if (sourceNumber.contains(".")) {
            return new ConverterFraction();
        }
        return new Converter();
    }
}