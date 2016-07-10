package by.epam.library.entity;

public class Newsletter extends Publication{
    private String purpose;

    public Newsletter() {
    }

    public Newsletter(int pages, int year, String title, String publishingOffice, String purpose) {
        super(pages, year, title, publishingOffice);
        this.purpose = purpose;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "Newsletter{" +
                "pages=" + getPages() +
                ", year=" + getYear() +
                ", title='" + getTitle() + '\'' +
                ", publisher='" + getPublisher() + '\'' +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}
