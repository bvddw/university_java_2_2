import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Shop implements Externalizable {
    private TreeMap<Product, Integer> products;
    private HashMap<String, Customer> customers;
    private String filename = "./individual_task_1/shop.ser";

    public Shop() {
        products = new TreeMap<>();
        customers = new HashMap<>();
    }

    public Shop(String path) {
        Shop shop = deserializeShop(path);
        products = shop.getProducts();
        customers = shop.getCustomers();
    }

    // Method to save the shop
    public void save() {
        if (filename != null && !filename.isEmpty()) {
            serializeShop(this, filename);
            System.out.println("Shop saved to " + filename);
        } else {
            System.err.println("Filename is not set. Unable to save shop.");
        }
    }

    // Method to serialize the shop
    private static void serializeShop(Shop shop, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(shop);
            System.out.println("Shop serialized to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to deserialize the shop
    private static Shop deserializeShop(String filename) {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Shop shop = (Shop) in.readObject();
            System.out.println("Shop deserialized from " + filename);
            return shop;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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

    public HashMap<String, Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer(String email) {
        return customers.get(email);
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

    public boolean addProductToCart(String email, Product product, int quantity) {
        if (!customers.containsKey(email)) {
            return false;
        }
        int quan = products.get(product);
        if (quan < quantity) {
            return false;
        }
        Customer customer = customers.get(email);
        customer.addProduct(product, quantity);
        if (quan == quantity) {
            products.put(product, 0);
        } else {
            products.put(product, products.get(product) - quantity);
        }
        return true;
    }

    public boolean addProductToCart(String email, Product product) {
        return addProductToCart(email, product, 1);
    }

    public boolean removeProductFromCart(String email, Product product, int quantity) {
        if (!customers.containsKey(email)) {
            return false;
        }
        Customer customer = customers.get(email);
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

    public boolean removeProductFromCart(String email, Product product) {
        return removeProductFromCart(email, product, 1);
    }

    @Override
    public String toString() {
        String productsString = "SHOP:\n---Products:---";
        int index = 1;
        for (Product product : products.keySet()) {
            productsString += "\n\t" + index + ") " + "\n\tProduct id: " + product.getProductId() + "\n\tProduct Name" +
                    product.getProductName() + "\n\tQuantity: " + products.get(product);
            index++;
        }
        productsString += "\n---Customers:---";
        index = 1;
        for (Customer customer : customers.values()) {
            productsString += "\n\t" + index++ + ")" + "\n\tCustomer Name: " + customer.getCustomerName() +
                    "\n\tCustomer Email: " + customer.getCustomerEmail();
        }
        return productsString;
    }

    public boolean addNewCustomer(Customer customer) {
        if (customers.containsKey(customer.getCustomerEmail())) {
            return false;
        }
        customers.put(customer.getCustomerEmail(), customer);
        return true;
    }

    public boolean removeCustomer(Customer customer) {
        if (customers.containsKey(customer.getCustomerEmail())) {
            customers.remove(customer.getCustomerEmail());
            return true;
        }
        return false;
    }

    public boolean makeCustomerAVIP(String customerEmail) {
        Customer customerToVIP = null;
        if (!customers.containsKey(customerEmail)) {
            return false;
        }
        customerToVIP = customers.get(customerEmail);
        if (customerToVIP instanceof VIPCustomer) {
            return false;
        }
        VIPCustomer customerVIP = new VIPCustomer(customerToVIP);
        removeCustomer(customerToVIP);
        addNewCustomer(customerVIP);
        return true;
    }

    public boolean addFunds(String customerEmail, int amount) {
        if (!customers.containsKey(customerEmail)) {
            return false;
        }
        customers.get(customerEmail).addFunds(amount);
        return true;
    }

    public boolean withdrawFunds(String customerEmail, int amount) {
        if (!customers.containsKey(customerEmail)) {
            return false;
        }
        return customers.get(customerEmail).withdrawFunds(amount);
    }

    public float getCustomerFunds(String email) {
        if (!customers.containsKey(email)) {
            return 0.0f;
        }
        return customers.get(email).getCustomerFunds();
    }

    public boolean cleanShoppingCart(String email) {
        if (!customers.containsKey(email)) {
            return false;
        }
        Customer customer = customers.get(email);
        TreeMap<Product, Integer> shoppingCart = customer.getShoppingCart();
        for (Product product : shoppingCart.keySet()) {
            products.put(product, products.get(product) + shoppingCart.get(product));
        }
        customer.cleanShoppingCart();
        return true;
    }

    public boolean confirmCart(String email) {
        if (!customers.containsKey(email)) {
            return false;
        }
        return customers.get(email).confirmCart();
    }

    public ArrayList<Order> getOrder(String email) {
        if (!customers.containsKey(email)) {
            return null;
        }
        return customers.get(email).getOrderList();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        //private final TreeMap<Product, Integer> products;
        //    private final HashMap<String, Customer> customers;
        out.writeObject(products);
        out.writeObject(customers);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        products = (TreeMap<Product, Integer>) in.readObject();
        customers = (HashMap<String, Customer>) in.readObject();
    }
}