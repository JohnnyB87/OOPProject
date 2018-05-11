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
        this.order = new ArrayList<>();
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

    //-----------------------------
    //      METHODS
    //-----------------------------
    public int getLength(){
        int counter = 0;
        for(ArrayList<Order> ao : this.order)
            counter++;

        return counter;
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
