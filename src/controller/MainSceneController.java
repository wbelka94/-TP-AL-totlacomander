package controller;

import I18N.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import model.FileDetails;


import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable{

    @FXML
    TableView<FileDetails> LeftTableView;
    @FXML
    TableColumn<FileDetails,String> FileName;
    @FXML
    TableColumn<FileDetails,Integer> FileSize;
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
    private TableColumn columnDate;
    @FXML
    private TableColumn columnDate2;
    @FXML
    private TableColumn columnName;
    @FXML
    private TableColumn columnName2;
    @FXML
    private TableColumn columnSize;
    @FXML
    private TableColumn columnSize2;
    @FXML
    private Menu menuLanguage;
    @FXML
    private MenuItem menuLanguagePL;
    @FXML
    private MenuItem menuLanguageEN;


    ResourceBundle resources;

    private I18N i18n;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        i18n = new I18N();
        bindTranslations();
        /*FileName.setCellValueFactory(new PropertyValueFactory<FileDetails,String>("name"));
        FileSize.setCellValueFactory(new PropertyValueFactory<FileDetails,Integer>("size"));*/
    }

    private void bindTranslations(){
        //buttons
        i18n.addElementToTranslate(new TranslateButton("button.cut",buttonCut));
        i18n.addElementToTranslate(new TranslateButton("button.copy",buttonCopy));
        i18n.addElementToTranslate(new TranslateButton("button.paste",buttonPaste));
        i18n.addElementToTranslate(new TranslateButton("button.search",buttonSearch));
        i18n.addElementToTranslate(new TranslateButton("button.search",buttonSearch2));
        //table columns
        i18n.addElementToTranslate(new TranslateTableColumn("column.date", columnDate));
        i18n.addElementToTranslate(new TranslateTableColumn("column.date", columnDate2));
        i18n.addElementToTranslate(new TranslateTableColumn("column.name", columnName));
        i18n.addElementToTranslate(new TranslateTableColumn("column.name", columnName2));
        i18n.addElementToTranslate(new TranslateTableColumn("column.size", columnSize));
        i18n.addElementToTranslate(new TranslateTableColumn("column.size", columnSize2));
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
}
