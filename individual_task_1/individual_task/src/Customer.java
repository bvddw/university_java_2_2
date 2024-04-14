import java.util.ArrayList;
import java.util.TreeMap;

public class Customer {
    protected int customerID;
    protected String customerName;
    protected String customerEmailAddress;
    protected String customerPhoneNumber;
    protected float customerFunds;
    protected TreeMap<Product, Integer> shoppingCart;
    protected ArrayList<Order> orderList;

    private static int lastAssignedCustomerID = 0;

    public Customer(String customerName, String customerEmailAddress, String customerPhoneNumber) {
        this.customerID = ++lastAssignedCustomerID;
        this.customerName = customerName;
        this.customerEmailAddress = customerEmailAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerFunds = 0.0f;
        shoppingCart = new TreeMap<Product, Integer>();
        orderList = new ArrayList<>();
    }

    public Customer(int customerID, String customerName, String customerEmailAddress, String customerPhoneNumber, float customerFunds) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmailAddress = customerEmailAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerFunds = customerFunds;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public TreeMap<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void addFunds(float funds) {
        this.customerFunds += funds;
    }

    public boolean withdrawFunds(float funds) {
        if (this.customerFunds - funds < 0) {
            return false;
        }
        this.customerFunds -= funds;
        return true;
    }

    public float getCustomerFunds() {
        return customerFunds;
    }

    public void addProduct(Product product, int quantity) {
        if (shoppingCart.containsKey(product)) {
            shoppingCart.put(product, shoppingCart.get(product) + quantity);
        } else {
            shoppingCart.put(product, quantity);
        }
    }

    public boolean removeProduct(Product product, int quantity) {
        if (!shoppingCart.containsKey(product)) {
            return false;
        }
        if (shoppingCart.get(product) < quantity) {
            return false;
        }
        if (shoppingCart.get(product) - quantity <= 0) {
            shoppingCart.remove(product);
            return true;
        }
        shoppingCart.put(product, shoppingCart.get(product) - quantity);
        return true;
    }

    private void cleanShoppingCart() {
        shoppingCart.clear();
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public Order getOrder(int orderID) {
        for (Order order : orderList) {
            if (order.getOrderID() == orderID) {
                return order;
            }
        }
        return null;
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
        Order order = new Order(customerID, shoppingCart);
        orderList.add(order);
        shoppingCart = new TreeMap<>();
        return true;
    }

    public VIPCustomer becomeAVIP() {
        System.out.println("Congratulations! " + this.customerName + " is now a VIP customer.");
        return new VIPCustomer(customerID, customerName, customerEmailAddress, customerPhoneNumber, customerFunds, shoppingCart, orderList);
    }

    @Override
    public String toString() {
        return "Customer \n\tcustomerID is " + customerID +
                "\n\tName is " + customerName +
                "\n\tFunds: $" + customerFunds;
    }

    protected float calculateTotalPrice() {
        float totalPrice = 0.0f;
        for (Product product : shoppingCart.keySet()) {
            totalPrice += product.getProductPrice();
        }
        return totalPrice;
    }
}