import java.util.ArrayList;
import java.util.TreeMap;

public class VIPCustomer extends Customer {
    private final float specialPriceRate;

    public VIPCustomer(int customerID, String customerName, String customerEmailAddress, String customerPhoneNumber, float customerFunds, TreeMap<Product, Integer> shoppingCart, ArrayList<Order> orderList) {
        super(customerID, customerName, customerEmailAddress, customerPhoneNumber, customerFunds);
        this.specialPriceRate = 0.8f;
        this.shoppingCart = shoppingCart;
        this.orderList = orderList;
    }

    public VIPCustomer(Customer customer) {
        super(customer.getCustomerID(), customer.getCustomerName(), customer.getCustomerEmailAddress(), customer.getCustomerPhoneNumber(), customer.getCustomerFunds());
        this.specialPriceRate = 0.8f;
        this.shoppingCart = customer.getShoppingCart();
        this.orderList = customer.getOrderList();
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
}