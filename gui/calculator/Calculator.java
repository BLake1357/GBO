package gui.calculator;

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Calculator extends Application
{
    public void start(Stage primaryStage)
    {
        /**
         * Try Catch block zum abfangen möglicher Ausnahmen. Muss bei FXML
         * erstellt werden.
         */
        try
        {
            /**
             * Scene wird mit Calculator.fxml erstellt.
             */
            Pane root = FXMLLoader.load(getClass().getResource("Calculator.fxml"));

            root.heightProperty().addListener(new ChangeListener<Object>()
            {
                @Override
                public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue)
                {
                    System.out.println("Height: " + oldValue + "=>" + newValue);
                }
            });

            root.widthProperty().addListener(new ChangeListener<Object>()
            {
                @Override
                public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue)
                {
                    System.out.println("Width: " + oldValue + "=>" + newValue);
                }
            });

            Scene scene = new Scene(root);

            /**
             * Stage wird mit Scene besetzt, benannt und angezeigt.
             */
            primaryStage.setTitle("Taschenrechner");
            primaryStage.setScene(scene);
            primaryStage.show();

        }
        catch (IOException e)
        {
            System.out.println(e.getStackTrace() + " " + e.getMessage() + " " + e.getCause());
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}