package I18N;

import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;

import java.util.ResourceBundle;

public class TranslateMenu extends TranslateElement {
    private Menu element;

    public TranslateMenu(String key, Menu element) {
        super(key);
        this.element = element;
    }

    @Override
    public void translate(ResourceBundle bundle) {
        element.setText(bundle.getString(key));
    }
}
