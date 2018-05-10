package classes;

public class Product {
    //--------------------------
    //  ATTRIBUTES
    //--------------------------
    private String name;
    private String description;
    private String price;
    private String pNo;

    //--------------------------
    //  CONSTRUCTORS
    //--------------------------
    public Product(){

    }

    public Product(String pNo, String name, String description, String price){
        this.pNo = pNo;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getPrice() {
        return this.price;
    }

    public String getPNo() {
        return pNo;
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

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPNo(String pNo){ this.pNo = pNo; }
    //--------------------------
    //  EXTRA FUNCTIONALITY
    //--------------------------
    public String toString(){
        return String.format("Product name: %s%n" +
                "Product Description: %s%n" +
                "Product Price: %s%n" +
                "Product ID: %s%n"
                ,this.name, this.description, this.price, pNo);
    }

    public void print(){
        System.out.println(this.toString());
    }
}
