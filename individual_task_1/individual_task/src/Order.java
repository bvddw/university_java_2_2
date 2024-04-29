import java.util.TreeMap;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Order implements Externalizable {
    private int orderID;
    private int customerID;
    private TreeMap<Product, Integer> products;
    private float specialPriceRate;

    private static int lastAssignedOrderID = 1;

    public Order(int customerID, TreeMap<Product, Integer> products) {
        this.orderID = lastAssignedOrderID++;
        this.customerID = customerID;
        this.products = products;
        this.specialPriceRate = 1;
    }

    public Order(int customerID, TreeMap<Product, Integer> products, float specialPriceRate) {
        this.orderID = lastAssignedOrderID++;
        this.customerID = customerID;
        this.products = products;
        this.specialPriceRate = specialPriceRate;
    }

    public Order() {
    }

    public int getOrderID() {
        return orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public TreeMap<Product, Integer> getProducts() {
        return products;
    }

    public float calculateTotalPrice() {
        float total = 0;
        for (Product product : products.keySet()) {
            total += product.getProductPrice();
        }
        return total * specialPriceRate;
    }

    @Override
    public String toString() {
        String result = "Order " + orderID;
        for (Product product : products.keySet()) {
            result += " " + product + "\n\tQuantity: " + products.get(product);
        }
        return result;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(orderID);
        out.writeInt(customerID);
        out.writeObject(products);
        out.writeFloat(specialPriceRate);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        orderID = in.readInt();
        customerID = in.readInt();
        products = (TreeMap<Product, Integer>) in.readObject();
        specialPriceRate = in.readFloat();
    }
}