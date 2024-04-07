import java.util.ArrayList;

public class VIPCustomer extends Customer {
    private Float specialPriceRate;

    public VIPCustomer(Integer customerID, String customerName, String customerEmailAddress, String customerPhoneNumber, Float customerFunds, ArrayList<Product> shoppingCart, ArrayList<Order> orderList) {
        super(customerID, customerName, customerEmailAddress, customerPhoneNumber, customerFunds);
        this.specialPriceRate = 0.8f;
        this.shoppingCart = shoppingCart;
        this.orderList = orderList;
    }

    @Override
    protected Float calculateTotalPrice() {
        Float totalPrice = 0.0f;
        for (Product product : shoppingCart) {
            totalPrice += product.getProductPrice();
        }
        return totalPrice * specialPriceRate;
    }

    @Override
    public String toString() {
        return "VIP Customer \n\tcustomerID is " + customerID +
                "\n\tName is " + customerName +
                "\n\tFunds: " + customerFunds;
    }
}