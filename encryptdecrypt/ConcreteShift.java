package encryptdecrypt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConcreteShift implements IStrategyCrypter {
    @Override
    public String encrypt(String message, int key) {
        List<Character> list = getList(message);
        return list.stream()
                .map(i -> shiftBy(i, key))
                .map(i -> i.toString())
                .collect(Collectors.joining());
    }

    private List<Character> getList(String message) {
        char[] charArray = message.toCharArray();
        return IntStream.range(0, charArray.length)
                .mapToObj(i -> charArray[i])
                .collect(Collectors.toList());
    }

    private char shiftBy(char currentLetter, int key) {
        if (!Character.isLetter(currentLetter))
            return currentLetter;
        int firstLowerCaseLetter = 'a';
        int firstUpperCaseLetter = 'A';
        int lastLowerCaseLetter = 'z';
        int lastUpperCaseLetter = 'Z';
        if (Character.isLowerCase(currentLetter)) {
            if (currentLetter + key > lastLowerCaseLetter) {
                int diff = (currentLetter + key) - lastLowerCaseLetter;
                return (char) (firstLowerCaseLetter + diff - 1);
            } else if (currentLetter + key < firstLowerCaseLetter) {
                int diff = Math.abs(key) - (currentLetter - firstLowerCaseLetter);
                return (char) (lastLowerCaseLetter - diff + 1);
            }
        } else if (!Character.isLowerCase(currentLetter)) {
            if (currentLetter + key > lastUpperCaseLetter) {
                int diff = (currentLetter + key) - lastUpperCaseLetter;
                return (char) (firstUpperCaseLetter + diff - 1);
            } else if (currentLetter + key < firstUpperCaseLetter) {
                int diff = Math.abs(key) - (currentLetter - firstUpperCaseLetter);
                return (char) (lastUpperCaseLetter - diff + 1);
            }
        }
        return (char) (currentLetter + key);
    }

    @Override
    public String decrypt(String message, int key) {
        List<Character> list = getList(message);
        return list.stream()
                .map(i -> shiftBy(i, Math.negateExact(key)))
                .map(i -> i.toString())
                .collect(Collectors.joining());
    }
}
