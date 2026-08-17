public class Book {
    private String title;
    private String author;
    private String category;
    private int year;

    public Book(String title, String author, String category, int year) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Title: '" + title + "', Author: " + author + ", Category: " + category + ", Year: " + year;
    }
}
