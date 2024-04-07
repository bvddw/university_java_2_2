import java.util.ArrayList;

public class Customer {
    protected Integer customerID;
    protected String customerName;
    protected String customerEmailAddress;
    protected String customerPhoneNumber;
    protected Float customerFunds;
    protected ArrayList<Product> shoppingCart;
    protected ArrayList<Order> orderList;

    private static Integer lastAssignedCustomerID = 0;

    public Customer(String customerName, String customerEmailAddress, String customerPhoneNumber) {
        this.customerID = ++lastAssignedCustomerID;
        this.customerName = customerName;
        this.customerEmailAddress = customerEmailAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerFunds = 0.0f;
        shoppingCart = new ArrayList<>();
        orderList = new ArrayList<>();
    }

    public Customer(Integer customerID, String customerName, String customerEmailAddress, String customerPhoneNumber, Float customerFunds) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmailAddress = customerEmailAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerFunds = customerFunds;
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

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    public boolean addFunds(Float funds) {
        this.customerFunds += funds;
        return true;
    }

    public boolean withdrawFunds(Float funds) {
        if (this.customerFunds - funds < 0) {
            return false;
        }
        this.customerFunds -= funds;
        return true;
    }

    public Float getCustomerFunds() {
        return customerFunds;
    }

    public void addProduct(Product product) {
        shoppingCart.add(product);
    }

    public Boolean removeProduct(Product product) {
        return shoppingCart.remove(product);
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

    public Boolean confirmCart() {
        if (shoppingCart.isEmpty()) {
            return false;
        }
        Float total = calculateTotalPrice();
        if (total > customerFunds) {
            return false;
        }
        customerFunds -= total;
        Order order = new Order(customerID, shoppingCart);
        shoppingCart.remove(order);
        orderList.add(order);
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
                "\n\tFunds: " + customerFunds;
    }

    protected Float calculateTotalPrice() {
        Float totalPrice = 0.0f;
        for (Product product : shoppingCart) {
            totalPrice += product.getProductPrice();
        }
        return totalPrice;
    }
}