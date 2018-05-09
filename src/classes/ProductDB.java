package classes;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {

    //--------------------------
    //  ATTRIBUTES
    //--------------------------
    private List<Product> items;

    //--------------------------
    //  CONSTRUCTORS
    //--------------------------
    public ProductDB(){
        this.items = new ArrayList<>();
    }
    //--------------------------
    //  GETTERS
    //--------------------------
    public List<Product> getItems() {
        return this.items;
    }

    //--------------------------
    //  METHODS
    //--------------------------
    public void add(Product p){
        this.items.add(p);
    }

    public void remove(Product p){
        int size = this.items.size();
        if(size == 0)
            System.out.println("ERROR: List is empty.");
        else
            this.items.remove(p);
    }

    public Product find(int id){

        for(Product p : this.items){
            if(p.getProductID() == id)
                return p;
        }
        System.out.println("There is no product with this id.");
        return null;
    }
    
}
