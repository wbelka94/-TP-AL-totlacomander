package components.files_operations;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class deleteOperation implements fileOperation {

    @Override
    public void doOperation(List<String> filesPaths, String destinationDirectory) {

        Boolean noAll = false;
        Iterator it = filesPaths.iterator();
        while (it.hasNext()) {
            String filePath = (String) it.next();
            File file = new File(filePath);
            if (file.exists()) {
                if(noAll){
                    it.remove();
                    continue;
                }
                try {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText(String.format("Plik %s zostanie usunięty", file.getName()));
                    alert.setContentText("Czy na pewno chcesz go usunąć?");

                    ButtonType buttonYes = new ButtonType("Tak");
                    ButtonType buttonYesAll = new ButtonType("Tak na wszystkie");
                    ButtonType buttonNo = new ButtonType("Nie");
                    ButtonType buttonNoAll = new ButtonType("Nie na wszystkie");

                    alert.getButtonTypes().setAll(buttonYes, buttonYesAll, buttonNo, buttonNoAll);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonYesAll) {
                        break;
                    } else if (result.get() == buttonNoAll) {
                        noAll = true;
                    }else if(result.get() == buttonNo){
                        it.remove();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }


        System.out.println("I am delete operation");
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws IOException, InterruptedException {
                int max = filesPaths.size();
                int progress = 0;
                for(String filePath : filesPaths) {
                    File file = new File(filePath);
                    file.delete();
                    this.updateMessage(filePath);
                    this.updateProgress(++progress,max);
                }
                filesPaths.clear();
                return true;
            }
        };
        try {
            new ProgressDialog(task);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(task).start();
        System.out.println("Files deleted");
    }
}
