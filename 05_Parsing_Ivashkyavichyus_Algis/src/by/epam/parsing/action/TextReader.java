package by.epam.parsing.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextReader {
    private static final Logger LOGGER = LogManager.getLogger(TextReader.class);
    public String readFromFile(String fileName) {
        /*
        String text = "";
        File file = new File(fileName);
        try (FileReader inFile = new FileReader(file)){
            char[] str = new char[(int)file.length()];
            inFile.read(str);
            text = new String(str);
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found");
        } catch (IOException e) {
            LOGGER.error("Can't read file");
        }
        return text;
        */
        String text = "";
        try {
            Stream<String> streamFromFiles = Files.lines(Paths.get(fileName));
            text = streamFromFiles.reduce((s1, s2) -> s1 + "\r\n" + s2).orElse("");
        } catch (IOException e) {
            LOGGER.error("Can't read file");
        }
        return text;
    }
}
