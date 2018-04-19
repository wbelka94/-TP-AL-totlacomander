package components.I18N;

import java.util.*;

public final class I18N{
    List<TranslateElement> elements;

    public I18N() {
        this.elements = new ArrayList<>();
    }

    public void addElementToTranslate(TranslateElement element){
        elements.add(element);
    }

    public void changeLanguage(ResourceBundle bundle){
        for(TranslateElement element : elements){
            element.translate(bundle);
        }
    }
}
