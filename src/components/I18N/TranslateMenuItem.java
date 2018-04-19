package components.I18N;

import javafx.scene.control.MenuItem;

import java.util.ResourceBundle;

public class TranslateMenuItem extends TranslateElement {
    private MenuItem element;

    public TranslateMenuItem(String key, MenuItem element) {
        super(key);
        this.element = element;
    }

    @Override
    public void translate(ResourceBundle bundle) {
        element.setText(bundle.getString(key));
    }
}
