package components.files_operations;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class ProgressDialog {
    private ProgressBar progressBar;
    private Label label;
    private Button cancelButton;

    ProgressDialog(Task task) throws IOException {
        ResourceBundle resources = ResourceBundle.getBundle("bundles.message");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scenes/ProgressScene.fxml"),resources);
        Parent root = null;
        root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle(resources.getString("progressDialogTitle"));
        stage.setScene(new Scene(root, 250, 100));


        progressBar = (ProgressBar)fxmlLoader.getNamespace().get("progressBar");
        progressBar.progressProperty().bind(task.progressProperty());
        label = (Label)fxmlLoader.getNamespace().get("label");
        label.textProperty().bind(task.messageProperty());
        cancelButton = (Button) fxmlLoader.getNamespace().get("cancelButton");
        cancelButton.setOnAction(event -> {
            task.cancel();
            stage.close();
        });

        task.setOnSucceeded(event -> stage.close());

        stage.show();
    }
}
