package classes;

public class Phone extends Product{
    //--------------------------
    //  ATTRIBUTES
    //--------------------------
    private String make;
    private String model;
    private String storageGB;

    //--------------------------
    //  CONSTRUCTORS
    //--------------------------
    public Phone(){
        super();
    }

    public Phone(String pNo, String name, String description, String price, String make, String model, String storage){
        super(pNo, name, description, price);
        this.make = make;
        this.model = model;
        this.storageGB = storage;
    }
    //--------------------------
    //  GETTERS
    //--------------------------
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getStorageGB() {
        return storageGB;
    }
    //--------------------------
    //  SETTERS
    //--------------------------
    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setStorageGB(String storageGB) {
        this.storageGB = storageGB;
    }

    //--------------------------
    //  EXTRA FUNCTIONALITY
    //--------------------------
    public String toString(){
        return String.format("%sProduct Make: %s%n" +
                        "Product Model: %s%n" +
                        "Product Storage: %sGB%n"
                ,super.toString(), this.make, this.model, this.storageGB);
    }

    public void print(){
        System.out.println(this.toString());
    }
}
