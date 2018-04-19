package scenes;

import components.I18N.*;
import components.files_operations.copyOperation;
import components.files_operations.filesSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import model.FileRow;


import javax.swing.text.TabableView;
import java.io.File;
import java.net.URL;
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
    Button buttonPaste;
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

    public ObservableList<FileRow> getFiles(){
        ObservableList<FileRow> files = FXCollections.observableArrayList();
        files.add(new FileRow("tmp",123, "10.02.2018"));
        files.add(new FileRow("tmp2",1231235, "10.02.2018"));
        files.add(new FileRow("tmp3",13123, "10.02.2018"));
        files.add(new FileRow("tmp4",1312, "10.02.2018"));
        return files;
    }



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

        leftTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void prepareTableView(){
        columnFileName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnFileSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        columnFileDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnFileName2.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnFileSize2.setCellValueFactory(new PropertyValueFactory<>("size"));
        columnFileDate2.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void bindTranslations(){
        //buttons
        i18n.addElementToTranslate(new TranslateButton("button.cut",buttonCut));
        i18n.addElementToTranslate(new TranslateButton("button.copy",buttonCopy));
        i18n.addElementToTranslate(new TranslateButton("button.paste",buttonPaste));
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
            buttonPaste.setDisable(false);
        }
        else {
            buttonPaste.setDisable(true);
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
        for(File file : dir.listFiles()){
            observableList.add(new FileRow(file.getName(),file.length(), sdf.format(file.lastModified())));
        }
        tableView.setItems(observableList);
    }

    public void onClickCopyButton(){
        filesSet.setFileOperation(new copyOperation());
        loadFileFormDirectory(leftSearchField.getText(),leftTableView);
        loadFileFormDirectory(rightSearchField.getText(),rightTableView);

    }

    public void onClickPasteButton(){

    }
}
