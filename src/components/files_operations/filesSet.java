package components.files_operations;

import java.util.ArrayList;
import java.util.List;

public class filesSet {
    private List<String> filesPaths;
    private fileOperation fileOperation;
    protected String sourceDirectoryPath;
    protected String destinationDirectoryPath;

    public filesSet() {
        this.filesPaths = new ArrayList<>();
    }

    public String getSourceDirectoryPath() {
        return sourceDirectoryPath;
    }

    public void setSourceDirectoryPath(String sourceDirectoryPath) {
        this.sourceDirectoryPath = sourceDirectoryPath;
    }

    public String getDestinationDirectoryPath() {
        return destinationDirectoryPath;
    }

    public void setDestinationDirectoryPath(String destinationDirectoryPath) {
        this.destinationDirectoryPath = destinationDirectoryPath;
    }

    public void addFile(String filePath){
        filesPaths.add(filePath);
    }

    public void removeFiles(){
        filesPaths.clear();
    }

    public void setFileOperation(fileOperation fileOperation){
        this.fileOperation = fileOperation;
    }

    public components.files_operations.fileOperation getFileOperation() {
        return fileOperation;
    }

    public void doOperation(){
        fileOperation.doOperation(filesPaths,destinationDirectoryPath);
    }

    public List<String> getFilesPaths() {
        return filesPaths;
    }
}
