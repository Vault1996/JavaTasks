package by.epam.library.entity;

public class Newspaper extends Publication{
    private int periodicity;    //weeks

    public Newspaper() {
    }

    public Newspaper(int pages, int year, String title, String publishingOffice, int periodicity) {
        super(pages, year, title, publishingOffice);
        this.periodicity = periodicity;
    }

    public int getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(int periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "pages=" + getPages() +
                ", year=" + getYear() +
                ", title='" + getTitle() + '\'' +
                ", publisher='" + getPublisher() + '\'' +
                ", periodicity=" + periodicity +
                '}';
    }
}
