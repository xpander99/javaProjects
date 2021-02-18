package encryptdecrypt;

import java.util.HashMap;

public class Status {
    private String mode = "enc";
    private int key = 0;
    private String data = "";
    private String in;
    private String out;
    private String alg = "shift";
    private HashMap<String, String> hashMap = new HashMap<>();
    private String[] arg;
    private IReader reader;

    public Status(String[] arg) {
        this.arg = arg;
        populateHash();
        setConfig();
    }

    public String getMode() {
        return mode;
    }

    public int getKey() {
        return key;
    }

    public String getData() {
        return data;
    }

    public String getIn() {
        return in;
    }

    public String getOut() {
        return out;
    }

    public String getAlg() {
        return alg;
    }


    private void setConfig() {
        if (hashMap.containsKey("-mode"))
            mode = hashMap.get("-mode");
        if (hashMap.containsKey("-key"))
            key = Integer.parseInt(hashMap.get("-key"));
        if (hashMap.containsKey("-data"))
            data = hashMap.get("-data");
        if (hashMap.containsKey("-in"))
            in = hashMap.get("-in");
        if (hashMap.containsKey("-out"))
            out = hashMap.get("-out");
        if (hashMap.containsKey("-alg"))
            alg = hashMap.get("-alg");
        if (!data.equals("") && in != null) {
            data = data;
        } else if (in != null) {
            reader = new ReaderFile(in);
            data = reader.read();
        } else {
            data = data;
        }
    }

    private void populateHash() {
        for (int i = 0; i < arg.length; i += 2) {
            hashMap.put(arg[i], arg[i + 1]);
        }
    }


}
