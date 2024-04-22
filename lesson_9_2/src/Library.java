import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private transient ArrayList<Book> books;
    private transient HashMap<Integer, User> users;

    public Library() {}

    public Library(String name, ArrayList<Book> books, HashMap<Integer, User> users) {
        this.name = name;
        this.books = books;
        this.users = users;
    }

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.users = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public HashMap<Integer, User> getUsers() {
        return users;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book) {
        if (books.contains(book) && book.isAvailable()) {
            return books.remove(book);
        }
        return false;
    }

    public boolean addUser(User user) {
        if (users.containsKey(user.getUserId()))
            return false;
        users.put(user.getUserId(), user);
        return true;
    }

    public boolean removeUser(User user) {
        if (!users.containsKey(user.getUserId()))
            return false;
        users.remove(user.getUserId());
        return true;
    }

    public boolean takeBook(int userId, Book book) {
        if (!users.containsKey(userId))
            return false;
        if (!books.contains(book))
            return false;
        if (!book.isAvailable())
            return false;
        User user = users.get(userId);
        return user.takeBook(book);
    }

    public boolean returnBook(int userId, Book book) {
        if (!users.containsKey(userId))
            return false;
        if (!books.contains(book))
            return false;
        if (book.isAvailable())
            return false;
        User user = users.get(userId);
        return user.returnBook(book);
    }

    @Override
    public String toString() {
        String result = "Library name: " + name + "\nBooks:\n";
        for (Book book : books) {
            result += book + "\n";
            String availability;
            if (book.isAvailable()) {
                availability = "Available";
            } else {
                availability = "Not Available";
            }
            result += "\tAvailable: " + availability + "\n";
        }
        result += "Users:\n";
        for (User user : users.values()) {
            result += user + "\n";
        }
        return result;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(books.size());
        for (Book book : books) {
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
        out.writeInt(users.size());
        for (User user : users.values()) {
            out.writeInt(user.getUserId());
            out.writeObject(user.getFirstName());
            out.writeObject(user.getLastName());
            books = user.getBooks();
            out.writeInt(books.size());
            if (!books.isEmpty()) {
                for (Book book : books) {
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
            }
            ArrayList<Rental> rentals = user.getRentals();
            out.writeInt(rentals.size());
            if (!rentals.isEmpty()) {
                for (Rental rental : rentals) {
                    out.writeObject(rental);
                }
            }
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int bookCount = in.readInt();
        books = new ArrayList<>();
        for (int i = 0; i < bookCount; i++) {
            String title = (String) in.readObject();
            int authorCount = in.readInt();
            ArrayList<Author> authors = new ArrayList<>();
            for (int j = 0; j < authorCount; j++) {
                String name = (String) in.readObject();
                String surname = (String) in.readObject();
                int yearBirth = in.readInt();
                authors.add(new Author(name, surname, yearBirth));
            }
            int releaseYear = in.readInt();
            boolean available = in.readBoolean();
            Book book = new Book(title, authors, releaseYear, available);
            books.add(book);
        }
        int userCount = in.readInt();
        users = new HashMap<>();
        for (int i = 0; i < userCount; i++) {
            int userId = in.readInt();
            String firstName = (String) in.readObject();
            String lastName = (String) in.readObject();
            bookCount = in.readInt();
            ArrayList<Book> userBooks = new ArrayList<>();
            for (int j = 0; j < bookCount; j++) {
                String title = (String) in.readObject();
                int authorCount = in.readInt();
                ArrayList<Author> authors = new ArrayList<>();
                for (int k = 0; k < authorCount; k++) {
                    String name = (String) in.readObject();
                    String surname = (String) in.readObject();
                    int yearBirth = in.readInt();
                    authors.add(new Author(name, surname, yearBirth));
                }
                int releaseYear = in.readInt();
                boolean available = in.readBoolean();
                Book book = new Book(title, authors, releaseYear, available);
                userBooks.add(book);
            }
            int rentalCount = in.readInt();
            ArrayList<Rental> userRentals = new ArrayList<>();
            for (int j = 0; j < rentalCount; j++) {
                Rental rental = (Rental) in.readObject();
                userRentals.add(rental);
            }
            User user = new User(userId, firstName, lastName, userBooks, userRentals);
            users.put(user.getUserId(), user);
        }
    }
}
