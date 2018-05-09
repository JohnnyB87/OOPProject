package controllers;

import classes.PaneFrame;
import classes.ProductPane;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PhoneController extends ProductController{

    @FXML private PaneFrame paneFrame;
    @FXML private ProductPane superPane;
    @FXML private Label titleLabel;
    @FXML private TextField phoneMake;
    @FXML private TextField phoneModel;
    @FXML private ComboBox<String> phoneSizeGB;
    @FXML private  TableView tableView;

    @FXML
    private void initialize(){
//        this.getAllData();
        Button confirmButton = this.paneFrame.getConfirmButton();
        confirmButton.setOnAction(e -> confirmButtonPressed());
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

    private void confirmButtonPressed(){

    }
}
