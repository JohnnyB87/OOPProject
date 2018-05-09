package classes;

import java.util.ArrayList;

public class Customer {
    private String name;
    private String address;
    private ArrayList<ArrayList<Order>> order;

    //-----------------------------
    //      CONSTRUCTORS
    //-----------------------------
    public Customer(){
        this.order = new ArrayList<ArrayList<Order>>();
    }
    //-----------------------------
    //      GETTERS
    //-----------------------------
    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public ArrayList<ArrayList<Order>> getOrder() {
        return this.order;
    }

    //-----------------------------
    //      SETTERS
    //-----------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOrder(ArrayList<ArrayList<Order>> order) {
        this.order = order;
    }

    //-----------------------------
    //      METHODS
    //-----------------------------
    public int getLength(){
        int counter = 0;
        for(ArrayList<Order> ao : this.order)
            counter++;

        return counter;
    }

    public void add(Order o){
        int size = o.getOd().size();
        ArrayList<Order> newOrder = new ArrayList<>();
        for(int i=0; i<size; i++){
            OrderDetails od = new OrderDetails(o.get(i).getProduct(),o.get(i).getQuantity());
            Order newO = new Order();
            newO.add(od.getProduct(), od.getQuantity());
            newOrder.add(newO);
        }
        this.order.add(newOrder);
    }

    public String toString(){
        String str = String.format("Name: %s%nAddress: %s%n",this.name,this.address);
        int count = 1;
        for(ArrayList<Order> ao : this.order) {
            str += "Order " + count++ + "\n";
            for (Order o : ao) {
                str += "  " + o.toString();
            }
        }
        return str;
    }

    public void print(){
        System.out.println(this.toString());
    }
}
