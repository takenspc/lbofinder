package com.example.lbofinder;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.ibm.icu.util.ULocale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.StringConverter;

class LocaleStringConverter extends StringConverter<Locale> {
    @Override
    public String toString(Locale locale) {
        return locale.getDisplayName();
    }

    @Override
    public Locale fromString(String languageTag) {
        return Locale.forLanguageTag(languageTag);
    }
}

public class AppController implements Initializable {
    @FXML
    TextFlow textflow1;
    @FXML
    TextArea textarea1;
    @FXML
    ComboBox<Locale> locale1;
    @FXML
    CheckBox wrapText1;

    private final Color[] colors = { Color.rgb(128, 0, 0), Color.rgb(0, 64, 32) };
    private static boolean isRTL(Locale locale) {
        return ULocale.forLocale(locale).isRightToLeft();
    }

    @FXML
    protected void updateText(final ActionEvent ev) {
        final String text = textarea1.getText();
        final List<Text> texts = new ArrayList<Text>();
        final Locale locale = locale1.getSelectionModel().getSelectedItem();

        final TextBreaker breaker = new TextBreaker(text, locale);
        for (int i = 0; i < breaker.segments.size(); i++) {
            final String segment = breaker.segments.get(i);
            final Text t = new Text(segment);
            t.setFill(colors[i % colors.length]);
            t.setUnderline(i % colors.length == 0);
            texts.add(t);
        }

        textflow1.getChildren().clear();
        textflow1.getChildren().addAll(texts);
        textflow1.setTextAlignment(isRTL(locale) ? TextAlignment.RIGHT : TextAlignment.LEFT);
    }

    public void initialize(URL location, ResourceBundle resources) {
        textarea1.wrapTextProperty().bind(wrapText1.selectedProperty());
        wrapText1.setSelected(true);

        final ObservableList<Locale> list = FXCollections.observableArrayList();
        list.addAll(Locale.getAvailableLocales());
        locale1.setItems(list.sorted());
        locale1.setConverter(new LocaleStringConverter());

        locale1.getSelectionModel().selectedItemProperty().addListener((r, o, locale) -> {
            textarea1.setNodeOrientation(isRTL(locale) ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT);
        });
        locale1.getSelectionModel().select(Locale.getDefault());
    }
}
