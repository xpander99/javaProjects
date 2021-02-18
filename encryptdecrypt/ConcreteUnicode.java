package encryptdecrypt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConcreteUnicode implements IStrategyCrypter {

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
