package by.epam.library.action;

import by.epam.library.entity.Library;
import by.epam.library.entity.Publication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class PublicationFinder {
    private static final Logger LOGGER = LogManager.getLogger(PublicationFinder.class.getName());
    public ArrayList<Publication> findByPages(Library library, int lowerBound, int upperBound) {
        LOGGER.info("Finding by page.");
        if (library == null) {
            return null;
        } else if (lowerBound > upperBound) {
            return new ArrayList<>();
        }
        ArrayList<Publication> result = new ArrayList<>();
        ArrayList<Publication> publications = library.getPublications();
        for (Publication pub : publications) {
            if (pub.getPages() <= upperBound && pub.getPages() >= lowerBound) {
                result.add(pub);
            }
        }
        return result;
    }

    public ArrayList<Publication> findByYear(Library library, int lowerBound, int upperBound) {
        LOGGER.info("Finding by year.");
        if (library == null) {
            return null;
        } else if (lowerBound > upperBound) {
            return new ArrayList<>();
        }
        ArrayList<Publication> result = new ArrayList<>();
        ArrayList<Publication> publications = library.getPublications();
        for (Publication pub : publications) {
            if (pub.getYear() <= upperBound && pub.getYear() >= lowerBound) {
                result.add(pub);
            }
        }
        return result;
    }
    public ArrayList<Publication> findByTitle(Library library, String letters) {
        LOGGER.info("Finding by title.");
        if (library == null) {
            return null;
        }
        ArrayList<Publication> result = new ArrayList<>();
        ArrayList<Publication> publications = library.getPublications();
        for (Publication pub : publications) {
            if (pub.getTitle().contains(letters)) {
                result.add(pub);
            }
        }
        return result;
    }
    public ArrayList<Publication> findByPublisher(Library library, String letters) {
        LOGGER.info("Finding by publisher.");
        if (library == null) {
            return null;
        }
        ArrayList<Publication> result = new ArrayList<>();
        ArrayList<Publication> publications = library.getPublications();
        for (Publication pub : publications) {
            if (pub.getPublisher().contains(letters)) {
                result.add(pub);
            }
        }
        return result;
    }
}
