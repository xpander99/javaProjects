package encryptdecrypt;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static String encryptedMessage;
    private static String decryptedMessage;
    private static final Scanner sc = new Scanner(System.in);
    private static String mode = "enc";
    private static int key = 0;
    private static String data = "";
    private static HashMap<String, String> hashMap;

    private static String getEncryptedMessage() {
        return Main.encryptedMessage;

    }

    private static String getDecryptedMessage() {
        return Main.decryptedMessage;
    }

    private static void encrypt(String message, int key) {
        List<Character> list = getList(message);
        Main.encryptedMessage = list.stream()
                .map(i -> shiftBy(i, key))
                .map(i -> i.toString())
                .collect(Collectors.joining());

    }

    private static void decrypt(String message, int key) {
        List<Character> list = getList(message);
        Main.decryptedMessage = list.stream()
                .map(i -> shiftBy(i, Math.negateExact(key)))
                .map(i -> i.toString())
                .collect(Collectors.joining());
    }

    private static List<Character> getList(String message) {
        char[] charArray = message.toCharArray();
        return IntStream.range(0, charArray.length)
                .mapToObj(i -> charArray[i])
                .collect(Collectors.toList());
    }

    private static char shiftBy(char currentLetter, int key) {
        return (char) (currentLetter + key);
    }

    private static void mainMenu(String mode) {
        switch (mode) {
            case "enc":
                encrypt(data, key);
                System.out.println(getEncryptedMessage());
                break;
            case "dec":
                decrypt(data, key);
                System.out.println(getDecryptedMessage());
                break;
        }
    }

    private static void setConfiguration() {
        if (hashMap.containsKey("-mode"))
            mode = hashMap.get("-mode");
        if (hashMap.containsKey("-key"))
            key = Integer.parseInt(hashMap.get("-key"));
        if (hashMap.containsKey("-data"))
            data = hashMap.get("-data");
    }

    public static void main(String[] args) {
        hashMap = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            hashMap.put(args[i], args[i + 1]);
        }
        setConfiguration();
        mainMenu(mode);

    }
}
