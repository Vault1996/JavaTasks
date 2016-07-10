package by.epam.library.action;

import by.epam.library.comparator.PublicationComparator;
import by.epam.library.entity.Library;
import by.epam.library.entity.Publication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;

public class SortLibrary {
    private static final Logger LOGGER = LogManager.getLogger(PublicationFinder.class.getName());
    public void sortLibrary(Library library) {
        LOGGER.info("Start sorting library by number of pages.");
        ArrayList<Publication> publications = library.getPublications();
        Collections.sort(publications, new PublicationComparator());
        library.setPublications(publications);
        LOGGER.info("Finish sorting.");
    }
}
