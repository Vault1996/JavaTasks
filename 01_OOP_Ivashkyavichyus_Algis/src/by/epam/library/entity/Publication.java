package by.epam.library.entity;

public abstract class Publication {
    private int pages;                  //number of pages
    private int year;                   //year of publication
    private String title;               //title
    private String publisher;           //publishing office

    public Publication() {
    }

    public Publication(int pages, int year, String title, String publisher) {
        this.pages = pages;
        this.year = year;
        this.title = title;
        this.publisher = publisher;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
