package encryptdecrypt;

public class Initializer {
    private Status status;
    private CrypterContext crypterContext;


    public Initializer(Status status, CrypterContext crypterContext) {
        this.status = status;
        this.crypterContext = crypterContext;
    }

    private void print() {
        switch (status.getMode()) {
            case "enc":
                if (status.getOut() == null) {
                    System.out.println(crypterContext.getMessage());
                } else
                    new WriterFile(status.getOut()).write(crypterContext.getMessage());
                break;
            case "dec":
                if (status.getOut() == null) {
                    System.out.println(crypterContext.getMessage());
                } else
                    new WriterFile(status.getOut()).write(crypterContext.getMessage());
                break;
        }

    }

    public void init() {
        switch (status.getMode()) {
            case "enc":
                try {
                    crypterContext.encrypt(status.getData(), status.getKey());
                    print();
                } catch (Exception e) {
                    System.out.println("Error");
                }
                break;
            case "dec":
                try {
                    crypterContext.decrypt(status.getData(), status.getKey());
                    print();
                } catch (Exception e) {
                    System.out.println("Error");
                }
                break;
        }
    }
}
