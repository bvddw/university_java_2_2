import java.io.Externalizable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class VIPCustomer extends Customer implements Externalizable {
    private float specialPriceRate;

    public VIPCustomer(int customerID, String customerName, String customerEmail, String customerPhoneNumber, float customerFunds, TreeMap<Product, Integer> shoppingCart, ArrayList<Order> orderList) {
        super(customerID, customerName, customerEmail, customerPhoneNumber, customerFunds);
        this.specialPriceRate = 0.8f;
        this.shoppingCart = shoppingCart;
        this.orderList = orderList;
    }

    public VIPCustomer(Customer customer) {
        super(customer.getCustomerID(), customer.getCustomerName(), customer.getCustomerEmail(), customer.getCustomerPhoneNumber(), customer.getCustomerFunds());
        this.specialPriceRate = 0.8f;
        this.shoppingCart = customer.getShoppingCart();
        this.orderList = customer.getOrderList();
    }

    public VIPCustomer() {
    }

    @Override
    protected float calculateTotalPrice() {
        float totalPrice = 0.0f;
        for (Product product : shoppingCart.keySet()) {
            totalPrice += product.getProductPrice();
        }
        return totalPrice * specialPriceRate;
    }

    public boolean confirmCart() {
        if (shoppingCart.isEmpty()) {
            return false;
        }
        float total = calculateTotalPrice();
        if (total > customerFunds) {
            return false;
        }
        customerFunds -= total;
        Order order = new Order(customerID, shoppingCart, specialPriceRate);
        orderList.add(order);
        shoppingCart = new TreeMap<>();
        return true;
    }

    @Override
    public String toString() {
        return "VIP Customer \n\tcustomerID is " + customerID +
                "\n\tName is " + customerName +
                "\n\tFunds: $" + customerFunds;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(customerID);
        out.writeObject(customerName);
        out.writeObject(customerEmail);
        out.writeObject(customerPhoneNumber);
        out.writeFloat(customerFunds);
        out.writeObject(shoppingCart);
        out.writeObject(orderList);
        out.writeFloat(specialPriceRate);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        customerID = in.readInt();
        customerName = (String) in.readObject();
        customerEmail = (String) in.readObject();
        customerPhoneNumber = (String) in.readObject();
        customerFunds = in.readFloat();
        shoppingCart = (TreeMap<Product, Integer>) in.readObject();
        orderList = (ArrayList<Order>) in.readObject();
        specialPriceRate = in.readFloat();
    }
}