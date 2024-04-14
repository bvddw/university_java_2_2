import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Shop myShop = new Shop();

        Product product1 = new Product("Iphone 15 256gb", "Apple", 1200.0f, "This is the Iphone 15.");
        Product product2 = new Product("Iphone 15 128gb", "Apple", 1000.0f, "This is the Iphone 15.");
        Product product3 = new Product("Iphone 14 256gb", "Apple", 800.0f, "This is the Iphone 14.");
        Product product4 = new Product("Macbook Pro M2", "Apple", 1600.0f, "This is the Macbook Pro.");

        myShop.addProduct(product1);
        myShop.addProduct(product1, 4);
        myShop.addProduct(product2, 3);
        myShop.addProduct(product3, 2);
        myShop.addProduct(product4, 4);

        System.out.println(myShop);

        TreeMap<Product, Integer> filteredProducts = myShop.searchProduct("Iphone 15");
        System.out.print("Only Iphoneos 15:\n");
        int index = 1;
        for (Product product : filteredProducts.keySet()) {
            System.out.print(index + ") ");
            System.out.print(product);
            System.out.println("\n\tQuantity: " + filteredProducts.get(product));
            index++;
        }

        Customer customer1 = new Customer("ALex", "alex@gmail.com", "+380912834295");
        Customer customer2 = new Customer("Tony", "t0ny@gmail.com", "+380192374583");
        myShop.addNewCustomer(customer1);
        myShop.addNewCustomer(customer2);
        myShop.makeCustomerAVIP(customer2.getCustomerID());
        customer2 = myShop.getCustomer(2);

        myShop.addProductToCart(customer2, myShop.getExactProduct(1), 2);
        myShop.addProductToCart(customer2, myShop.getExactProduct(2));
        myShop.addProductToCart(customer1, myShop.getExactProduct(1), 2);
        myShop.addProductToCart(customer1, myShop.getExactProduct(2));

        customer1.addFunds(2500.0f);
        customer2.addFunds(2500.0f);

        if (customer2.confirmCart()) {
            System.out.println("Confirmed!");
        } else {
            System.out.println("Not confirmed!");
        }
        if (customer1.confirmCart()) {
            System.out.println("Confirmed!");
        } else {
            System.out.println("Not confirmed!");
        }

        System.out.println(customer1.getOrderList());
        System.out.println(customer2.getOrderList());
        System.out.println("First order of second customer:");
        TreeMap<Product, Integer> products = customer2.getOrder(1).getProducts();
        index = 1;
        for (Product product : products.keySet()) {
            System.out.println(index++ + ") " + product);
            System.out.println("\tQuantity: " + products.get(product));
        }

        System.out.println("First order of first customer:");
        products = customer1.getOrder(2).getProducts();
        index = 1;
        for (Product product : products.keySet()) {
            System.out.println(index++ + ") " + product);
            System.out.println("\tQuantity: " + products.get(product));
        }

        System.out.println(customer1);
        System.out.println(customer2);
    }
}
