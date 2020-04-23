package gui.graphics.sinus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        SinusPresenter sinusPresenter = new SinusPresenter();
        SinusView sinusView = new SinusView(sinusPresenter);
        SinusModel sinusModel = new SinusModel();

        sinusPresenter.setSinusView(sinusView);
        sinusPresenter.setSinusModel(sinusModel);
        sinusPresenter.calcSin();

        Scene scene = new Scene(sinusPresenter.getSinusView(), 700, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sinus");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
