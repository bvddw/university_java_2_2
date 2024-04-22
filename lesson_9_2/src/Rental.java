import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Rental implements Serializable {
    private static final long serialVersionUID = 1L;
    private int rentalID;
    private int customerID;
    private transient Book book;
    private Date date;

    private static int lastAssignedID = 0;

    public Rental() {}

    public Rental(int customerID, Book book) {
        this.rentalID = ++lastAssignedID;
        this.customerID = customerID;
        this.book = book;
        this.date = new Date();
    }

    public int getRentalID() {
        return rentalID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public Book getBook() {
        return book;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        String result = "----Rental----\n" + "\trentalId: " + rentalID;
        result += "\n\tRental date: " + date;
        result += "\n\tCustomerID: " + customerID + "\n\t";
        result += book;
        return result;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(book.getTitle());
        out.writeInt(book.getAuthors().size());
        for (Author author : book.getAuthors()) {
            out.writeObject(author.getName());
            out.writeObject(author.getSurname());
            out.writeInt(author.getYearBirth());
        }
        out.writeInt(book.getReleaseYear());
        out.writeBoolean(book.isAvailable());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        String title = (String) in.readObject();
        int authorCount = in.readInt();
        ArrayList<Author> authors = new ArrayList<>();
        for (int i = 0; i < authorCount; i++) {
            String name = (String) in.readObject();
            String surname = (String) in.readObject();
            int yearBirth = in.readInt();
            authors.add(new Author(name, surname, yearBirth));
        }
        int releaseYear = in.readInt();
        boolean available = in.readBoolean();
        book = new Book(title, authors, releaseYear, available);
    }
}
