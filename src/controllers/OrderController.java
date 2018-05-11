package controllers;

import classes.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderController {

    @FXML private PaneFrame paneFrame;
    @FXML private ComboBox<String> productNoComboBox;
    @FXML private TextField quantityTxtFld;
    @FXML private TextField fNameTxtFld;
    @FXML private TextField lNameTxtFld;

    private OrderDetails orderDetails;
    private Validator validator;
    private Customer customer;
    private Connection con;
    private Order order;
    private String pNo;
    private Boolean addCustomer;


    @FXML private void initialize(){
        orderDetails = new OrderDetails();
        validator = new Validator();
        customer = new Customer();
        con = Main.getCon();
        order = new Order();

        Button confirmBtn = paneFrame.getConfirmButton();
        ObservableList<String> bNoList = validator.createObservableList(this.con, "product");
        this.productNoComboBox.getItems().addAll(bNoList);
        confirmBtn.setOnAction(e->confirmBtnPressed());
    }

    private void validateInput(){
        validator.setCont(true);
        addCustomer = false;
        orderDetails.setQuantity(Integer.parseInt(validator.validateTextFieldInputInt(quantityTxtFld.getText())));
        pNo = checkComboBox(this.productNoComboBox);
        customer.setFName(validator.validateTextFieldInputString(this.fNameTxtFld.getText()));
        customer.setLName(validator.validateTextFieldInputString(this.lNameTxtFld.getText()));
        setCustomerNo();
        if(customer.getCNo() == null || customer.getCNo().isEmpty()) {
            customer.setCNo(validator.getNumber(con, "customer", "c"));
            addCustomer = true;
        }
        order.setONo(validator.getNumber(con, "CustomerOrder","o"));
        order.setCNo(customer.getCNo());
        orderDetails.setONo(order.getONo());
    }

    private void confirmBtnPressed() {
        validateInput();
        if(validator.isCont()){
            addProductToOrderDetails();
            if(addCustomer)
                addCustomerToDB();
                addOrderToDB();
                addOrderDetailsToDB();
        }

    }

    private void addProductToOrderDetails(){
        Product p = new Product();
        p.setPNo(pNo);
        orderDetails.setProduct(p);
    }

    private void addCustomerToDB(){
        SQLQuery query = new SQLQuery();
        query.insertQuery(con, "customer",customer.getCNo(),customer.getFName(),customer.getLName());
    }

    private void addOrderToDB(){
        SQLQuery query = new SQLQuery();
        query.insertQuery(con, "CustomerOrder",order.getONo(),order.getCNo());
    }

    private void addOrderDetailsToDB(){
        SQLQuery query = new SQLQuery();
        query.insertQuery(con, "OrderDetail",order.getONo(),
                orderDetails.getProduct().getPNo(),Integer.toString(orderDetails.getQuantity()));
    }

    private String checkComboBox(ComboBox cb){
        if(cb.getValue() != null
                && !cb.getValue().toString().isEmpty() )
            return cb.getValue().toString();
        validator.setCont(false);
        return null;
    }

    private void setCustomerNo() {
        String fName = customer.getFName();
        String lName = customer.getLName();
        String cNo;
        SQLQuery findCustomer = new SQLQuery();
        ResultSet  rs = findCustomer.getResultSet(con, "customer");
        try {
            while (rs.next()) {
                String rsFName = rs.getString("FirstName");
                String rsLName = rs.getString("LastName");
                if (rsFName.equalsIgnoreCase(fName) && rsLName.equalsIgnoreCase(lName)) {
                    cNo = rs.getString("cNo");
                    customer.setCNo(cNo);
                    return;
                }
            }
        }catch(SQLException sql){
            System.out.println("\n\nERROR: setCustomerNo() -> CustomerController\n\n");
            sql.printStackTrace();
        }
    }
}
