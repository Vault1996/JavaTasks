package by.epam.library.creator;

import by.epam.library.entity.Library;
import by.epam.library.parser.FileParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LibraryCreator {
    private static final Logger LOGGER = LogManager.getLogger(LibraryCreator.class.getName());
    public Library createLibrary(String file) {
        LOGGER.info("Start creating new library.");
        FileParser fileParser = new FileParser();
        LOGGER.info("Finish creating new library.");
        return new Library(fileParser.parse(file));
    }
}
