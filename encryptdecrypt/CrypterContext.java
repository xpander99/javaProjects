package encryptdecrypt;

public class CrypterContext {
    private String message;
    private IStrategyCrypter strategyCrypter;

    public CrypterContext(IStrategyCrypter strategyCrypter){
        this.strategyCrypter = strategyCrypter;
    }

    public void encrypt(String message, int key) {
        this.message = strategyCrypter.encrypt(message,key);
    }

    public void decrypt(String message, int key){
        this.message = strategyCrypter.decrypt(message, key);
    }

    public String getMessage(){
        return this.message;
    }
}
