package by.epam.library.entity;

import java.util.ArrayList;

public class Library {
    private ArrayList<Publication> publications;

    public Library() {
    }

    public Library(ArrayList<Publication> publications) {
        this.publications = publications;
    }

    public ArrayList<Publication> getPublications() {
        if (publications == null) {
            return null;
        }
        return (ArrayList<Publication>)publications.clone();
    }

    public void setPublications(ArrayList<Publication> publications) {
        this.publications = publications;
    }

    @Override
    public String toString() {
        String result = "Library:\n";
        for (Publication publication : publications) {
            result += publication + "\n";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o.getClass().equals(Library.class))) {
            return false;
        }
        Library library = (Library) o;

        if (publications != null ? !publications.equals(library.publications) : library.publications != null){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return publications != null ? publications.hashCode() : 0;
    }
}
