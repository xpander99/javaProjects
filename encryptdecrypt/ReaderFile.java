package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReaderFile implements IReader{
    private File file;

    public ReaderFile(String path){
        this.file = new File(path);
    }
    @Override
    public String read() {
        StringBuilder sb = new StringBuilder();
        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNext()){
                sb.append(scanner.nextLine());
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Error");
        }
        return sb.toString();
    }
}
