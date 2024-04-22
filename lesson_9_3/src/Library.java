import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;

public class Library implements Externalizable {
    private static final long serialVersionUID = 1L;
    private String name;
    private ArrayList<Book> books;
    private HashMap<Integer, User> users;

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeObject(books);
        out.writeObject(users);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        books = (ArrayList<Book>) in.readObject();
        users = (HashMap<Integer, User>) in.readObject();
    }
}
