package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterFile implements IWriter {
    private File file;

    public WriterFile(String path) {
        this.file = new File(path);
    }

    public void write(String message) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(message);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}

