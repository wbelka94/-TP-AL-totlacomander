package I18N;

import javafx.scene.control.Button;

import java.util.ResourceBundle;

public class TranslateButton extends TranslateElement {
    private Button element;

    public TranslateButton(String key, Button element) {
        super(key);
        this.element = element;
    }

    @Override
    public void translate(ResourceBundle bundle) {
        element.setText(bundle.getString(key));
    }
}
