package I18N;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import java.util.ResourceBundle;

public class TranslateTableColumn extends TranslateElement {
    private TableColumn element;

    public TranslateTableColumn(String key, TableColumn element) {
        super(key);
        this.element = element;
    }

    @Override
    public void translate(ResourceBundle bundle) {
        element.setText(bundle.getString(key));
    }
}
