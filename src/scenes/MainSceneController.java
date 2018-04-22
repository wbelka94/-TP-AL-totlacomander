package scenes;

import components.I18N.*;
import components.files_operations.copyOperation;
import components.files_operations.deleteOperation;
import components.files_operations.filesSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import model.FileRow;


import javax.swing.text.TabableView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable{

    @FXML
    TableView<FileRow> leftTableView;
    @FXML
    TableColumn<FileRow,String> columnFileName;
    @FXML
    TableColumn<FileRow,Integer> columnFileSize;
    @FXML
    TableColumn<FileRow,Integer> columnFileDate;
    @FXML
    TableView<FileRow> rightTableView;
    @FXML
    TableColumn<FileRow,String> columnFileName2;
    @FXML
    TableColumn<FileRow,Integer> columnFileSize2;
    @FXML
    TableColumn<FileRow,Integer> columnFileDate2;
    @FXML
    Button buttonCut;
    @FXML
    Button buttonCopy;
    @FXML
    Button buttonMove;
    @FXML
    Button buttonSearch;
    @FXML
    Button buttonSearch2;
    @FXML
    Menu menuLanguage;
    @FXML
    MenuItem menuLanguagePL;
    @FXML
    MenuItem menuLanguageEN;
    @FXML
    TextField leftSearchField;
    @FXML
    TextField rightSearchField;


    private FileRow selectedFile;

    private TabableView selectedTable;

    private filesSet filesSet;


    ResourceBundle resources;

    private I18N i18n;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        filesSet = new filesSet();
        this.resources = resources;
        i18n = new I18N();
        bindTranslations();
        prepareTableView();

        loadFileFormDirectory("C:/",leftTableView);
        loadFileFormDirectory("C:/",rightTableView);


        leftSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            loadFileFormDirectory(newValue,leftTableView);
        });
        rightSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            loadFileFormDirectory(newValue,rightTableView);
        });


    }

    private void prepareTableView(){
        leftTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        rightTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        columnFileName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnFileSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        columnFileDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnFileName2.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnFileSize2.setCellValueFactory(new PropertyValueFactory<>("size"));
        columnFileDate2.setCellValueFactory(new PropertyValueFactory<>("date"));

        setDoubleClickHandler(rightTableView,rightSearchField);
        setDoubleClickHandler(leftTableView,leftSearchField);
    }

    private void setDoubleClickHandler(TableView tableView, TextField searchField){
        tableView.setRowFactory( tv -> {
            TableRow<FileRow> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    FileRow rowData = row.getItem();
                    File file = new File(rowData.getPath());
                    if(file.isDirectory()) {
                        searchField.setText(rowData.getPath());
                    }else{
                        try {
                            Desktop.getDesktop().open(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            return row ;
        });
    }

    private void bindTranslations(){
        //buttons
        i18n.addElementToTranslate(new TranslateButton("button.cut",buttonCut));
        i18n.addElementToTranslate(new TranslateButton("button.copy",buttonCopy));
        i18n.addElementToTranslate(new TranslateButton("button.paste", buttonMove));
        i18n.addElementToTranslate(new TranslateButton("button.search",buttonSearch));
        i18n.addElementToTranslate(new TranslateButton("button.search",buttonSearch2));
        //table columns
        i18n.addElementToTranslate(new TranslateTableColumn("column.date", columnFileDate));
        i18n.addElementToTranslate(new TranslateTableColumn("column.date", columnFileDate2));
        i18n.addElementToTranslate(new TranslateTableColumn("column.name", columnFileName));
        i18n.addElementToTranslate(new TranslateTableColumn("column.name", columnFileName2));
        i18n.addElementToTranslate(new TranslateTableColumn("column.size", columnFileSize));
        i18n.addElementToTranslate(new TranslateTableColumn("column.size", columnFileSize2));
        //menu
        i18n.addElementToTranslate(new TranslateMenu("menu.language", menuLanguage));
        //menu items
        i18n.addElementToTranslate(new TranslateMenuItem("menu.language.pl", menuLanguagePL));
        i18n.addElementToTranslate(new TranslateMenuItem("menu.language.en", menuLanguageEN));
    }

    public void onClickMenuPL(){
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.message", new Locale("pl", "PL"));
        resources = bundle;
        i18n.changeLanguage(resources);
    }

    public void onClickMenuEN(){
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.message", new Locale("en", "EN"));
        resources = bundle;
        i18n.changeLanguage(resources);
    }

    public void onSelectFileFromLeft(){
        filesSet.setSourceDirectoryPath(leftSearchField.getText());
        filesSet.setDestinationDirectoryPath(rightSearchField.getText());
        filesSet.removeFiles();
        for( FileRow file : leftTableView.getSelectionModel().getSelectedItems()){
            filesSet.addFile(leftSearchField.getText()+"/"+file.getName());
        }
        if(filesSet.getFilesPaths().size() > 0){
            buttonMove.setDisable(false);
        }
        else {
            buttonMove.setDisable(true);
        }
        System.out.println(filesSet.getFilesPaths().size());
    }

    public void onSelectFileFromRight() {
        createFilesSet(rightTableView,rightSearchField,leftSearchField);
    }


    private void createFilesSet(TableView<FileRow> srcTable, TextField srcSearchField, TextField dstSearchField) {
        filesSet.setSourceDirectoryPath(srcSearchField.getText());
        filesSet.setDestinationDirectoryPath(dstSearchField.getText());
        filesSet.removeFiles();
        for (FileRow file : srcTable.getSelectionModel().getSelectedItems()) {
            filesSet.addFile(srcSearchField.getText() + "/" + file.getName());
        }
        if (filesSet.getFilesPaths().size() > 0) {
            buttonMove.setDisable(false);
        } else {
            buttonMove.setDisable(true);
        }
        System.out.println(filesSet.getFilesPaths().size());
    }

    public void onClickLeftSearchButton(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(resources.getString("directoryChooserTitle"));
        File dir = directoryChooser.showDialog(buttonSearch.getScene().getWindow());
        try {
            leftSearchField.setText(dir.getAbsolutePath());
        }catch (Exception e){}
    }

    public void onClickRightSearchButton(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(resources.getString("directoryChooserTitle"));
        File dir = directoryChooser.showDialog(buttonSearch2.getScene().getWindow());
        try {
            rightSearchField.setText(dir.getAbsolutePath());
        }catch (Exception e){}
    }

    private void loadFileFormDirectory(String path, TableView tableView){
        File dir = new File(path);
        if(!dir.exists() || !dir.isDirectory()){
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        ObservableList<FileRow> observableList = FXCollections.observableArrayList();
        Path backPath = Paths.get(path+"/../").normalize();
        observableList.add(new FileRow("..",0,"",backPath.toString()));
        for(File file : dir.listFiles()){
            observableList.add(new FileRow(file.getName(),file.length()/1024, sdf.format(file.lastModified()), file.getAbsolutePath()));
        }
        tableView.setItems(observableList);
    }

    public void onClickCopyButton(){
        filesSet.setFileOperation(new copyOperation());
        filesSet.doOperation();
    }

    public void onClickDeleteButton(){
        filesSet.setFileOperation(new deleteOperation());
        filesSet.doOperation();
    }
}
