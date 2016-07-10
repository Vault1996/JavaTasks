package by.epam.library.entity;

public class Book extends Publication {
    private String author;          //author
    private int part;               //part
    private String genre;           //genre

    public Book() {
    }

    public Book(int pages, int year, String title, String publishingOffice, String author, int part, String genre) {
        super(pages, year, title, publishingOffice);
        this.author = author;
        this.part = part;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return  "Book{" +
                "pages=" + getPages() +
                ", year=" + getYear() +
                ", title='" + getTitle() + '\'' +
                ", publisher='" + getPublisher() + '\'' +
                ", author='" + author + '\'' +
                ", part=" + part +
                ", genre='" + genre + '\'' +
                '}';
    }
}
