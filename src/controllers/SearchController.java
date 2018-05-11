package controllers;

import classes.Main;
import classes.PaneFrame;
import classes.Product;
import classes.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchController {

    @FXML private TableColumn<Product, String> pNoCol;
    @FXML private TableColumn<Product, String> pNameCol;
    @FXML private TableColumn<Product, String> pDesCol;
    @FXML private TableColumn<Product, String> pPriceCol;
    @FXML private ComboBox<String> searchBox;
    @FXML private PaneFrame paneFrame;
    @FXML private TableView<Product> tableView;

    private Connection con;
    private Validator validator;
    private String pNo;

    @FXML
    private void initialize(){
        Button confirmBtn = paneFrame.getConfirmButton();
        con = Main.getCon();
        validator = new Validator();
        setColumns();
        ObservableList<String> pNoList = validator.createObservableList(this.con, "product");
        this.searchBox.getItems().addAll(pNoList);

        confirmBtn.setOnAction(e->confirmBtnPressed());
    }

    private void confirmBtnPressed(){
        validator.setCont(true);
        pNo = checkComboBox(this.searchBox);
        if(validator.isCont()){
            fillTable();
        }
        else{
            System.out.println("Invalid Input");
        }
    }

    private String checkComboBox(ComboBox cb){
        if(cb.getValue() != null
                && !cb.getValue().toString().isEmpty() )
            return cb.getValue().toString();
        validator.setCont(false);
        return null;
    }

    private void setColumns() {
        this.pNoCol.setCellValueFactory(new PropertyValueFactory<>("PNo"));
        this.pNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.pDesCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        this.pPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }

    private void fillTable(){
        ObservableList<Product> ol = FXCollections.observableArrayList();
        Statement s;
        try {
            s = con.createStatement();
            //Simple Query
            String str = String.format("SELECT * FROM product where Pno='%s'",pNo);
            ResultSet rs = s.executeQuery (str);
            while (rs.next ())
            {
                int index = 1;
                Product p = new Product(rs.getString (index++),rs.getString(index++)
                        ,rs.getString(index++),rs.getString(index));
                ol.add(p);
            }
            this.tableView.setItems(ol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
