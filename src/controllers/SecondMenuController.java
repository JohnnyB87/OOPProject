package controllers;

import classes.PaneFrame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class SecondMenuController {

    @FXML private Button newPhone;
    @FXML private Button newTV;
    @FXML private Label titleLabel;

    private PaneFrame pane;
    private String menuName;
    private String buttonPressed;
    private int width = 400;
    private int height = 350;

    @FXML
    private void initialize(){
        this.newPhone.setOnAction(e->{
            this.buttonPressed=this.newPhone.getText();
            buttonPressed();
        });
        this.newTV.setOnAction(e->{
            this.buttonPressed=this.newTV.getText();
            buttonPressed();
        });
    }

    void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setPhoneButtonText(String text){
        this.newPhone.setText(text);
    }

    public void setTVButtonText(String text){
        this.newTV.setText(text);
    }

    public Label getTitleLabel() {
        return this.titleLabel;
    }

    private void buttonPressed()  {
        try {
            String s = this.menuName;
            if (!this.menuName.equalsIgnoreCase("add")) {
                s = "viewDeleteUpdate";
                this.width = 500;
                this.height = 400;
            }
            String str = String.format("../resources/%s/%sWindow.fxml",s ,this.buttonPressed);
            if(this.menuName.equalsIgnoreCase("view"))
                str = String.format("../resources/%s/View%sWindow.fxml",s, this.buttonPressed);

            FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
            this.pane = loader.load();
            if (this.buttonPressed.equalsIgnoreCase("phone")) {
                PhoneController pController = loader.getController();
                pController.getTitleLabel().setText(String.format("%s Phone Product", this.menuName));
            }
            else if (this.buttonPressed.equalsIgnoreCase("tv")) {
                TVController tvController = loader.getController();
                tvController.getTitleLabel().setText(String.format("%s TV Product", this.menuName));
            }
            else if (this.buttonPressed.equalsIgnoreCase("product")) {
                ProductController pController = loader.getController();
                pController.getTitleLabel().setText("View Products");
            }
            else {
                ViewController vController = loader.getController();
                vController.getTitleLabel().setText("View Orders");
            }
            this.pane.setConfirmButtonText(this.menuName);
            createNewStage(String.format("%s %s record", this.menuName, this.buttonPressed), width, height);
        }catch(IOException ioe){
            System.out.println("Window not loading");
            ioe.printStackTrace();
        }
    }

    private void createNewStage(String title, int width, int height){
        StackPane sp = new StackPane();
        sp.getChildren().add(this.pane);

        Scene scene = new Scene(sp,width,height);
        Stage stage = new Stage();

        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initOwner(MainMenuController.getStage());
        stage.showAndWait();
    }
}
