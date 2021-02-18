package encryptdecrypt;

public class Main {

    public static void main(String[] args) {
        Status status = new Status(args);
        CrypterContext crypterContext = new CrypterContext(CrypterFactory.getCrypter(status.getAlg()));
        Initializer initializer = new Initializer(status,crypterContext);
        initializer.init();

    }
}
