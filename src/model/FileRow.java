package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileRow {
    private final SimpleStringProperty name;
    private final SimpleLongProperty size;
    private final SimpleStringProperty date;

    public FileRow(String name, long size, String date) {
        this.name = new SimpleStringProperty(name);
        this.size = new SimpleLongProperty(size);
        this.date = new SimpleStringProperty(date);
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
}
