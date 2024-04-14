import java.util.ArrayList;
import java.util.TreeMap;

public class Shop {
    private final TreeMap<Product, Integer> products;
    private final ArrayList<Customer> customers;

    public Shop() {
        products = new TreeMap<>();
        customers = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public boolean removeProduct(Product product, int quantity) {
        if (!products.containsKey(product)) {
            return false;
        }
        if (products.get(product) < quantity) {
            return false;
        }
        products.put(product, products.get(product) - quantity);
        return true;
    }

    public boolean removeProduct(Product product) {
        return removeProduct(product, 1);
    }

    public TreeMap<Product, Integer> getProducts() {
        return products;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer(int id) {
        for (Customer customer : customers) {
            if (customer.getCustomerID() == id) {
                return customer;
            }
        }
        return null;
    }

    public Product getExactProduct(int id) {
        for (Product product : products.keySet()) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }

    public TreeMap<Product, Integer> searchProduct(String keyword) {
        TreeMap<Product, Integer> filteredProducts = new TreeMap<Product, Integer>();

        for (Product product : products.keySet()) {
            if (product.getProductName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredProducts.put(product, products.get(product));
            }
        }

        return filteredProducts;
    }

    public boolean addProductToCart(Customer customer, Product product, int quantity) {
        if (!customers.contains(customer)) {
            return false;
        }
        if (!products.containsKey(product)) {
            return false;
        }
        if (products.get(product) < quantity) {
            return false;
        }
        customer.addProduct(product, quantity);
        products.put(product, products.get(product) - quantity);
        return true;
    }

    public boolean addProductToCart(Customer customer, Product product) {
        return addProductToCart(customer, product, 1);
    }

    public boolean removeProductFromCart(Customer customer, Product product, int quantity) {
        if (!customers.contains(customer)) {
            return false;
        }
        if (!products.containsKey(product)) {
            return false;
        }
        if (!customer.removeProduct(product, quantity)) {
            return false;
        }
        customer.removeProduct(product, quantity);
        products.put(product, products.get(product) + quantity);
        return true;
    }

    public boolean removeProductFromCart(Customer customer, Product product) {
        return removeProductFromCart(customer, product, 1);
    }

    @Override
    public String toString() {
        String productsString = "Products:\n";
        int index = 1;
        for (Product product : products.keySet()) {
            productsString += "\n\t" + index + ") " + "\n\tProduct id: " + product.getProductId() + "\n\tProduct Name" +
                    product.getProductName() + "\n\tQuantity: " + products.get(product) + "\n";
            index++;
        }
        return productsString;
    }

    public boolean addNewCustomer(Customer customer) {
        if (customers.contains(customer)) {
            return false;
        }
        customers.add(customer);
        return true;
    }

    public boolean removeCustomer(Customer customer) {
        return customers.remove(customer);
    }

    public boolean makeCustomerAVIP(int customerId) {
        Customer customerToVIP = null;
        for (Customer customer : customers) {
            if (customer.getCustomerID() != customerId) {
            } else {
                customerToVIP = customer;
            }
        }
        if (customerToVIP == null) {
            return false;
        }
        if (customerToVIP instanceof VIPCustomer) {
            return false;
        }
        VIPCustomer customerVIP = new VIPCustomer(customerToVIP);
        removeCustomer(customerToVIP);
        addNewCustomer(customerVIP);
        return true;
    }

//    public static void main(String[] args) {
//        productCatalog.addProduct(new Product("Iphone 15 256gb", "Apple", 1200.0f, "This is the Iphone 15."));
//        productCatalog.addProduct(new Product("Iphone 15 128gb", "Apple", 1000.0f, "This is the Iphone 15."));
//        productCatalog.addProduct(new Product("Iphone 14 256gb", "Apple", 800.0f, "This is the Iphone 14."));
//        productCatalog.addProduct(new Product("Macbook Pro M2", "Apple", 1600.0f, "This is the Macbook Pro."));
//
//        System.out.println(productCatalog);
//
//        ArrayList<Product> filteredProducts = productCatalog.searchProduct("Iphone 15");
//        System.out.println("Only Iphoneos 15:\n");
//        for (int i = 0; i < filteredProducts.size(); i++) {
//            System.out.println(i + 1 + ")");
//            System.out.println(filteredProducts.get(i));
//        };
//
//        Customer customer1 = new Customer("ALex", "alex@gmail.com", "+380912834295");
//        Customer customer2 = new Customer("Tony", "t0ny@gmail.com", "+380192374583");
//        System.out.println(customer1);
//        System.out.println("\n" + customer2);
//        customer2 = customer2.becomeAVIP();
//
//
//        customer2.addProduct(productCatalog.getExactProduct(1));
//        customer2.addProduct(productCatalog.getExactProduct(2));
//        customer1.addProduct(productCatalog.getExactProduct(2));
//        customer1.addProduct(productCatalog.getExactProduct(1));
//
//        customer1.addFunds(2500.0f);
//        customer2.addFunds(2500.0f);
//
//        if (customer2.confirmCart()) {
//            System.out.println("Confirmed!");
//        } else {
//            System.out.println("Not confirmed!");
//        }
//        if (customer1.confirmCart()) {
//            System.out.println("Confirmed!");
//        } else {
//            System.out.println("Not confirmed!");
//        }
//
//        System.out.println(customer1.getOrderList());
//        System.out.println(customer2.getOrderList());
//        System.out.println("First order of second customer:");
//        for (int i = 0; i < customer2.getOrder(1).getProducts().size(); i++)
//        {
//            System.out.print(i + 1 + ")");
//            System.out.println(customer2.getOrder(1).getProducts().get(i));
//        }
//
//        System.out.println("First order of first customer:");
//        for (int i = 0; i < customer1.getOrder(2).getProducts().size(); i++)
//        {
//            System.out.print(i + 1 + ")");
//            System.out.println(customer1.getOrder(2).getProducts().get(i));
//        }
//
//        System.out.println(customer1);
//        System.out.println(customer2);
//    }
}