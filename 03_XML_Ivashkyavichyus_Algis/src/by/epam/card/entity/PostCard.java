package by.epam.card.entity;

public class PostCard extends OldCard
{

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

    @Override
    public String toString() {
        return "PostCard: {" + super.toString() + ", message='" + message + '\'' + "}";
    }
}
