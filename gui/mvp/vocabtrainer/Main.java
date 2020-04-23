package gui.mvp.vocabtrainer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Presenter p = new Presenter();
        View v = new View(p);
        v.initView();
        Model m = new Model();
        p.setView(v);
        p.setModel(m);
        // Initialisierung der Scene
        Scene scene = new Scene(v.getUI());
        // Konfiguration der primaryStage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vokabel-Training");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
