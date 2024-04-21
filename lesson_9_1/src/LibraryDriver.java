import java.io.*;
import java.util.ArrayList;

public class LibraryDriver {
    public static void serializeLibrary(Library library, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(library);
            out.close();
            fileOut.close();
            System.out.println("Library serialized to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Library deserializeLibrary(String filename) {
        Library library = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            library = (Library) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Library deserialized from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return library;
    }


    public static void main(String[] args) {
        // Приклад створення об'єктів для серіалізації
        Author author1 = new Author("John", "Doe", 1980);
        Author author2 = new Author("Jane", "Smith", 1975);
        ArrayList<Author> authors1 = new ArrayList<Author>();
        authors1.add(author1);
        ArrayList<Author> authors2 = new ArrayList<Author>();
        authors2.add(author1);
        Book book1 = new Book("Sample Book 1", authors1, 1974);
        Book book2 = new Book("Sample Book 2", authors2, 1999);
        User user1 = new User("Alice", "White");
        User user2 = new User("Bob", "Brown");

        // library creation
        Library library = new Library("myLibrary");
        library.addBook(book1);
        library.addBook(book2);
        library.addUser(user1);
        library.addUser(user2);
        library.takeBook(user1.getUserId(), book1);
        library.returnBook(user1.getUserId(), book1);
        library.takeBook(user1.getUserId(), book1);
        library.returnBook(user1.getUserId(), book1);
        library.takeBook(user1.getUserId(), book1);
        library.returnBook(user1.getUserId(), book1);
        library.takeBook(user1.getUserId(), book1);
        library.takeBook(user1.getUserId(), book2);
        // library before serialization
        System.out.println("LIBRARY BEFORE SERIALIZATION:");
        System.out.println(library);
        // serialization
        serializeLibrary(library, "./lesson_9_1/src/library.ser");
        // deserialization
        Library deserializedLibrary = deserializeLibrary("./lesson_9_1/src/library.ser");
        // library after deserialization
        System.out.println("\n\nLIBRARY AFTER DESERIALIZATION:");
        System.out.println(deserializedLibrary);
    }
}