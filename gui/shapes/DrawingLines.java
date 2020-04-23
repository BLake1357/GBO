package gui.shapes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class DrawingLines extends Application
{
    private Pane root;
    private double x, y;
    
    public void start(Stage primaryStage)
    {
        root = new Pane();
        root.setOnMousePressed
        (
            e -> mousePressed(e.getX(), e.getY())
        );
        root.setOnMouseDragged
        (
            e -> mouseDragged(e.getX(), e.getY())
        );

        primaryStage.setTitle("Freihandzeichnen");
        primaryStage.setScene(new Scene(root, 330, 300));
        primaryStage.show();
    }

    private void mousePressed(double newX, double newY)
    {
        x = newX;
        y = newY;
        mouseDragged(x, y);
    }

    private void mouseDragged(double newX, double newY)
    {
        root.getChildren().add(new Line(x, y, newX, newY));
        x = newX;
        y = newY;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
