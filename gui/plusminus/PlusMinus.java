package gui.plusminus;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PlusMinus extends Application
{
    private int counter;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        /**
         * Ergebnis wird in diesem Label angezeigt
         */
        Label lblcounter = new Label();
        lblcounter.setId("counterL");
        lblcounter.setText("0");

        /**
         * Dieser Button erhöht die Zahl die im Label angezeigt wird um eins
         */
        Button btnPlus = new Button();
        btnPlus.setId("plus");
        btnPlus.setText("+");

        /**
         * counter++ oder ähnliches besser auslagern auch änderungen von
         * attributen oder dynamischen elementen auslagern
         */
        btnPlus.setOnAction((ActionEvent e) ->
        {
            this.counter++;
            lblcounter.setText("" + counter);
        });

        /**
         * Dieser Button
         */
        Button btnMinus = new Button();
        btnMinus.setId("minus");
        btnMinus.setText("-");
        btnMinus.setOnAction((ActionEvent e) ->
        {
            this.counter--;
            lblcounter.setText("" + counter);
        });

        GridPane root = new GridPane();
        root.add(btnPlus, 0, 0);
        root.add(lblcounter, 0, 1);
        root.add(btnMinus, 0, 2);

        Scene scene = new Scene(root, 300, 100);

        primaryStage.setScene(scene);
        primaryStage.setTitle("PlusMinus");
        primaryStage.show();
    }

    /**
     * Main Mehtode startet das Programm
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
