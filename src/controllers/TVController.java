package controllers;

import classes.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TVController extends ProductController{

    @FXML private PaneFrame paneFrame;
    @FXML private ProductPane superPane;
    @FXML private Label titleLabel;
    @FXML private TextField tvMakeTxtFld;
    @FXML private ComboBox tvSizeComboBox;
    @FXML private ComboBox tvTypeComboBox;
    @FXML private CheckBox tv3DCheckBox;
    @FXML private TableView tableView;

    private TV tv;
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
            this.dbTableName = "tv";
            this.tv = new TV();
            con = Main.getCon();
            if(action.equalsIgnoreCase("add"))
                confirmButton.setOnAction(e -> confirmButtonPressed());
            else if(action.equalsIgnoreCase("delete")){
                SQLQuery join = new SQLQuery();
                ResultSet rs = join.joinQuery(con, dbTableName, "product" ,"pno","all");
                getAllData(rs);
                tableView = super.getTableView();
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

    //---------------------------
    //      SETTERS
    //---------------------------

    public void setTitleLabel(Label titleLabel) {
        this.titleLabel = titleLabel;
    }

    //---------------------------
    //      METHODS
    //---------------------------

    private void getWindowDetails(){
        validator.setCont(true);
        this.tv.setPno(validator.getNumber(con, "Product"));
        this.tv.setName(validator.validateTextFieldInputString(superPane.getNameTxtFld().getText()));
        this.tv.setPrice(validator.validateTextFieldInputDouble(superPane.getPriceTxtFld().getText()));
        this.tv.setDescription(validator.validateTextFieldInputString(superPane.getDesTxtFld().getText()));
        this.tv.setMake(validator.validateTextFieldInputString(this.tvMakeTxtFld.getText()));
        this.tv.setScreenSize(validator.validateTextFieldInputInt((String)this.tvSizeComboBox.getValue()));
        this.tv.setType(validator.validateTextFieldInputString((String)this.tvTypeComboBox.getValue()));
        String yesOrNo = this.tv3DCheckBox.isSelected() ? "Yes" : "No";
        this.tv.setIs3DCapable(yesOrNo);
    }

    private void confirmButtonPressed() {
        getWindowDetails();
        if (validator.isCont()) {
            SQLQuery insert = new SQLQuery();
            insert.insertQuery(con,"product",tv.getpNo(),tv.getName(),
                    tv.getDescription(), tv.getPrice());
            insert.insertQuery(con, this.dbTableName, tv.getpNo(),tv.getMake(),
                    tv.getType(), tv.getScreenSize(), tv.is3DCapable());
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
        query.deleteQuery(con, dbTableName, "pNo", pNo);
        query.deleteQuery(con, super.getDbTableName(), "pNo", pNo);
    }

}
