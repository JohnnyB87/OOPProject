package classes;

import java.util.ArrayList;

public class Customer {

    private String cNo;
    private String fName;
    private String lName;
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
    public String getCNo() {
        return cNo;
    }

    public String getFName() {
        return this.fName;
    }

    public String getLName() {
        return this.lName;
    }

    public ArrayList<ArrayList<Order>> getOrder() {
        return this.order;
    }

    //-----------------------------
    //      SETTERS
    //-----------------------------
    public void setCNo(String cNo) {
        this.cNo = cNo;
    }

    public void setFName(String name) {
        this.fName = name;
    }

    public void setLName(String address) {
        this.lName = address;
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
        String str = String.format("Name: %s %s%n",this.fName,this.lName);
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
