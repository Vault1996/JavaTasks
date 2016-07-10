package by.epam.library.comparator;

import by.epam.library.entity.Publication;

import java.util.Comparator;

public class PublicationComparator implements Comparator<Publication> {

    @Override
    public int compare(Publication o1, Publication o2) {
        return Integer.valueOf(o1.getPages()).compareTo(o2.getPages());
    }
}
