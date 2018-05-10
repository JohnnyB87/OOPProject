package controllers;

import classes.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneController extends ProductController{

    @FXML private PaneFrame paneFrame;
    @FXML private ProductPane superPane;
    @FXML private Label titleLabel;
    @FXML private TextField phoneMake;
    @FXML private TextField phoneModel;
    @FXML private ComboBox<String> phoneSizeGB;
    @FXML private TableView tableView;

    private Phone phone;
    private Validator validator;
    private Connection con;
    private String dbTableName;

    @FXML
    private void initialize(){
//        this.getAllData();
        if(this.paneFrame !=  null) {
            Button confirmButton = this.paneFrame.getConfirmButton();
            String action =  Main.getButtonPressed();
            this.validator = new Validator();
            this.dbTableName = "phone";
            this.phone = new Phone();
            con = Main.getCon();

            if(action.equalsIgnoreCase("add"))
                confirmButton.setOnAction(e -> confirmButtonPressed());
            else if(action.equalsIgnoreCase("delete")) {
                SQLQuery join = new SQLQuery();
                ResultSet rs = join.joinQuery(con,dbTableName, "product" ,"pno","all");
                getAllData(rs);
//                tableView = super.getTableView();
                this.tableView.setEditable(true);
                confirmButton.setOnAction(e -> deleteButtonPressed());
            }

        }
    }



    //---------------------------
    //      GETTERS
    //---------------------------
    public Label getTitleLabel() {
        return titleLabel;
    }

    public TextField getPhoneMake() {
        return phoneMake;
    }

    public TextField getPhoneModel() {
        return phoneModel;
    }

    public ComboBox<String> getPhoneSizeGB() {
        return phoneSizeGB;
    }

    //---------------------------
    //      SETTERS
    //---------------------------
    public void setTitleLabel(Label titleLabel) {
        this.titleLabel = titleLabel;
    }

    public void setPhoneMake(TextField phoneMake) {
        this.phoneMake = phoneMake;
    }

    public void setPhoneModel(TextField phoneModel) {
        this.phoneModel = phoneModel;
    }

    public void setPhoneSizeGB(ComboBox<String> phoneSizeGB) {
        this.phoneSizeGB = phoneSizeGB;
    }

    //---------------------------
    //      METHODS
    //---------------------------

    private void getWindowDetails(){
        validator.setCont(true);
        this.phone.setPNo(validator.getNumber(con, "Product", "p"));
        this.phone.setName(validator.validateTextFieldInputString(superPane.getNameTxtFld().getText()));
        this.phone.setPrice(validator.validateTextFieldInputDouble(superPane.getPriceTxtFld().getText()));
        this.phone.setDescription(validator.validateTextFieldInputString(superPane.getDesTxtFld().getText()));
        this.phone.setMake(validator.validateTextFieldInputString(this.phoneMake.getText()));
        this.phone.setModel(validator.validateTextFieldInputMixed(this.phoneModel.getText()));
        this.phone.setStorageGB(validator.validateTextFieldInputInt(this.phoneSizeGB.getValue()));
    }

    private void confirmButtonPressed() {
        getWindowDetails();
        if (validator.isCont()) {
            SQLQuery insert = new SQLQuery();
            insert.insertQuery(con,"product",phone.getPNo(),phone.getName(),
                    phone.getDescription(), phone.getPrice());
            insert.insertQuery(con, this.dbTableName, phone.getPNo(),phone.getMake(),
                    phone.getModel(),phone.getStorageGB());
        }
        else{
            System.out.println("Wrong input");
        }
    }

    private void deleteButtonPressed() {
        ArrayList<String> selectedItem = (ArrayList<String>) tableView.getSelectionModel().getSelectedItem();
        String pNo = selectedItem.get(0);
        tableView.getItems().remove(selectedItem);
        SQLQuery query = new SQLQuery();
        query.deleteQuery(con, dbTableName,"pNo", pNo);
        query.deleteQuery(con, super.getDbTableName(),"pNo", pNo);
    }



}
