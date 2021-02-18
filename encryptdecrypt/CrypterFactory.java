package encryptdecrypt;

public class CrypterFactory {
    public static IStrategyCrypter getCrypter(String crypter){
        if(crypter.equals("shift"))
            return new ConcreteShift();
        else if(crypter.equals("unicode"))
            return new ConcreteUnicode();
        return null;
    }
}
