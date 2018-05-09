package controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {


    @FXML private TableView tableView;
    private List<String> colListNames;
    private List<List<String>> data;

    public void getAllData(ResultSet rs){
        this.tableView.getColumns().clear();
        this.colListNames = new ArrayList<>();
        this.data = new ArrayList<>();
        try {
            ResultSetMetaData meta = rs.getMetaData();
            int count = meta.getColumnCount();

            for(int i=1;i<=count;i++){
                this.colListNames.add(meta.getColumnName(i));
            }

            while(rs.next()){
                List<String> row = new ArrayList<>();
                for(int i=1; i<=count;i++){
                    String s = rs.getString(i);
                    row.add(s);
                }
                data.add(row);
            }

            fillTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillTable(){
        for (int i = 0 ; i < colListNames.size() ; i++) {
            TableColumn<List<String>, String> column = new TableColumn<>(colListNames.get(i));
            int columnIndex = i ;
            column.setCellValueFactory(cellData ->
                    new SimpleObjectProperty<>(cellData.getValue().get(columnIndex)));
            tableView.getColumns().add(column);

        }
        tableView.getItems().setAll(this.data);
    }
}
