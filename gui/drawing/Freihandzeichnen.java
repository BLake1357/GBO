package gui.drawing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Freihandzeichnen extends Application
{
    private Pane root;

    private Path currentPath;

    private ComboBox<Paint> colorBox;

    private ComboBox<Number> sizeBox;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // BorderPane als übergeordneter Container
        BorderPane bPane = new BorderPane();

        // Pane als Root Container
        this.root = new Pane();

        // Hbox als Container für die Comboxen und die Buttons
        HBox menuBox = new HBox();
        menuBox.setSpacing(10);

        // Clipping
        Rectangle clipRectangle = new Rectangle();
        clipRectangle.heightProperty().bind(root.heightProperty());
        clipRectangle.widthProperty().bind(root.widthProperty());
        this.root.setClip(clipRectangle);

        // Interaktionselemente
        this.sizeBox = new ComboBox<Number>();
        this.colorBox = new ComboBox<Paint>();
        Button btnDelete = new Button("Löschen");
        // ComboBox Elemente
        sizeBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
        sizeBox.getSelectionModel().select(1); // Index 1 entspricht der 2
        colorBox.getItems().addAll(Color.BLACK, Color.BLUE, Color.RED, Color.ORANGE, Color.PURPLE);
        colorBox.getSelectionModel().select(Color.BLACK);

        // Interaktionselmente werden in die HBox eingefügt
        menuBox.getChildren().addAll(sizeBox, colorBox, btnDelete);

        // Alles wird in die BorderPane eingefügt
        bPane.setTop(menuBox);
        bPane.setCenter(root);

        currentPath = new Path();
        currentPath.strokeWidthProperty().bind(sizeBox.valueProperty());
        currentPath.strokeProperty().bind(colorBox.valueProperty());

        // Eventbehandlung
        this.root.setOnMousePressed(e -> mousePressed(e.getX(), e.getY()));
        this.root.setOnMouseDragged(e -> mouseDragged(e.getX(), e.getY()));
        this.root.setOnMouseReleased(e -> mouseReleased());
        btnDelete.setOnAction(e -> this.root.getChildren().clear());

        primaryStage.setTitle("Freihandzeichnen");
        primaryStage.setScene(new Scene(bPane, 330, 300));
        primaryStage.show();
    }

    private void mousePressed(double x, double y)
    {
        currentPath = new Path();

        currentPath.setStroke(Color.BLACK);
        currentPath.setStrokeWidth(2);
        currentPath.getElements().add(new MoveTo(x, y));
        root.getChildren().add(currentPath);
    }

    private void mouseDragged(double x, double y)
    {
        currentPath.getElements().add(new LineTo(x, y));
        currentPath.strokeWidthProperty().bind(sizeBox.valueProperty());
        currentPath.strokeProperty().bind(colorBox.valueProperty());

        currentPath.strokeWidthProperty().unbind();
        currentPath.strokeProperty().unbind();
    }

    private void mouseReleased()
    {

        currentPath = null;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
