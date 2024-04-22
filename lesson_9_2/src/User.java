import java.io.Serializable;
import java.util.ArrayList;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private ArrayList<Book> books;
    private ArrayList<Rental> rentals;

    private static int lastAssignedID = 0;

    public User() {}

    public User(String firstName, String lastName) {
        this.userId = ++lastAssignedID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    public User(int userId, String firstName, String lastName, ArrayList<Book> books, ArrayList<Rental> rentals) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
        this.rentals = rentals;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Rental> getRentals() {
        return rentals;
    }

    public boolean takeBook(Book book) {
        if (!book.isAvailable()) {
            return false;
        }
        books.add(book);
        book.changeAvailability();
        return true;
    }

    public boolean returnBook(Book book) {
        if (!books.contains(book)) {
            return false;
        }
        books.remove(book);
        book.changeAvailability();
        rentals.add(new Rental(userId, book));
        return true;
    }

    @Override
    public String toString() {
        String result = "----User----\n";
        result += "\tUser ID: " + userId + "\n";
        result += "\tFirst Name: " + firstName + "\n";
        result += "\tLast Name: " + lastName + "\n";
        if (!books.isEmpty()) {
            String myBooks = "\tBooks:";
            for (Book book : books) {
                myBooks += "\n\t" + book.toString();
            }
            result += myBooks;
        }
        if (!rentals.isEmpty()) {
            String myRentals = "\tRentals:";
            for (Rental rental : rentals) {
                myRentals += "\n\t" + rental.toString();
            }
            result += myRentals;
        }
        return result;
    }
}
