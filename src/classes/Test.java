package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    private static Scanner in = new Scanner(System.in);
    private static ProductDB db1 = new ProductDB();
    private static ProductDB db2 = new ProductDB();
    private static ArrayList<Customer> orders = new ArrayList<>();

    public static void main(String [] args){

        //--------------------------
        //      CREATE DATABASE
        //--------------------------
        Phone p1 = new Phone("Samsung S7","Phone",600,"Samsung","S7",32);
        Phone p2 = new Phone("Apple iPhone X","Phone",800,"Apple","X",32);
        Phone p3 = new Phone("Apple iPhone 6","Phone",500,"Apple","6",16);
        Phone p4 = new Phone("Samsung S8","Phone",750,"Samsung","S8",64);
        Phone p5 = new Phone("Sony Xperia XZ1","Phone",700,"Sony","Xperia XZ1",64);
        TV t1 = new TV("Sony Bravia" , "TV", 400, "Sony", 32, "LED", false);
        TV t2 = new TV("LG OLED" , "TV", 3000, "LG", 55, "LED", true);
        TV t3 = new TV("Blaupunkt XI" , "TV", 300, "Blaupunkt", 43, "LED", false);


        db1.add(p1);
        db1.add(p2);
        db1.add(p3);
        db1.add(p4);
        db1.add(p5);
        db1.add(t1);
        db1.add(t2);
        db1.add(t3);

//        db2.add(p4);
//        db2.add(p5);
//        db2.add(t);

        //--------------------------
        //      TEST PRINT()
        //--------------------------

        System.out.print("\n-------------- Test print()-------------------\n");

        System.out.println(db1.getItems().toString());
        db1.remove(t2);
//        System.out.println(db2.getItems().toString());
        p1.print();
        t1.print();

        //--------------------------
        //      TEST FIND()
        //--------------------------

        System.out.print("\n-------------- Test find()-------------------\n");
        System.out.println(db1.find(1));
        System.out.println(db1.find(10));

//        System.out.println(db2.find(5));
//        System.out.println(db2.find(6));

        //--------------------------
        //      TEST ORDER
        //--------------------------
        System.out.print("\n-------------- Test Order-------------------\n");
        Order o1 = new Order();
        o1.add(p1,3);
        o1.add(p2,5);

        o1.get(1).print();
        o1.get(5).print();

        Order o2 = new Order();
        o2.add(p3,6);
        o2.add(p5,5);


        //--------------------------
        //      TEST CUSTOMER
        //--------------------------

        System.out.print("\n-------------- Test Customer-------------------\n");

        Customer joe = new Customer();
        joe.setName("Joe");
        joe.setAddress("Cork");
        joe.add(o1);
        joe.add(o2);
        joe.print();

        System.out.println(joe.getLength());

        o1.add(t1,3);

        Customer alice = new Customer();
        alice.setName("Alice");
        alice.setAddress("Dublin");
        alice.add(o1);

        joe.print();
        alice.print();

        orders.add(joe);
        orders.add(alice);
        //--------------------------
        //      TEST MENU
        //--------------------------

        System.out.print("\n-------------- Test menu-------------------\n");
        runMenu();
        in.close();
    }

    private static void printMenu(){
        System.out.println("\n-------------MENU----------------\n" +
                "1. Create a Phone.\n" +
                "2. Search for a product by supplying the productid.\n" +
                "3. Display all products.\n" +
                "4. Order Products.\n" +
                "5. Display all orders.\n" +
                "6. Quit\n");
        System.out.print("Choice: ");
    }

    private static void runMenu(){
        int choice = 0;
        do{
            boolean isValid = false;
            do{
                printMenu();
                if(in.hasNextInt()){
                    choice = in.nextInt();
                    isValid = true;
                }
                in.nextLine();
            }while(!isValid);

            System.out.println("---------------------------------");

            switch(choice){
                case 1:
                    Phone p = createPhone();
                    db1.add(p);
                    break;
                case 2:
                    Product pr = getProduct();
                    System.out.println(pr);
                    break;
                case 3:
                    displayAllProducts();
                    break;
                case 4:
                    orderProducts();
                    break;
                case 5:
                    displayOrders();
                    break;
                case 6:
                    System.out.println("You decided to quit.");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

        }while(choice != 6);
    }

    private static Phone createPhone(){
        System.out.print("Enter phone name: ");
        String name = getValidInput("name");

        System.out.print("Enter description: ");
        String description = getValidInput("description");

        System.out.print("Enter price: ");
        double price = getValidInput();

        System.out.print("Enter make: ");
        String make = getValidInput("make");

        System.out.print("Enter model: ");
        String model = getValidInput("model");

        System.out.print("Enter Storage size in GB: ");
        int storage = (int)getValidInput();

        return new Phone(name, description, price, make, model, storage);
    }

    private static Product getProduct(){
        System.out.println("Enter a Product by ID number.");
        int choice = (int)getValidInput();
        return db1.find(choice);
    }

    private static void displayAllProducts(){
        System.out.println(db1.getItems().toString());
    }

    private static void orderProducts(){
        System.out.print("Enter customer name: ");
        String name = getValidInput("customer name");
        Customer c = null;
        for(Customer x : orders)
            if(x.getName().equalsIgnoreCase(name))
                c = x;

        if(c == null) {
            c = new Customer();
            c.setName(name);
            orders.add(c);
        }

        int choice = 0;
        Order o = new Order();
        while(choice != -1){
            System.out.println("Enter a product id and a quantity. Enter -1 to finish");
            System.out.print("Enter id: ");
            choice = (int)getValidInput();
            if(choice != -1) {
                System.out.print("Enter quantity: ");
                int quantity = (int) getValidInput();
                Product p = db1.find(choice);
                if(p != null) {
                    o.add(p, quantity);
                    System.out.printf("You ordered: %d %s%n", quantity, p.getName());
                }

            }
        }
        c.add(o);
    }

    private static void displayOrders(){
        System.out.print("Enter customer name: ");
        String name = getValidInput("customer name");
        for(Customer c : orders){
            if(c.getName().equalsIgnoreCase(name)){
                c.print();
                return;
            }
        }
        System.out.println("No customer with that name in database.");
    }

    private static String getValidInput(String name){
        String str = "";
        boolean isValid;
        do{
            isValid = true;
            str = in.nextLine();
            if(str.length()==0 || !Character.isAlphabetic(str.charAt(0))) {
                System.out.println("ERROR: Invalid Input.");
                System.out.printf("Enter %s: ",name);
                isValid = false;
            }
        }while(!isValid);
        return str;
    }

    private static double getValidInput(){
        boolean isValid;
        double value = 0;
        do{
            isValid = false;
            if(in.hasNextDouble()) {
                value = in.nextDouble();
                isValid = true;
            }
            else
                System.out.print("Enter a number: ");
            in.nextLine();
        }while(!isValid);
        return value;
    }

}
