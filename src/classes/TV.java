package classes;

public class TV extends Product{
    //--------------------------
    //  ATTRIBUTES
    //--------------------------
    private String make;
    private String screenSize;
    private String type;
    private String is3DCapable;

    //--------------------------
    //  CONSTRUCTORS
    //--------------------------

    public TV(){
        super();
    }

    public TV(String pNo, String name, String description, String price, String make, String screenSize, String type, String is3dCapable){
        super(pNo, name, description, price);
        this.make = make;
        this.screenSize = screenSize;
        this.type = type;
        this.is3DCapable = is3dCapable;
    }
    //--------------------------
    //  GETTERS
    //--------------------------

    public String getMake() {
        return make;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public String getType() {
        return type;
    }

    public String is3DCapable() {
        return is3DCapable;
    }

    //--------------------------
    //  SETTERS
    //--------------------------

    public void setMake(String make) {
        this.make = make;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setIs3DCapable(String is3DCapable) {
        this.is3DCapable = is3DCapable;
    }

    //--------------------------
    //  EXTRA FUNCTIONALITY
    //--------------------------
    public String toString(){
        return String.format("%sProduct Make: %s%n" +
                        "Product Screen Size: %s%n" +
                        "Product Type: %s%n" +
                        "Product is 3D capable: %b%n"
                ,super.toString(), this.make, this.screenSize, this.type,this.is3DCapable);
    }

    public void print(){
        System.out.println(this.toString());
    }
}
