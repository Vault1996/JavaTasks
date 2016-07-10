package test.by.epam.library;

import by.epam.library.action.PublicationFinder;
import by.epam.library.action.SortLibrary;
import by.epam.library.entity.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PublicationTest {
    @Test
    public void sorterTest() {
        Library actual = new Library();
        Library expected = new Library();
        Book book = new Book(10, 1900, "qwe", "qwe", "qwe", 3, "qwe");
        Newspaper newspaper = new Newspaper(12, 1234, "qwe", "qwe", 23);
        Newsletter newsletter = new Newsletter(8, 1988, "qwe", "qwe", "qwe");
        ArrayList<Publication> pubs1 = new ArrayList<>();
        pubs1.add(book);
        pubs1.add(newspaper);
        pubs1.add(newsletter);
        actual.setPublications(pubs1);
        ArrayList<Publication> pubs2 = new ArrayList<>();
        pubs2.add(newsletter);
        pubs2.add(book);
        pubs2.add(newspaper);
        expected.setPublications(pubs2);

        SortLibrary sortLibrary = new SortLibrary();
        sortLibrary.sortLibrary(actual);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void finderTest() {
        Library actual = new Library();
        Library expected = new Library();
        Book book = new Book(10, 1900, "qwe", "qwe", "qwe", 3, "qwe");
        Newspaper newspaper = new Newspaper(12, 1234, "qwe", "qwe", 23);
        Newsletter newsletter = new Newsletter(8, 1988, "qwe", "qwe", "qwe");
        ArrayList<Publication> pubs1 = new ArrayList<>();
        pubs1.add(book);
        pubs1.add(newspaper);
        pubs1.add(newsletter);
        actual.setPublications(pubs1);
        ArrayList<Publication> pubs2 = new ArrayList<>();
        pubs2.add(book);
        pubs2.add(newsletter);
        expected.setPublications(pubs2);

        PublicationFinder publicationFinder = new PublicationFinder();
        Assert.assertEquals(expected.getPublications(), publicationFinder.findByYear(actual, 1900, 2000));
    }
}