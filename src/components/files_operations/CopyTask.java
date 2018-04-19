package components.files_operations;

import java.io.File;

import javafx.concurrent.Task;

// Copy all file in C:/Windows
public class CopyTask extends Task {

    @Override
    protected Object call() throws Exception {

        int i = 0;
        for (i = 0; i< 100 ; i++) {
            copy();
            this.updateProgress(i, 100);
        }
        this.updateProgress(100, 100);
        return true;
    }

    private void copy() throws Exception {
        //this.updateMessage("Copying: " + file.getAbsolutePath());
        Thread.sleep(100);
    }

}