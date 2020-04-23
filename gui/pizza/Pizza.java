package gui.pizza;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Pizza extends Application
{
    private String zutaten; // Zum erfassen der ausgewählten Zutaten

    private String groesse; // Zum erfassen der gewählten Größe

    private int preis; // Zum erfassen des Gesamtpreises

    /**
     * start Methode zum erzeugen der Oberfläche
     * 
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        var x = ParameterConverter.createConfiguration(getParameters().getNamed());

        // Grid pane als root
        GridPane root = new GridPane();
        root.setPadding(new Insets(10)); // Abstand vom Rand
        root.setHgap(10); // Horizontaler Abstand

        // Kleinere Pane für die Checkboxes besser FlowPane
        GridPane cbPane = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        // Hbox für die Radio Buttons
        HBox rbBox = new HBox();
        rbBox.setPadding(new Insets(10));
        rbBox.setSpacing(10);

        // ToggleGroup für die Radio Buttons
        ToggleGroup tGroup = new ToggleGroup();
        // Array um die Radio Button Anzahl den Kommandozeilenargumenten
        // anpassen zu können
        RadioButton[] radioButtons = new RadioButton[x.getSizeNames().length];
        // Initialisierung der Radio Buttons durch eine for-Schleife
        for (int i = 0; i < radioButtons.length; i++)
        {
            radioButtons[i] = new RadioButton(x.getSizeNames()[i]);
            // Buttons werden zur ToggleGroup hinzugefügt
            radioButtons[i].setToggleGroup(tGroup);
            if (radioButtons[i].getText().contentEquals("normal"))
            {
                radioButtons[i].setSelected(true);
            }
            // Einfügen der Radio Buttons
            rbBox.getChildren().add(radioButtons[i]);

        }

        // Checkboxen für die Zutaten
        CheckBox[] checkBoxs = new CheckBox[x.getToppingNames().length];
        // Initialisierung der Checkboxen im Array
        int defTop = 0;
        for (int i = 0; i < checkBoxs.length; i++)
        {
            checkBoxs[i] = new CheckBox(x.getToppingNames()[i]);
            // Default Toppings
            if (defTop < x.getNumberOfDefaultToppings())
            {
                checkBoxs[defTop].setSelected(true);
                checkBoxs[defTop].setDisable(true);
            }
            defTop++;
            // Einfügen der Radio Buttons
            cbPane.add(checkBoxs[i], 0, i);
        }

        // Text Area für den Bestelltext
        TextArea taAusgabe = new TextArea();
        taAusgabe.setEditable(false);
        taAusgabe.setId("bestelltext");

        // Bestellen Button um die Bestellung abzugeben
        Button btnBestellen = new Button();
        btnBestellen.setText("Bestellen!");
        btnBestellen.setOnAction(e -> pizzaConfig(checkBoxs, radioButtons, taAusgabe));

        // Einfügen der Elemnte in die root Pane
        root.add(cbPane, 0, 0);
        root.add(btnBestellen, 0, 2);
        root.add(rbBox, 0, 1);
        root.add(taAusgabe, 0, 3);

        // Reagiert auf Änderungen der Größe
        changeGridPaneSize(root);

        // Erzeugung der Scene
        Scene scene = new Scene(root);
        // PrimaryStage wird initialisiert
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pizza Bestellen");
        primaryStage.show();
    }

    private void pizzaConfig(CheckBox[] checkBoxs, RadioButton[] radioButtons, TextArea taAusgabe)
    {
        this.zutaten = ""; // Initialisierung
        this.groesse = ""; // Initialisierung
        this.preis = 0; // Initialisierung

        // Zutaten und Preise der gewählten Zutaten werden abgefragt und
        // errechnet
        for (int i = 0; i < checkBoxs.length; i++)
        {
            // If prüft welche checkbox selektiert wurde
            if (checkBoxs[i].isSelected())
            {
                this.zutaten += (checkBoxs[i].getText() + ", ");
                this.preis += ParameterConverter.createConfiguration(getParameters().getNamed()).getToppingPrices()[i];
            }
        }

        // Größen und Preis der gewählten Größe werden abgefragt und errechnet
        for (int i = 0; i < radioButtons.length; i++)
        {
            // If prüft welcher Button selektier wurde
            if (radioButtons[i].isSelected())
            {
                groesse += radioButtons[i].getText();
                this.preis += ParameterConverter.createConfiguration(getParameters().getNamed()).getSizePrices()[i];
            }
        }

        // Preis werden zu einem String gemacht, um ein Komma einzufügen
        String s = "";
        s = String.valueOf(preis);
        String endPreis = "";
        // Komma wird eingefügt
        for (int i = 0; i < s.length(); i++)
        {
            // Komma wird an der drittletzten stelle eingefügt
            if (i == (s.length() - 2))
            {
                endPreis += ",";
            }
            endPreis += s.charAt(i);
        }

        // Text der TextArea wird gesetzt
        taAusgabe.setText("Vielen Dank für ihre Bestellung!" + "\n" + "Ihre Bestellung beinhaltet: " + "\n" + "Zutaten: " + this.zutaten + "\n" + "Größe: " + this.groesse + "\n" + "Der Preis Beträgt: " + endPreis + "€");
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Ändert die Größe der GridPane dynamisch
     * 
     * @param pane
     */
    public void changeGridPaneSize(GridPane pane)
    {
        // Größenanpassung der GridPane root
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
        pane.widthProperty().addListener(new ChangeListener<Object>()
        {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue)
            {
                ColumnConstraints column1 = new ColumnConstraints();
                column1.setMaxWidth((double) newValue);
                column1.setHgrow(Priority.ALWAYS);
                pane.getColumnConstraints().setAll(column1);
            }
        });
    }
}
