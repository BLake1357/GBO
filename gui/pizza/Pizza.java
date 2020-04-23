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
    private String zutaten; // Zum erfassen der ausgew�hlten Zutaten

    private String groesse; // Zum erfassen der gew�hlten Gr��e

    private int preis; // Zum erfassen des Gesamtpreises

    /**
     * start Methode zum erzeugen der Oberfl�che
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

        // Kleinere Pane f�r die Checkboxes besser FlowPane
        GridPane cbPane = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        // Hbox f�r die Radio Buttons
        HBox rbBox = new HBox();
        rbBox.setPadding(new Insets(10));
        rbBox.setSpacing(10);

        // ToggleGroup f�r die Radio Buttons
        ToggleGroup tGroup = new ToggleGroup();
        // Array um die Radio Button Anzahl den Kommandozeilenargumenten
        // anpassen zu k�nnen
        RadioButton[] radioButtons = new RadioButton[x.getSizeNames().length];
        // Initialisierung der Radio Buttons durch eine for-Schleife
        for (int i = 0; i < radioButtons.length; i++)
        {
            radioButtons[i] = new RadioButton(x.getSizeNames()[i]);
            // Buttons werden zur ToggleGroup hinzugef�gt
            radioButtons[i].setToggleGroup(tGroup);
            if (radioButtons[i].getText().contentEquals("normal"))
            {
                radioButtons[i].setSelected(true);
            }
            // Einf�gen der Radio Buttons
            rbBox.getChildren().add(radioButtons[i]);

        }

        // Checkboxen f�r die Zutaten
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
            // Einf�gen der Radio Buttons
            cbPane.add(checkBoxs[i], 0, i);
        }

        // Text Area f�r den Bestelltext
        TextArea taAusgabe = new TextArea();
        taAusgabe.setEditable(false);
        taAusgabe.setId("bestelltext");

        // Bestellen Button um die Bestellung abzugeben
        Button btnBestellen = new Button();
        btnBestellen.setText("Bestellen!");
        btnBestellen.setOnAction(e -> pizzaConfig(checkBoxs, radioButtons, taAusgabe));

        // Einf�gen der Elemnte in die root Pane
        root.add(cbPane, 0, 0);
        root.add(btnBestellen, 0, 2);
        root.add(rbBox, 0, 1);
        root.add(taAusgabe, 0, 3);

        // Reagiert auf �nderungen der Gr��e
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

        // Zutaten und Preise der gew�hlten Zutaten werden abgefragt und
        // errechnet
        for (int i = 0; i < checkBoxs.length; i++)
        {
            // If pr�ft welche checkbox selektiert wurde
            if (checkBoxs[i].isSelected())
            {
                this.zutaten += (checkBoxs[i].getText() + ", ");
                this.preis += ParameterConverter.createConfiguration(getParameters().getNamed()).getToppingPrices()[i];
            }
        }

        // Gr��en und Preis der gew�hlten Gr��e werden abgefragt und errechnet
        for (int i = 0; i < radioButtons.length; i++)
        {
            // If pr�ft welcher Button selektier wurde
            if (radioButtons[i].isSelected())
            {
                groesse += radioButtons[i].getText();
                this.preis += ParameterConverter.createConfiguration(getParameters().getNamed()).getSizePrices()[i];
            }
        }

        // Preis werden zu einem String gemacht, um ein Komma einzuf�gen
        String s = "";
        s = String.valueOf(preis);
        String endPreis = "";
        // Komma wird eingef�gt
        for (int i = 0; i < s.length(); i++)
        {
            // Komma wird an der drittletzten stelle eingef�gt
            if (i == (s.length() - 2))
            {
                endPreis += ",";
            }
            endPreis += s.charAt(i);
        }

        // Text der TextArea wird gesetzt
        taAusgabe.setText("Vielen Dank f�r ihre Bestellung!" + "\n" + "Ihre Bestellung beinhaltet: " + "\n" + "Zutaten: " + this.zutaten + "\n" + "Gr��e: " + this.groesse + "\n" + "Der Preis Betr�gt: " + endPreis + "�");
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * �ndert die Gr��e der GridPane dynamisch
     * 
     * @param pane
     */
    public void changeGridPaneSize(GridPane pane)
    {
        // Gr��enanpassung der GridPane root
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
