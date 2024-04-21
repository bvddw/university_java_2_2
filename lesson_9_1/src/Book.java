import java.io.Serializable;
import java.util.ArrayList;


public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private ArrayList<Author> authors;
    private int releaseYear;
    private boolean isAvailable;

    public Book() {}

    public Book(String title, ArrayList<Author> authors, int releaseYear) {
        this.title = title;
        this.authors = authors;
        this.releaseYear = releaseYear;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public boolean addAuthor(Author author) {
        if (authors.contains(author)) {
            return false;
        }
        return authors.add(author);
    }

    public boolean removeAuthor(Author author) {
        for (Author a : authors) {
            if (a.equals(author)) {
                return authors.remove(a);
            }
        }
        return false;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void changeAvailability() {
        this.isAvailable = !this.isAvailable;
    }

    @Override
    public String toString() {
        String availability;
        if (isAvailable) {
            availability = "Available";
        } else {
            availability = "Not Available";
        }
        return "----Book---- \n\tTitle: " + '"' + title + '"' + "\n\tReleased in year: " + releaseYear;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Book other = (Book) obj;
        return this.title.equals(other.title)
                && this.authors == other.authors
                && this.releaseYear == other.releaseYear
                && this.isAvailable == other.isAvailable;
    }
}
