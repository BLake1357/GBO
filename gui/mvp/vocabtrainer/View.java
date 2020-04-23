package gui.mvp.vocabtrainer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class View
{
    private Presenter presenter;

    private GridPane pane;

    private Label lblPrompt;

    private Label lblVocable;

    private TextField tfTranslation;

    private Label lblStatus;

    public View(Presenter presenter)
    {
        this.presenter = presenter;
    }

    /**
     * Initialisierunge der View
     */
    public void initView()
    {
        this.pane = new GridPane(); // root Pane
        this.pane.setPadding(new Insets(10)); // Abstand zum Rand
        this.pane.setHgap(10); // Abstand zwischen den Zeilen
        this.pane.setHgap(10); // Abstand zwischen den Spalten

        // Interaktionselemente
        this.lblPrompt = new Label("Bitte Übersetzen Sie: ");
        this.lblVocable = new Label();
        this.lblVocable.setId("vocable");
        this.tfTranslation = new TextField();
        this.tfTranslation.setId("translation");
        Button btnCheck = new Button("Überprüfen");
        btnCheck.setId("check");
        Button btnNext = new Button("weiter");
        btnNext.setId("next");
        this.lblStatus = new Label();
        lblStatus.setId("status");

        // Setzen der Ersten Vokabel
        lblVocable.setText("Hund");

        // Anpassung des Layouts bei Änderungen der Fenstergröße
        adjustLayout();

        // Elemente werden zur pane hinzugefügt
        this.pane.addColumn(0, lblPrompt, lblVocable, btnCheck);
        this.pane.add(lblStatus, 0, 4, 2, 1);
        this.pane.add(tfTranslation, 1, 1);
        this.pane.add(btnNext, 1, 2);

        // Eventhandling
        EventHandler<ActionEvent> h = e -> handle();
        btnCheck.setOnAction(h);
        EventHandler<ActionEvent> h2 = e -> next();
        btnNext.setOnAction(h2);
    }

    /**
     * Eventhandling
     */
    public void handle()
    {
        String english = lblVocable.getText();
        String german = tfTranslation.getText();

        // Abschneiden nicht sichtbarer Zeichen
        german = german.trim();

        presenter.study(german, english);
    }

    /**
     * Setzt das label auf die nächste Vokabel
     */
    public void next()
    {
        tfTranslation.clear();
        lblStatus.setText("");
        presenter.nextVocable();
    }

    public Pane getUI()
    {
        return pane;
    }

    /**
     * Setter für die Vokabel im Label
     * 
     * @param vocab
     */
    public void setVocab(String vocab)
    {
        lblVocable.setText(vocab);
    }

    public void showOkayMessage()
    {
        lblStatus.setText("Lösung war Richtig");
    }

    public void showInputError()
    {
        lblStatus.setText("Sie müssen eine Lösung zur Überprüfung eingeben.");
    }

    public void showTranslationError()
    {
        lblStatus.setText("Lösung war Falsch. Sie können es nochmal versuchen.");
    }

    /**
     * Anpassung der Pane an die Fenstergöße
     */
    public void adjustLayout()
    {
        // Anpassung der Höhe
        pane.heightProperty().addListener(new ChangeListener<Object>()
        {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue)
            {
                RowConstraints row1 = new RowConstraints();
                row1.setMaxHeight(((double) newValue));
                row1.setVgrow(Priority.ALWAYS);
                pane.getRowConstraints().setAll(row1, row1, row1, row1);

            }
        });

        // Anpassung der Breite
        pane.widthProperty().addListener(new ChangeListener<Object>()
        {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue)
            {
                ColumnConstraints column1 = new ColumnConstraints();
                column1.setMaxWidth((double) newValue);
                column1.setHgrow(Priority.ALWAYS);
                pane.getColumnConstraints().setAll(column1, column1, column1);
            }
        });
    }
}
