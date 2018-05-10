package controllers;

import classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static javafx.scene.input.KeyCode.ENTER;

public class CustomerController {

    @FXML private PaneFrame paneFrame;
    @FXML private Label titleLabel;
    @FXML private TextField fNameTxtFld;
    @FXML private TextField lNameTxtFld;

    private Connection con;
    private Validator validator;
    private Customer customer;
    private String dbTableName;

    @FXML
    private void initialize(){
        con  = Main.getCon();
        dbTableName = "Customer";
        customer = new Customer();
    }


    private void confirmButtonPressed(){
        validator.setCont(true);
        customer.setFName(validator.validateTextFieldInputString(this.fNameTxtFld.getText()));
        customer.setLName(validator.validateTextFieldInputString(this.lNameTxtFld.getText()));

        if(!validator.isCont())
            System.out.println("Enter correct input");
        else{

            setCustomerNo();
            System.out.println("Database connection established1");
            SQLQuery sqlQuery = new SQLQuery();
//            sqlQuery.insertQuery(con, this.tableName,
//                    customer.getCNo(), customer.getBNo(), customer.getName(),
//                    customer.getAddress(), customer.getContactNo());
        }
        Stage s = (Stage)paneFrame.getScene().getWindow();
        s.close();
    }

    private void setCustomerNo() {
        String fName = customer.getFName();
        String lName =  customer.getLName();
        String cNo;
        SQLQuery findCustomer = new SQLQuery();
        ResultSet  rs = findCustomer.getResultSet(con, dbTableName);
        try {
            while (rs.next()) {
                String rsFName = rs.getString("First Name");
                String rsLName = rs.getString("Last Name");
                if (rsFName.equalsIgnoreCase(fName) && rsLName.equalsIgnoreCase(lName)) {
                    cNo = rs.getString("cNo");
                    customer.setCNo(cNo);
                    return;
                }
            }
        }catch(SQLException sql){
            System.out.println("\n\nERROR: setCustomer() -> CustomerController\n\n");
        }
        cNo = validator.getNumber(con, dbTableName);
        customer.setCNo(cNo);

    }

    public Label getTitleLabel() {
        return titleLabel;
    }
}
