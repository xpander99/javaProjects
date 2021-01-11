package converter;

public class ConverterFraction implements Convert{
    private String[] numbers = new String[2];
    private String decimalNumber;

    @Override
    public void convert(int sourceRadix, String sourceNumber, int targetRadix) {
        toDecimal(sourceRadix, sourceNumber, targetRadix);
        System.out.println(toTargetBase(decimalNumber, targetRadix));
    }

    private String toDecimal(int sourceRadix, String sourceNumber, int targetRadix) {
        splitForDecimal(sourceNumber);
        int firstNumber = getDecimalFirstPart(numbers, sourceRadix);
        double secondNumber = getDecimalSecondPart(numbers, sourceRadix);
        decimalNumber = Double.toString(firstNumber + secondNumber);
        return decimalNumber;
    }

    private StringBuilder toTargetBase(String decimalNumber, int targetRadix) {
        splitForTarget(decimalNumber);
        StringBuilder targetNumber = new StringBuilder();
        targetNumber.append(getTargetFirstPart (numbers, targetRadix));
        targetNumber.append(".");
        targetNumber.append(getTargetSecondPart (numbers, decimalNumber, targetRadix));
        System.out.println(targetNumber);

        return targetNumber;
    }

    private String[] splitForDecimal(String sourceNumber) {
        numbers = sourceNumber.split("\\.");
        return numbers;
    }

    private int getDecimalFirstPart(String[] numbers, int sourceRadix) {
        int DecimalFirstPart = Integer.parseInt(numbers[0], sourceRadix);
        return DecimalFirstPart;
    }

    private double getDecimalSecondPart(String[] str, int sourceRadix) {
        double decimalValue = 0;
        int power = 1;

        for (int i = 0; i < str[1].length(); i++) {
            decimalValue += (Character.getNumericValue(str[1].charAt(i)) / Math.pow(sourceRadix, power));
            power++;
        }
        return decimalValue;
    }

    private String[] splitForTarget(String decimalNumber) {
        numbers = decimalNumber.split("\\.");
        return numbers;
    }

    private String getTargetFirstPart (String[] decimalNumbers, int targetRadix) {
        String targetFirstPart = Integer.toString(Integer.parseInt(decimalNumbers[0]), targetRadix);
        return targetFirstPart;
    }

    private StringBuilder getTargetSecondPart (String[] decimalNumbers, String decimalNumber, int targetRadix) {
        StringBuilder targetSecondPart = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            double secondPart = Double.parseDouble(decimalNumber) - Integer.parseInt(decimalNumbers[0]);
            if (decimalNumber.equals("0")) {
                secondPart = 0;
            }
            decimalNumbers =  Double.toString(secondPart * targetRadix).split("\\.");
            targetSecondPart = targetSecondPart.append(Character.forDigit(Integer.parseInt(decimalNumbers[0]), targetRadix));
            decimalNumber = Double.toString(secondPart * targetRadix);
        }

        return targetSecondPart;
    }
}
