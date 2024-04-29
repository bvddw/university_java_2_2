import java.io.*;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop("./individual_task_1/shop.ser");
        System.out.println(shop);
        Product product1 = new Product("Iphone 15 256gb", "Apple", 1200.0f, "This is the Iphone 15.");
        Product product2 = new Product("Iphone 15 128gb", "Apple", 1000.0f, "This is the Iphone 15.");
        Product product3 = new Product("Iphone 14 256gb", "Apple", 800.0f, "This is the Iphone 14.");
        Product product4 = new Product("Macbook Pro M2", "Apple", 1600.0f, "This is the Macbook Pro.");

        shop.addProduct(product1);
        shop.addProduct(product1, 3);
        shop.addProduct(product2, 4);
        shop.addProduct(product3, 4);
        shop.addProduct(product4, 4);
        Customer customer = new Customer("Bill", "b1ll@gmail.com", "+380192374583");
        shop.addNewCustomer(customer);
        System.out.println(shop);
    }
}
