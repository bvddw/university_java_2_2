import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Shop myShop = new Shop();

        Product product1 = new Product("Iphone 15 256gb", "Apple", 1200.0f, "This is the Iphone 15.");
        Product product2 = new Product("Iphone 15 128gb", "Apple", 1000.0f, "This is the Iphone 15.");
        Product product3 = new Product("Iphone 14 256gb", "Apple", 800.0f, "This is the Iphone 14.");
        Product product4 = new Product("Macbook Pro M2", "Apple", 1600.0f, "This is the Macbook Pro.");

        myShop.addProduct(product1);
        myShop.addProduct(product1, 3);
        myShop.addProduct(product2, 4);
        myShop.addProduct(product3, 4);
        myShop.addProduct(product4, 4);

        System.out.println(myShop);
        System.out.println();
        TreeMap<Product, Integer> filteredProducts = myShop.searchProduct("Iphone 15");
        System.out.print("Only Iphoneos 15:\n");
        int index = 1;
        for (Product product : filteredProducts.keySet()) {
            System.out.println(index++ + ") " + product);
            System.out.println("\tQuantity: " + myShop.getProducts().get(product));
        }

        Customer customer1 = new Customer("ALex", "alex@gmail.com", "+380912834295");
        Customer customer2 = new Customer("Tony", "t0ny@gmail.com", "+380192374583");
        myShop.addNewCustomer(customer1);
        myShop.addNewCustomer(customer2);
        myShop.makeCustomerAVIP("alex@gmail.com");
        customer2 = myShop.getCustomer("t0ny@gmail.com");
        customer1 = myShop.getCustomer("alex@gmail.com");

        myShop.addProductToCart("alex@gmail.com", myShop.getExactProduct(1), 2);
        myShop.addProductToCart("alex@gmail.com", myShop.getExactProduct(2));
        myShop.addProductToCart("t0ny@gmail.com", myShop.getExactProduct(1), 2);
        myShop.addProductToCart("t0ny@gmail.com", myShop.getExactProduct(2));

        customer1.addFunds(2500.0f);
        customer2.addFunds(2500.0f);
        System.out.println();
        if (customer2.confirmCart()) {
            System.out.println(customer2.getCustomerName() + "'s order confirmed.");
        } else {
            System.out.println(customer2.getCustomerName() + "'s order not confirmed.");
        }
        if (customer1.confirmCart()) {
            System.out.println(customer1.getCustomerName() + "'s order confirmed.");
        } else {
            System.out.println(customer1.getCustomerName() + "'s order not confirmed.");
        }
        System.out.println();
        System.out.println(customer1.getCustomerName() + " orders:");
        for (Order order : customer1.getOrderList()) {
            System.out.println(order);
        }
        System.out.println(customer2.getCustomerName() + " orders:");
        for (Order order : customer2.getOrderList()) {
            System.out.println(order);
        }
        System.out.println();
        System.out.println("Customers:");
        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println();
        System.out.println("Testing clean shopping cart method: ");
        System.out.println("Shop before taking products: ");
        index = 1;
        for (Product product : myShop.getProducts().keySet()) {
            System.out.println(index++ + ") " + product);
            System.out.println("\tQuantity: " + myShop.getProducts().get(product));
        }
        myShop.addProductToCart("t0ny@gmail.com", myShop.getExactProduct(4), 2);
        myShop.addProductToCart("t0ny@gmail.com", myShop.getExactProduct(3));
        index = 1;
        System.out.println("\nShop after taking products: ");
        for (Product product : myShop.getProducts().keySet()) {
            System.out.println(index++ + ") " + product);
            System.out.println("\tQuantity: " + myShop.getProducts().get(product));
        }
        myShop.cleanShoppingCart("t0ny@gmail.com");
        System.out.println("\nShop after cleaning cart without confirmation: ");
        index = 1;
        for (Product product : myShop.getProducts().keySet()) {
            System.out.println(index++ + ") " + product);
            System.out.println("\tQuantity: " + myShop.getProducts().get(product));
        }
    }
}
