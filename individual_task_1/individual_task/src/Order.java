import java.util.ArrayList;

public class Order {
        private Integer orderID;
        private Integer customerID;
        ArrayList<Product> products;

        private static Integer lastAssignedOrderID = 1;

        public Order(Integer customerID, ArrayList<Product> products) {
            this.orderID = lastAssignedOrderID++;
            this.customerID = customerID;
            this.products = products;
        }

        public Integer getOrderID() {
            return orderID;
        }

        public Integer getCustomerId() {
            return customerID;
        }

        public ArrayList<Product> getProducts() {
            return products;
        }

        public Float calculateTotalPrice() {
            float total = 0;
            for (Product product : products) {
                total += product.getProductPrice();
            }
            return total;
        }

        @Override
        public String toString() {
            return "Order " + orderID;
        }
}