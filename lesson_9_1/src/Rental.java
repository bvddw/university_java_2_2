import java.io.Serializable;
import java.util.Date;

public class Rental implements Serializable {
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
}
