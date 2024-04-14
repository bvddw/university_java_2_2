import java.util.TreeMap;

public class Order {
    private final int orderID;
    private final int customerID;
    private final TreeMap<Product, Integer> products;
    private final float specialPriceRate;

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
        return "Order " + orderID;
    }
}



//import java.util.ArrayList;
//
//public class Order {
//        private final int orderID;
//        private final int customerID;
//        ArrayList<Product> products;
//
//        private static int lastAssignedOrderID = 1;
//
//        public Order(int customerID, ArrayList<Product> products) {
//            this.orderID = lastAssignedOrderID++;
//            this.customerID = customerID;
//            this.products = products;
//        }
//
//        public int getOrderID() {
//            return orderID;
//        }
//
//        public int getCustomerId() {
//            return customerID;
//        }
//
//        public ArrayList<Product> getProducts() {
//            return products;
//        }
//
//        public float calculateTotalPrice() {
//            float total = 0;
//            for (Product product : products) {
//                total += product.getProductPrice();
//            }
//            return total;
//        }
//
//        @Override
//        public String toString() {
//            return "Order " + orderID;
//        }
//}