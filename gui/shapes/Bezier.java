package gui.shapes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;

public class Bezier extends Application
{
    private Pane root; // root container

    private CubicCurve bezier; // Bezierkurve

    private double clickedX; // x - Koordinate der Maus

    private double clickedY; // y - Koordinate der Maus

    private Circle cStart; // Startpunkt

    private Circle cEnd; // Endpunkt

    private Circle cControl1; // Kontrollpunkt 1

    private Circle cControl2; // Kontrollpunkt 2

    private Label lblStart; // Text für den Startpunkt

    private Label lblEnd; // Text für den Endpunkt

    private Label lblControl1; // Text für Kontrollpunkt 1

    private Label lblControl2; // Text für Kontrollpunkt 2

    public void start(Stage primaryStage)
    {
        // Pane als root Container
        this.root = new Pane();
        // Aufruf der drawBezier Methode
        drawBezier();
        // Methodenaufruf wenn etwas in der root geklickt oder gedragged wird
        root.setOnMousePressed(e -> clicked(e));
        root.setOnMouseDragged(e -> dragged(e));

        // initialisierung der Stage
        primaryStage.setTitle("Bezier-Kurve");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    /**
     * Zeichnet die Bezier Kurve mit Start- und Endpunkt
     * 
     * @param root
     */
    private void drawBezier()
    {
        // Startpunkt
        this.cStart = new Circle(50, 50, 5, Color.RED);
        this.lblStart = new Label("Start");
        this.lblStart.setTextFill(Color.RED);

        // Endpunkt
        this.cEnd = new Circle(300, 50, 5, Color.RED);
        this.lblEnd = new Label("End");
        this.lblEnd.setTextFill(Color.RED);

        // Erster Kontrollpunkt
        this.cControl1 = new Circle(100, 200, 5, Color.RED);
        this.lblControl1 = new Label("Control Point 1");
        this.lblControl1.setTextFill(Color.RED);

        // Zweiter Kontrollpunkt
        this.cControl2 = new Circle(250, 200, 5, Color.RED);
        this.lblControl2 = new Label("Control Point 2");
        lblControl2.setTextFill(Color.RED);

        // Punkte werden in die Pane eingefügt
        this.root.getChildren().addAll(cStart, cEnd, cControl1, cControl2);
        this.root.getChildren().addAll(lblStart, lblEnd, lblControl1, lblControl2);

        this.bezier = new CubicCurve(50, 50, 100, 200, 250, 200, 300, 50);
        bezier.setStroke(Color.BLACK);
        bezier.setFill(null);
        bezier.setStrokeWidth(3);
        this.root.getChildren().add(bezier);
        // Bezierkurve wird aktualisiert
        this.updateBezier(); // notwendig, da die Labels sonst oben links stehen
    }

    /**
     * Bezierkurve wird aktualisiert
     */
    private void updateBezier()
    {
        // setzen der Startpunkte der Bezierkurve
        this.bezier.setStartX(this.cStart.getCenterX());
        this.bezier.setStartY(this.cStart.getCenterY());
        // setzen der Positionen der Lables lblStart
        this.lblStart.setLayoutX(this.cStart.getCenterX() + 5);
        this.lblStart.setLayoutY(this.cStart.getCenterY());

        // setzen von Kontrollpunkt 1 der Bezierkurve
        this.bezier.setControlX1(this.cControl1.getCenterX());
        this.bezier.setControlY1(this.cControl1.getCenterY());
        // setzen der Position des Labels lblControl1
        this.lblControl1.setLayoutX(this.cControl1.getCenterX() + 5);
        this.lblControl1.setLayoutY(this.cControl1.getCenterY());

        // setzen von Kontrollpunkt 2 der Bezierkurve
        this.bezier.setControlX2(this.cControl2.getCenterX());
        this.bezier.setControlY2(this.cControl2.getCenterY());
        // setzen der Postion des Labels lblControl2
        this.lblControl2.setLayoutX(this.cControl2.getCenterX() + 5);
        this.lblControl2.setLayoutY(this.cControl2.getCenterY());

        // setzen des Endpunktes der Bezierkurve
        this.bezier.setEndX(this.cEnd.getCenterX());
        this.bezier.setEndY(this.cEnd.getCenterY());
        // setzen der Position des Labels lblEnd
        this.lblEnd.setLayoutX(this.cEnd.getCenterX() + 5);
        this.lblEnd.setLayoutY(this.cEnd.getCenterY());
    }

    /**
     * Reagiert auf mouseDragged
     * 
     * @param e
     */
    private void dragged(MouseEvent e)
    {
        double xValue = e.getX() - this.clickedX;
        double yValue = e.getY() - this.clickedY;
        // If fängt den Fall ab, dass der kreis nicht getroffen wird
        if (e.getTarget() instanceof Circle)
        {
            Circle c = (Circle) e.getTarget();
            double newX = c.getCenterX() + xValue;
            double newY = c.getCenterY() + yValue;
            c.setCenterX(newX);
            c.setCenterY(newY);
            this.updateBezier();
        }
        this.clickedX = e.getX();
        this.clickedY = e.getY();
    }

    /**
     * reagiert auf mousePressed
     * 
     * @param e
     */
    private void clicked(MouseEvent e)
    {
        // x und y koordinate werden aktualisiert
        this.clickedX = e.getX();
        this.clickedY = e.getY();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
