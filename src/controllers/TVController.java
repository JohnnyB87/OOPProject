package controllers;

import classes.ProductPane;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TVController extends ProductController{

    @FXML private ProductPane superPane;
    @FXML private Label titleLabel;
    @FXML private TextField tvMakeTxtFld;
    @FXML private ComboBox tvSizeComboBox;
    @FXML private ComboBox tvTypeComboBox;
    @FXML private CheckBox tv3DCheckBox;

    @FXML
    private void initialize(){

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


}
