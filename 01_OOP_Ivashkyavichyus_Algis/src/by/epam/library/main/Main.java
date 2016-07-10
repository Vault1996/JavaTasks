package by.epam.library.main;

import by.epam.library.action.PublicationFinder;
import by.epam.library.action.SortLibrary;
import by.epam.library.creator.LibraryCreator;
import by.epam.library.entity.Library;
import by.epam.library.entity.Publication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        LOGGER.info("Starting program.");
        LOGGER.info("Creating new library from the file.");
        Library library = new LibraryCreator().createLibrary("file" + File.separator + "publication");
        LOGGER.info("Result:\n" + library);
        SortLibrary sort = new SortLibrary();
        LOGGER.info("Sorting existing library.");
        sort.sortLibrary(library);
        LOGGER.info("Result:\n" + library);
        PublicationFinder publicationFinder = new PublicationFinder();
        LOGGER.info("Finding publications in given range.");
        ArrayList<Publication> subLibrary = publicationFinder.findByYear(library, 1950, 2015);
        LOGGER.info("Result:\n" + subLibrary);
        LOGGER.info("Finishing program.");
    }
}
