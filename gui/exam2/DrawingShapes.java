package gui.exam2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class DrawingShapes extends Application
{
    private Pane graphicsPane;

    private Shape s;

    private RadioButton rbtnLine, rbtnCircle, rbtnRect;

    private double startX, startY;

    private Label lblStatus;

    private int shapes, circles, lines, rectangles;

    private Protokoll protokoll;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Pane als Root Container
        BorderPane root = new BorderPane();
        // HBox als Container der RadioButtons
        HBox radioBox = new HBox();
        // Pane als Leinwand
        this.graphicsPane = new Pane();
        // Clip
        Rectangle clipRectangle = new Rectangle();
        clipRectangle.widthProperty().bind(graphicsPane.widthProperty());
        clipRectangle.heightProperty().bind(graphicsPane.heightProperty());
        graphicsPane.setClip(clipRectangle);
        // HBox für die StatusZeile
        HBox statusBox = new HBox();
        statusBox.setSpacing(10);

        // Interaktionselemente
        // Togglegroup
        ToggleGroup tgShapes = new ToggleGroup();
        // RadioButtons
        rbtnLine = new RadioButton("Linie");
        rbtnLine.setToggleGroup(tgShapes);
        rbtnCircle = new RadioButton("Kreis");
        rbtnCircle.setToggleGroup(tgShapes);
        rbtnRect = new RadioButton("Rechteck");
        rbtnRect.setToggleGroup(tgShapes);
        // Line wird selektiert
        rbtnLine.setSelected(true);
        // Labels
        lblStatus = new Label();
        lblStatus.setText("Linien: " + lines + ", Kreise: " + circles + ", Rechtecke: " + rectangles + ", insgesamt: " + shapes);
        // Buttons
        Button btnProtocol = new Button("Protokoll");
        Button btnDelete = new Button("Löschen");

        // initialisierung der Protokolls
        protokoll = new Protokoll();

        // Eventbehandlung
        btnDelete.setOnAction(e ->
        {
            // Löschen aller Shapes auf der graphicsPane
            graphicsPane.getChildren().clear();
            // Löschen der Statuszeile
            clearValues();
        });

        // Öffnet das neue Protokoll Fenster
        btnProtocol.setOnAction(e -> protokoll.show());

        // Elemente werden in die statusBox eingefügt
        statusBox.getChildren().addAll(lblStatus, btnProtocol);

        // Einfügen der RadioButtons in die HBox
        radioBox.getChildren().addAll(rbtnLine, rbtnCircle, rbtnRect, btnDelete);

        // Eventbehandlung
        this.graphicsPane.setOnMousePressed(e -> mousePressed(e.getX(), e.getY()));
        this.graphicsPane.setOnMouseDragged(e -> mouseDragged(e.getX(), e.getY()));
        this.graphicsPane.setOnMouseReleased(e -> mouseReleased());

        // Elemente werden in die root eingefügt
        root.setCenter(graphicsPane);
        root.setTop(radioBox);
        root.setBottom(statusBox);

        // Stage wird initialisiert
        primaryStage.setScene(new Scene(root, 400, 150));
        primaryStage.setTitle("Zeichnen von Formen");
        primaryStage.show();
    }

    private void mousePressed(double x, double y)
    {
        // Wenn Linie selektiert ist
        if (rbtnLine.isSelected())
        {
            s = new Line(x, y, x, y);
            lines++;
        }
        // Wenn Kreis selektiert ist
        else if (rbtnCircle.isSelected())
        {
            s = new Circle(x, y, 0);
            circles++;
        }
        // Wenn Rechteck selektiert ist
        else if (rbtnRect.isSelected())
        {
            s = new Rectangle(x, y, 0, 0);
            startX = x;
            startY = y;

            rectangles++;
        }
        s.setStroke(Color.GREY);
        s.setStrokeWidth(0.5);
        s.setFill(null);
        graphicsPane.getChildren().add(s);

    }

    private void mouseDragged(double x, double y)
    {
        if (s instanceof Line)
        {
            Line l = (Line) s;
            l.setEndX(x);
            l.setEndY(y);
        }
        else if (s instanceof Circle)
        {
            Circle c = (Circle) s;
            double centerX = c.getCenterX();
            double centerY = c.getCenterY();
            double r = Math.sqrt((centerY - y) * (centerY - y) + (centerX - x) * (centerX - x));
            c.setRadius(r);
        }
        else if (s instanceof Rectangle)
        {
            Rectangle r = (Rectangle) s;

            double width = x - startX;
            // Fallunterscheidung positive und negative breite
            if (width < 0)
            {
                r.setX(x);
                r.setWidth(-width);
            }
            else
            {
                r.setX(startX);
                r.setWidth(width);
            }

            double height = y - startY;
            // Fallunterscheidung positive und negative höhe
            if (height < 0)
            {
                r.setY(y);
                r.setHeight(-height);
            }
            else
            {
                r.setY(startY);
                r.setHeight(height);
            }
        }
    }

    private void mouseReleased()
    {
        s.setStroke(Color.BLACK);
        s.setStrokeWidth(2);

        shapes++;
        updateStatus();
        protokoll.updateProtokoll(s.toString() + "\n");
    }

    private void updateStatus()
    {
        lblStatus.setText("Linien: " + lines + ", Kreise: " + circles + ", Rechtecke: " + rectangles + ", insgesamt: " + shapes);
    }

    public void clearValues()
    {
        this.circles = 0;
        this.lines = 0;
        this.rectangles = 0;
        this.shapes = 0;

        updateStatus();
        protokoll.clearProtokoll();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
