package classes;

public class OrderDetails {

    private Product product;
    private int quantity;
    private String oNo;

    //-----------------------------
    //      CONSTRUCTORS
    //-----------------------------
    public OrderDetails(){}

    public OrderDetails(Product p, int q, String oNo){
        this.product = p;
        this.quantity = q;
        this.oNo = oNo;
    }
    //-----------------------------
    //      GETTERS
    //-----------------------------
    public int getQuantity() {
        return quantity;
    }

    public Product getProduct(){
        return this.product;
    }

    //-----------------------------
    //      SETTERS
    //-----------------------------
    public void setProduct(Product p){
        this.product = p;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setONo(String oNo) {
        this.oNo = oNo;
    }

    //-----------------------------
    //      METHODS
    //-----------------------------
    public String toString(){
        String str = this.product == null ? "" : String.format("Product: %s %s, Quantity: %d%n",
                this.product.getName(),this.product.getDescription(), this.quantity);
        return str;
    }

    public void print(){
        System.out.println(this.toString());
    }


}
