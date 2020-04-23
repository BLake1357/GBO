package gui.drawingmenu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FreihandzeichnenMenu extends Application
{
    private Pane graphicsPane;

    private Path currentPath;

    private SimpleDoubleProperty prop;

    private SimpleObjectProperty<Paint> color;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // BorderPane als root Container
        BorderPane root = new BorderPane();
        // Pane als graphicsPane
        this.graphicsPane = new Pane();
        // Menuleiste als Container für das Menu
        MenuBar menuBar = new MenuBar();
        // Clipping
        Rectangle clipRectangle = new Rectangle();
        clipRectangle.heightProperty().bind(graphicsPane.heightProperty());
        clipRectangle.widthProperty().bind(graphicsPane.widthProperty());
        graphicsPane.setClip(clipRectangle);

        // Menus wird in die Menubar eingefügt
        Menu sizeMenu = new Menu("Größe");
        Menu coloMenu = new Menu("Farbe");
        Menu exitMenu = new Menu("Optionen");
        menuBar.getMenus().addAll(sizeMenu, coloMenu, exitMenu);

        // Properties
        prop = new SimpleDoubleProperty(1);
        color = new SimpleObjectProperty<Paint>(Color.BLACK);

        // ToggleGroup
        ToggleGroup tgSize = new ToggleGroup();
        ToggleGroup tgColor = new ToggleGroup();

        // MenuItems
        // Größen
        RadioMenuItem checkMenuItem1 = new RadioMenuItem("1");
        RadioMenuItem checkMenuItem2 = new RadioMenuItem("2");
        RadioMenuItem checkMenuItem3 = new RadioMenuItem("3");
        RadioMenuItem checkMenuItem4 = new RadioMenuItem("4");
        RadioMenuItem checkMenuItem5 = new RadioMenuItem("5");
        // Farben
        RadioMenuItem checkMenuItemBlack = new RadioMenuItem("Schwarz");
        RadioMenuItem checkMenuItemRed = new RadioMenuItem("Rot");
        RadioMenuItem checkMenuItemBlue = new RadioMenuItem("Blau");
        RadioMenuItem checkMenuItemPurple = new RadioMenuItem("Lila");
        RadioMenuItem checkMenuItemOrange = new RadioMenuItem("Orange");
        // Optionen
        MenuItem menuItemExit = new MenuItem("Beenden");
        MenuItem menuItemClear = new MenuItem("Löschen");
        //
        sizeMenu.getItems().addAll(checkMenuItem1, checkMenuItem2, checkMenuItem3, checkMenuItem4, checkMenuItem5);
        //
        coloMenu.getItems().addAll(checkMenuItemBlack, checkMenuItemBlue, checkMenuItemOrange, checkMenuItemPurple, checkMenuItemRed);
        //
        exitMenu.getItems().addAll(menuItemClear, menuItemExit);

        // Einfügen der RadioMenuItems in die ToggelGroups
        // Größen
        checkMenuItem1.setToggleGroup(tgSize);
        checkMenuItem2.setToggleGroup(tgSize);
        checkMenuItem3.setToggleGroup(tgSize);
        checkMenuItem4.setToggleGroup(tgSize);
        checkMenuItem5.setToggleGroup(tgSize);
        // Farben
        checkMenuItemBlack.setToggleGroup(tgColor);
        checkMenuItemRed.setToggleGroup(tgColor);
        checkMenuItemBlue.setToggleGroup(tgColor);
        checkMenuItemPurple.setToggleGroup(tgColor);
        checkMenuItemOrange.setToggleGroup(tgColor);

        // Eventbehandlung
        // Zeichnen
        this.graphicsPane.setOnMousePressed(e -> mousePressed(e.getX(), e.getY()));
        this.graphicsPane.setOnMouseDragged(e -> mouseDragged(e.getX(), e.getY()));
        this.graphicsPane.setOnMouseReleased(e -> mouseReleased());
        // Größen
        checkMenuItem1.setOnAction(e -> changeSize(1));
        checkMenuItem2.setOnAction(e -> changeSize(2));
        checkMenuItem3.setOnAction(e -> changeSize(3));
        checkMenuItem4.setOnAction(e -> changeSize(4));
        checkMenuItem5.setOnAction(e -> changeSize(5));
        // Farben
        checkMenuItemBlack.setOnAction(e -> changeColor(Color.BLACK));
        checkMenuItemRed.setOnAction(e -> changeColor(Color.RED));
        checkMenuItemBlue.setOnAction(e -> changeColor(Color.BLUE));
        checkMenuItemPurple.setOnAction(e -> changeColor(Color.PURPLE));
        checkMenuItemOrange.setOnAction(e -> changeColor(Color.ORANGE));
        // Löschen und Beenden
        menuItemClear.setOnAction(e -> clear());
        menuItemExit.setOnAction(e -> Platform.exit());

        //
        checkMenuItem1.setSelected(true);
        checkMenuItemBlack.setSelected(true);

        // graphicsPane wird in die root eingefügt
        root.setCenter(graphicsPane);
        // MenuBar wird die root eingefügt
        root.setTop(menuBar);

        // Anzeigen der primaryStage
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setTitle("Freihandzeichnen");
        primaryStage.show();
    }

    private void mousePressed(double x, double y)
    {
        currentPath = new Path();
        currentPath.setStroke(Color.BLACK);
        currentPath.setStrokeWidth(2);
        currentPath.getElements().add(new MoveTo(x, y));
        graphicsPane.getChildren().add(currentPath);
    }

    private void mouseDragged(double x, double y)
    {
        currentPath.getElements().add(new LineTo(x, y));
        currentPath.strokeWidthProperty().bind(prop);
        currentPath.strokeProperty().bind(color);

        currentPath.strokeWidthProperty().unbind();
        currentPath.strokeProperty().unbind();
    }

    private void mouseReleased()
    {
        currentPath = null;
    }

    private void changeSize(double newValue)
    {
        prop.set(newValue);
    }

    private void changeColor(Color newValue)
    {
        color.set(newValue);
    }

    private void clear()
    {
        graphicsPane.getChildren().clear();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
