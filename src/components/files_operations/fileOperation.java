package components.files_operations;

import java.util.List;

interface fileOperation {

    void doOperation(List<String> filesPaths, String destinationDirectory);
}
