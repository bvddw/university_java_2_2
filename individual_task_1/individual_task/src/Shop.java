import java.util.ArrayList;

public class Shop {
    private static ProductCatalog productCatalog = new ProductCatalog();

    public static void main(String[] args) {
        productCatalog.addProduct(new Product("Iphone 15 256gb", "Apple", 1200.0f, "This is the Iphone 15."));
        productCatalog.addProduct(new Product("Iphone 15 128gb", "Apple", 1000.0f, "This is the Iphone 15."));
        productCatalog.addProduct(new Product("Iphone 14 256gb", "Apple", 800.0f, "This is the Iphone 14."));
        productCatalog.addProduct(new Product("Macbook Pro M2", "Apple", 1600.0f, "This is the Macbook Pro."));

        System.out.println(productCatalog);

        ArrayList<Product> filteredProducts = productCatalog.searchProduct("Iphone 15");
        System.out.println("Only Iphoneos 15:\n");
        for (int i = 0; i < filteredProducts.size(); i++) {
            System.out.println(i + 1 + ")");
            System.out.println(filteredProducts.get(i));
        };

        Customer customer1 = new Customer("ALex", "alex@gmail.com", "+380912834295");
        Customer customer2 = new Customer("Tony", "t0ny@gmail.com", "+380192374583");
        System.out.println(customer1);
        System.out.println("\n" + customer2);
        customer2 = customer2.becomeAVIP();


        customer2.addProduct(productCatalog.getExactProduct(1));
        customer2.addProduct(productCatalog.getExactProduct(2));
        customer1.addProduct(productCatalog.getExactProduct(2));
        customer1.addProduct(productCatalog.getExactProduct(1));

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
        for (int i = 0; i < customer2.getOrder(1).getProducts().size(); i++)
        {
            System.out.print(i + 1 + ")");
            System.out.println(customer2.getOrder(1).getProducts().get(i));
        }

        System.out.println("First order of first customer:");
        for (int i = 0; i < customer1.getOrder(2).getProducts().size(); i++)
        {
            System.out.print(i + 1 + ")");
            System.out.println(customer1.getOrder(2).getProducts().get(i));
        }

        System.out.println(customer1);
        System.out.println(customer2);
    }
}