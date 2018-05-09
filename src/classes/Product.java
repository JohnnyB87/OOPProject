package classes;

public class Product {
    //--------------------------
    //  ATTRIBUTES
    //--------------------------
    private String name;
    private String description;
    private double price;
    private final int PRODUCTID;
    private static int idCounter = 1;

    //--------------------------
    //  CONSTRUCTORS
    //--------------------------
    public Product(){
        PRODUCTID = idCounter++;
    }

    public Product(String name, String description, double price){
        this.name = name;
        this.description = description;
        this.price = price;
        this.PRODUCTID = Product.idCounter++;
    }
    //--------------------------
    //  GETTERS
    //--------------------------
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public int getProductID() {
        return PRODUCTID;
    }

    //--------------------------
    //  SETTERS
    //--------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    //--------------------------
    //  EXTRA FUNCTIONALITY
    //--------------------------
    public String toString(){
        return String.format("Product name: %s%n" +
                "Product Description: %s%n" +
                "Product Price: %.2f%n" +
                "Product ID: %d%n"
                ,this.name, this.description, this.price, PRODUCTID);
    }

    public void print(){
        System.out.println(this.toString());
    }
}
