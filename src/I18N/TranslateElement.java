package I18N;

import java.util.ResourceBundle;

public abstract class TranslateElement {
    protected String key;

    public TranslateElement(String key) {
        this.key = key;
    }

    abstract void translate(ResourceBundle bundle);
}
