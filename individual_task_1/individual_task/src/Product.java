public class Product implements Comparable<Product> {
    private final int productId;
    private String productName;
    private String productBrand;
    private float productPrice;
    private String productDescription;

    private static int lastAssignedProductID = 0;

    public Product(String productName, String productBrand, float productPrice, String productDescription) {
        this.productId = ++lastAssignedProductID;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String newProductName) {
        productName = newProductName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String newProductBrand) {
        productBrand = newProductBrand;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float newProductPrice) {
        productPrice = newProductPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String newProductDescription) {
        productDescription = newProductDescription;
    }

    @Override
    public String toString() {
        return "\n\tName=" + productName +
                "\n\tBrand=" + productBrand +
                "\n\tPrice=" + productPrice +
                "\n\tDescription=" + productDescription;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Product product)) return false;
        return productName.equals(product.productName) &&
                productBrand.equals(product.productBrand) &&
                Math.abs(productPrice - product.productPrice) <= 0.0f &&
                productDescription.equals(product.productDescription);
    }

    @Override
    public int compareTo(Product otherProduct) {
        return Integer.compare(this.productId, otherProduct.productId);
    }
}
