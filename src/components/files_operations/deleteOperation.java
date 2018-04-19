package components.files_operations;

import java.util.List;

public class deleteOperation implements fileOperation {

    @Override
    public void doOperation(List<String> filesPaths, String destinationDirectory) {
        System.out.println("I am delete operation");
    }
}
