import java.util.ArrayList;

public class ProductCatalog {
    private ArrayList<Product> products;

    public ProductCatalog() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Product getExactProduct(Integer id) {
        return products.get(id - 1);
    }

    public ArrayList<Product> searchProduct(String keyword) {
        ArrayList<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getProductName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    @Override
    public String toString() {
        String productsString = "Products:\n";
        for (Integer i = 0; i < products.size(); i++) {
            productsString += i + 1 + ") ";
            productsString += products.get(i);
            productsString += "\n";
        }
        return productsString;
    }
}