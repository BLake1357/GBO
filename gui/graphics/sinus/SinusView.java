package gui.graphics.sinus;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;

public class SinusView extends VBox
{
    private SinusPresenter sinusPresenter;

    private Path sinusPath;

    private Pane pSinus;

    /**
     * Allgemeiner Konstruktor
     */
    public SinusView(SinusPresenter presenter)
    {
        initView();
        this.sinusPresenter = presenter;
    }

    public void initView()
    {
        this.setPadding(new Insets(10)); // 10 Pixel Abstand zum Rand
        this.setSpacing(10); // 10 Pixel Abstand zwischen den Elementen

        GridPane slPane = new GridPane(); // GridPane für die Slider
        slPane.setPadding(new Insets(10)); // 10 Pixel Abstand zum Rand
        slPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(slPane, Priority.ALWAYS);
        GridPane.setVgrow(slPane, Priority.ALWAYS);

        // Interaktionselmente
        Label lblFormula = new Label();
        lblFormula.setFont(new Font(20));
        Label lblAmplitude = new Label("Amplitude:");
        lblAmplitude.setFont(new Font(20));
        Label lblFrequency = new Label("Frequenz:");
        lblFrequency.setFont(new Font(20));
        Label lblPhase = new Label("Phase:");
        lblPhase.setFont(new Font(20));
        Label lblZoom = new Label("Zoom:");
        lblZoom.setFont(new Font(20));
        Slider sldAmplitude = new Slider(-6, 6, 1);
        sldAmplitude.setId("amplitude");
        Slider sldFrequency = new Slider(0, 40, 2.77);
        sldFrequency.setId("frequency");
        Slider sldPhase = new Slider(-10, 10, 0);
        sldPhase.setId("phase");
        Slider sldZoom = new Slider(10, 210, 100);
        sldZoom.setId("zoom");

        // Konfiguration der Slider
        GridPane.setHgrow(sldAmplitude, Priority.ALWAYS);
        sldAmplitude.setShowTickLabels(true);
        sldAmplitude.setShowTickMarks(true);
        sldAmplitude.setMajorTickUnit(6);
        sldAmplitude.setMinorTickCount(2);
        sldFrequency.setShowTickLabels(true);
        sldFrequency.setShowTickMarks(true);
        sldPhase.setShowTickLabels(true);
        sldPhase.setShowTickMarks(true);
        sldZoom.setShowTickLabels(true);
        sldZoom.setShowTickMarks(true);

        // Path wird initialisiert
        this.sinusPath = new Path();
        this.sinusPath.setStroke(Color.BLACK);
        this.sinusPath.setStrokeWidth(2);
        this.sinusPath.getElements().add(new MoveTo(50, 50));
        this.sinusPath.getElements().add(new LineTo(100, 50));
        this.sinusPath.getElements().add(new LineTo(100, 100));

        // Label wird gesetzt
        lblFormula.setText(sldAmplitude.getValue() + " * sin(" + sldFrequency.getValue() + "* x + " + sldPhase.getValue() + ")");

        // Pane zum zeichen der Sinuskurve
        this.pSinus = new Pane();
        this.pSinus.getChildren().add(this.sinusPath);

        // Listener wird an die Amplitude angefügt
        sldAmplitude.valueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                lblFormula.setText(sldAmplitude.getValue() + " * sin(" + sldFrequency.getValue() + "* x + " + sldPhase.getValue() + ")");
            }
        });

        // Listener wird an die Frequenz angefügt
        sldFrequency.valueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                lblFormula.setText(sldAmplitude.getValue() + " * sin(" + sldFrequency.getValue() + "* x + " + sldPhase.getValue() + ")");
            }
        });

        // Listener wird an die Phase angefügt
        sldPhase.valueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                lblFormula.setText(sldAmplitude.getValue() + " * sin(" + sldFrequency.getValue() + "* x + " + sldPhase.getValue() + ")");
            }
        });

        // Listener wird an den Zoom angefügt
        sldZoom.valueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
            }
        });

        // Slider und Labels werden in die GridPane eingefügt
        slPane.addColumn(0, lblAmplitude, lblFrequency, lblPhase, lblZoom);
        slPane.addColumn(1, sldAmplitude, sldFrequency, sldPhase, sldZoom);

        this.getChildren().addAll(lblFormula, this.pSinus, slPane);
    }

    public void drawSinus(double value)
    {
        for (Node node : this.pSinus.getChildren())
        {
            Line line = (Line) node;

            line.setStrokeWidth(2);
            line.setStroke(Color.BLACK);
            line.setStrokeLineCap(StrokeLineCap.SQUARE);
        }
    }
}
