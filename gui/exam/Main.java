package gui.exam;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Presenter presenter = new Presenter();
        Model model = new Model();
        View view = new View(presenter);

        presenter.setModel(model);
        presenter.setView(view);

        primaryStage.setScene(new Scene(view));
        primaryStage.setTitle("Ergebnisse");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        try
        {
            launch(args);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
