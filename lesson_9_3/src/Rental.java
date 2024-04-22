import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

public class Rental implements Externalizable {
    private static final long serialVersionUID = 1L;
    private int rentalID;
    private int customerID;
    private Book book;
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(rentalID);
        out.writeInt(customerID);
        out.writeObject(book);
        out.writeObject(date);
        out.writeInt(lastAssignedID);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        rentalID = in.readInt();
        customerID = in.readInt();
        book = (Book) in.readObject();
        date = (Date) in.readObject();
        lastAssignedID = in.readInt();
    }
}
