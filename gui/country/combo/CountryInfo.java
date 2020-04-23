package gui.country.combo;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class CountryInfo extends Application
{
    private ComboBox<Country> cbCountries;

    private Label lblCountryValue;

    private Label lblCapitalValue;

    private Label lblPeopleValue;

    private Label lblAreaValue;

    private Label lblPopulationValue;

    /**
     * Erzeugung der Oberfläche
     * 
     * @throws Exception
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Country Objekte mit den Informationen zu den Ländern
        Country deCountry = new Country("Deutschland", "Berlin ", 83019213, 357578);
        Country luCountry = new Country("Luxemburg", "Luxemburg (Stadt)", 613894, 2586);
        Country beCountry = new Country("Belgien", "Brüssel", 11376070, 30688);

        // Observable List mit den Country Objekten
        List<Country> list = new ArrayList<Country>();
        ObservableList<Country> olist = FXCollections.observableArrayList(list);
        olist.addAll(deCountry, luCountry, beCountry);

        // Erzeugung der Root Pane auf der alle späteren Panes und/oder Elemente
        // liegen
        GridPane root = new GridPane(); // GridPane als root Pane
        root.setPadding(new Insets(10)); // Abstand des Gitters zum Rand
        root.setVgap(10); // Vertikaler Abstand zu zwischen den Spalten
        root.setHgap(10); // Horizontaler Abstand zwischen den Reihen
        // Listener reagiert auf Änderungen der Höhe
        root.heightProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                RowConstraints row1 = new RowConstraints();
                row1.setMaxHeight(((double) newValue));
                row1.setVgrow(Priority.ALWAYS);
                root.getRowConstraints().setAll(row1, row1, row1, row1, row1, row1, row1, row1);
            }
        });
        // Listener Reagiert auf Änderungen der Breite
        root.widthProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                ColumnConstraints column1 = new ColumnConstraints();
                column1.setMaxWidth((double) newValue);
                column1.setHgrow(Priority.ALWAYS);
                root.getColumnConstraints().setAll(column1, column1, column1, column1);
            }
        });

        // Combobox für die Auswahl der Länder
        this.cbCountries = new ComboBox<Country>();
        this.cbCountries.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        // ID der ComboBox
        this.cbCountries.setId("countrySelector");

        // Konfiguration der ComboBox
        this.cbCountries.setItems(olist);
        this.cbCountries.getSelectionModel().select(0);
        this.cbCountries.setPromptText(cbCountries.getSelectionModel().getSelectedItem().toString());

        // Checkbox für exakte Informationen
        CheckBox cebExInf = new CheckBox();
        cebExInf.setId("exactValues");
        cebExInf.setText("exakte Angaben");

        // Wenn Checkbox Selektiert ist
        cebExInf.selectedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if (newValue)
                {
                    exactValues();
                }
                else
                {
                    lblCountryValue.setText(cbCountries.getSelectionModel().getSelectedItem().getName());
                    lblCapitalValue.setText(cbCountries.getSelectionModel().getSelectedItem().getCapital());
                    lblPeopleValue.setText(cbCountries.getSelectionModel().getSelectedItem().stringRound(cbCountries.getSelectionModel().getSelectedItem().getPeople()));
                    lblAreaValue.setText(cbCountries.getSelectionModel().getSelectedItem().stringRound(cbCountries.getSelectionModel().getSelectedItem().getArea()));
                    lblPopulationValue.setText(cbCountries.getSelectionModel().getSelectedItem().stringRound(cbCountries.getSelectionModel().getSelectedItem().getDensity()));
                }
            }
        });

        // Labels mit Informationen der Länder
        Label lblCountry = new Label("Land:");
        Label lblCapital = new Label("Hauptstadt:");
        Label lblPeople = new Label("Einwohner:");
        Label lblArea = new Label("Fläche (in qkm):");
        Label lblPopulation = new Label("Bevölkerungsdichte (in Personen pro qkm):");

        // Labels für die Werte der Informationen
        this.lblCountryValue = new Label();
        this.lblCountryValue.setId("countryName");
        this.lblCapitalValue = new Label();
        this.lblCapitalValue.setId("capital");
        this.lblPeopleValue = new Label();
        this.lblPeopleValue.setId("population");
        this.lblAreaValue = new Label();
        this.lblAreaValue.setId("area");
        this.lblPopulationValue = new Label();
        this.lblPopulationValue.setId("density");

        // Prüfung der Eingaben auf Gültigkeit

        // Anzeigen des Ersten Landes
        this.lblCountryValue.setText(cbCountries.getSelectionModel().getSelectedItem().getName());
        this.lblCapitalValue.setText(cbCountries.getSelectionModel().getSelectedItem().getCapital());
        this.lblPeopleValue.setText(cbCountries.getSelectionModel().getSelectedItem().stringRound(cbCountries.getSelectionModel().getSelectedItem().getPeople()));
        this.lblAreaValue.setText(cbCountries.getSelectionModel().getSelectedItem().stringRound(cbCountries.getSelectionModel().getSelectedItem().getArea()));
        this.lblPopulationValue.setText(cbCountries.getSelectionModel().getSelectedItem().stringRound(cbCountries.getSelectionModel().getSelectedItem().getDensity()));

        // Listener reagiert auf Änderungen der Objekte in der ComboBox
        this.cbCountries.valueProperty().addListener(new ChangeListener<Object>()
        {

            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue)
            {
                // Abfangen der Exception beim Löschen eines Objekts aus der
                // ComboBox
                if (cbCountries.getSelectionModel().getSelectedItem() == null)
                {
                    cbCountries.getSelectionModel().selectNext();
                }
                // Falls die Checkbox selektiert ist
                else
                {
                    if (cebExInf.isSelected())
                    {
                        try
                        {
                            lblCountryValue.setText(((Country) newValue).getName());
                            lblCapitalValue.setText(((Country) newValue).getCapital());
                            exactValues();
                        }
                        catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    else
                    {
                        lblCountryValue.setText(((Country) newValue).getName());
                        lblCapitalValue.setText(((Country) newValue).getCapital());
                        lblPeopleValue.setText(((Country) newValue).stringRound(((Country) newValue).getPeople()));
                        lblAreaValue.setText(((Country) newValue).stringRound(((Country) newValue).getArea()));
                        lblPopulationValue.setText(((Country) newValue).stringRound(((Country) newValue).getDensity()));
                    }
                }

                try
                {
                    cbCountries.setPromptText(cbCountries.getSelectionModel().getSelectedItem().toString());
                }
                catch (NullPointerException e)
                {
                    cbCountries.setPromptText("Keine L\u00e4nder vorhanden");
                    lblCountryValue.setText("");
                    lblCapitalValue.setText("");
                    lblPeopleValue.setText("");
                    lblAreaValue.setText("");
                    lblPopulationValue.setText("");
                }
            }
        });

        // Textfälder zum Hinzufügen von Ländern
        TextField tfCountry = new TextField();
        tfCountry.setPromptText("Land");
        tfCountry.setId("countryField");

        TextField tfCapital = new TextField();
        tfCapital.setPromptText("Hauptstadt");
        tfCapital.setId("capitalField");

        TextField tfPeople = new TextField();
        tfPeople.setPromptText("Einwohner");
        tfPeople.setId("populationField");

        TextField tfArea = new TextField();
        tfArea.setPromptText("Fläche");
        tfArea.setId("areaField");

        // Buttons zum Hinzufügen und Löschen
        Button btnAdd = new Button();
        btnAdd.setId("add");
        btnAdd.setText("Hinzuf\u00fcgen");

        Button btnDelete = new Button();
        btnDelete.setId("delete");
        btnDelete.setText("L\u00f6schen");

        // Action Methoden der Buttons
        btnAdd.setOnAction(e ->
        {
            try
            {
                // neues Country Objekt wird hinzugefügt
                add(new Country(tfCountry.getText(), tfCapital.getText(), Integer.parseInt(tfPeople.getText()), Integer.parseInt(tfArea.getText())));
                tfCountry.clear();
                tfCapital.clear();
                tfPeople.clear();
                tfArea.clear();
            }
            catch (Exception e2)
            {
                // TODO: handle exception
            }
        });
        btnDelete.setOnAction(e -> delete());

        // Interaktionselemente werden in die Root Pane eingefügt
        root.addColumn(0, this.cbCountries, cebExInf, lblCountry, lblCapital, lblPeople, lblArea, lblPopulation);
        root.add(lblCountryValue, 1, 2);
        root.add(lblCapitalValue, 1, 3);
        root.add(lblPeopleValue, 1, 4);
        root.add(lblAreaValue, 1, 5);
        root.add(lblPopulationValue, 1, 6);
        root.add(tfCountry, 0, 7);
        root.add(tfCapital, 1, 7);
        root.add(tfPeople, 2, 7);
        root.add(tfArea, 3, 7);
        root.add(btnAdd, 4, 7);
        root.add(btnDelete, 0, 8);

        // Erzeugung der Scene
        Scene scene = new Scene(root, 810, 300);

        // Initialisierung der PrimaryStage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Länder-Informationen");
        primaryStage.show();
    }

    /**
     * Gibt die Exakten Werte aus
     */
    public void exactValues()
    {
        try
        {
            this.lblPeopleValue.setText(format(cbCountries.getSelectionModel().getSelectedItem().getPeople()));
            this.lblAreaValue.setText(format(cbCountries.getSelectionModel().getSelectedItem().getArea()));
            this.lblPopulationValue.setText(format(cbCountries.getSelectionModel().getSelectedItem().getDensity()));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Formatierungs Methode
     * 
     * @param value
     * @return
     */
    public String format(double value)
    {
        if (value > 1000000000)
        {
            String erg = "";
            String temp = String.valueOf((int) value);
            for (int i = 0; i < temp.length(); i++)
            {
                if (i == temp.length() - 3 || i == temp.length() - 6 || i == temp.length() - 9)
                {
                    erg += ".";
                }
                erg += temp.charAt(i);
            }

            return erg;
        }
        else if (value > 1000000)
        {
            String erg = "";
            String temp = String.valueOf((int) value);
            for (int i = 0; i < temp.length(); i++)
            {
                if (i == temp.length() - 3 || i == temp.length() - 6)
                {
                    erg += ".";
                }
                erg += temp.charAt(i);
            }

            return erg;
        }
        else if (value > 1000)
        {
            String erg = "";
            String temp = String.valueOf((int) value);
            for (int i = 0; i < temp.length(); i++)
            {
                if (i == temp.length() - 3)
                {
                    erg += ".";
                }
                erg += temp.charAt(i);
            }

            return erg;
        }
        else
        {
            return String.valueOf((int) value);
        }
    }

    /**
     * Hinzufügung der Country Objekte
     * 
     * @param c
     */
    public void add(Country c)
    {
        // Überprüft die Eingabe mit Regular Expressions
        if (c.getName().matches("[\\w a-zA-Z]*") && c.getCapital().matches("[\\w a-zA-Z]*") && String.valueOf(c.getPeople()).matches("[0-9]*") && String.valueOf(c.getArea()).matches("[0-9]*"))
        {
            // Country Objekt wird in die Liste eingefügt
            this.cbCountries.getItems().add(c);

            if (cbCountries.getSelectionModel().getSelectedItem() == null)
            {
                this.cbCountries.getSelectionModel().selectNext();
            }
        }
        else
        {
            System.out.println("false");
        }
    }

    /*
     * Löschen Methode
     */
    public void delete()
    {
        // Objekt wird aus der Liste entfernt
        this.cbCountries.getItems().remove(cbCountries.getSelectionModel().getSelectedItem());
    }

    public static void main(String[] args)
    {
        try
        {
            launch(args);
        }
        catch (Exception e)
        {
            System.out.println(e.getCause() + e.getMessage());
        }
    }
}
