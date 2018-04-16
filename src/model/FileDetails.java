package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileDetails {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty size;

    public FileDetails(String name, Integer size) {
        this.name = new SimpleStringProperty(name);
        this.size = new SimpleIntegerProperty(size);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getSize() {
        return size.get();
    }

    public SimpleIntegerProperty sizeProperty() {
        return size;
    }

    public void setSize(int size) {
        this.size.set(size);
    }
}
