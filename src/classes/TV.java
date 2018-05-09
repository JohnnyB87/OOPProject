package classes;

public class TV extends Product{
    //--------------------------
    //  ATTRIBUTES
    //--------------------------
    private String make;
    private int screenSize;
    private String type;
    private boolean is3DCapable;
    //private final String[] TVTYPES = {"LED","Plasma","LCD"};

    //--------------------------
    //  CONSTRUCTORS
    //--------------------------

    public TV(){
        super();
    }

    public TV(String name, String description, double price, String make, int screenSize, String type, boolean is3dCapable){
        super(name, description, price);
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

    public int getScreenSize() {
        return screenSize;
    }

    public String getType() {
        return type;
    }

    public boolean is3DCapable() {
        return is3DCapable;
    }

    //--------------------------
    //  SETTERS
    //--------------------------

    public void setMake(String make) {
        this.make = make;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setIs3DCapable(boolean is3DCapable) {
        this.is3DCapable = is3DCapable;
    }

    //--------------------------
    //  EXTRA FUNCTIONALITY
    //--------------------------
    public String toString(){
        return String.format("%sProduct Make: %s%n" +
                        "Product Screen Size: %d%n" +
                        "Product Type: %s%n" +
                        "Product is 3D capable: %b%n"
                ,super.toString(), this.make, this.screenSize, this.type,this.is3DCapable);
    }

    public void print(){
        System.out.println(this.toString());
    }
}
