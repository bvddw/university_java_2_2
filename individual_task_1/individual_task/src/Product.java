public class Product {
    private String productName;
    private String productBrand;
    private Float productPrice;
    private String productDescription;

    public Product(String productName, String productBrand, Float productPrice, String productDescription) {
        this.productName = productName;
        this.productBrand = productBrand;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
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

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float newProductPrice) {
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
        return "\n\tproductName=" + productName +
                "\n\tproductBrand=" + productBrand +
                "\n\tproductPrice=" + productPrice +
                "\n\tproductDescription=" + productDescription + '\n';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Product)) return false;
        Product product = (Product) obj;
        return productName.equals(product.productName) &&
                productBrand.equals(product.productBrand) &&
                productPrice.equals(product.productPrice) &&
                productDescription.equals(product.productDescription);
    }
}
