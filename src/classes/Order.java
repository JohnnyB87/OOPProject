package classes;

import java.util.ArrayList;

public class Order {


    private ArrayList<OrderDetails> od;

    //-----------------------------
    //      CONSTRUCTORS
    //-----------------------------
    public Order(){
        this.od = new ArrayList<>();
    }


    //-----------------------------
    //      GETTERS
    //-----------------------------
    public ArrayList<OrderDetails> getOd() {
        return this.od;
    }

    //-----------------------------
    //      SETTERS
    //-----------------------------
    public void setOd(ArrayList<OrderDetails> od) {
        this.od = od;
    }

    //-----------------------------
    //      METHODS
    //-----------------------------
    public void add(Product p, int quantity) {
        OrderDetails o = new OrderDetails();
        o.setProduct(p);
        o.setQuantity(quantity);
        this.od.add(o);
    }

    public OrderDetails get(int i){
        int size = this.od.size();
        for(int j=0;j<size;j++)
            if(j==i)
                return this.od.get(i);
        System.out.println("ERROR: out of bounds.");
        return new OrderDetails();
    }

    public String toString(){
        String str = "";
        for(OrderDetails o : this.od)
            str = o == null ? str : str + o.toString();

        return str;
    }

    public void print() {
        System.out.println(this == null ? "ERROR: NullPointerException":this.toString());
    }
}
