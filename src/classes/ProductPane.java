package classes;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ProductPane extends Pane {

    public ProductPane() {
        TextField nameTxtFld = new TextField();
        TextField priceTxtFld = new TextField();
        TextField desTxtFld = new TextField();

        setLocation(nameTxtFld, 194, 0);
        setLocation(priceTxtFld, 194, 30);
        setLocation(desTxtFld, 194, 60);

        Label nameLbl = new Label("Name");
        Label priceLbl = new Label("Price");
        Label desLbl = new Label("Description");

        setLocation(nameLbl,103, 4);
        setLocation(priceLbl,103, 34);
        setLocation(desLbl,103, 64);

        this.getChildren().addAll(nameTxtFld,priceTxtFld,desTxtFld,nameLbl,priceLbl,desLbl);
    }

    private void setLocation(Node txt, double X, double Y){
        txt.setLayoutX(X);
        txt.setLayoutY(Y);
    }
}
