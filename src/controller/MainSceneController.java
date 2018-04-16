package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.FileDetails;


import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable{

    @FXML
    TableView<FileDetails> LeftTableView;
    @FXML
    TableColumn<FileDetails,String> FileName;
    @FXML
    TableColumn<FileDetails,Integer> FileSize;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*FileName.setCellValueFactory(new PropertyValueFactory<FileDetails,String>("name"));
        FileSize.setCellValueFactory(new PropertyValueFactory<FileDetails,Integer>("size"));*/
    }
}
