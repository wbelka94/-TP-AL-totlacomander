package model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileRow {
    private final SimpleStringProperty name;
    private final SimpleLongProperty size;
    private final SimpleStringProperty date;
    private final SimpleStringProperty path;

    public FileRow(String name, long size, String date, String path) {
        this.name = new SimpleStringProperty(name);
        this.size = new SimpleLongProperty(size);
        this.date = new SimpleStringProperty(date);
        this.path = new SimpleStringProperty(path);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public long getSize() {
        return size.get();
    }

    public void setSize(int size) {
        this.size.set(size);
    }

    public String getDate() {
        return date.get();
    }


    public void setDate(String date) {
        this.date.set(date);
    }

    public String getPath() {
        return path.get();
    }

    public SimpleStringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }
}
