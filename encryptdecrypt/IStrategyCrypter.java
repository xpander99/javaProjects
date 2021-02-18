package encryptdecrypt;

public interface IStrategyCrypter {
    String encrypt(String message, int key);

    String decrypt(String message, int key);
}
