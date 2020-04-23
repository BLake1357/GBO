package gui.mvp.basicquiz;

import java.io.IOException;

import gui.mvp.basicquiz.game.QuizPresenter;
import gui.mvp.basicquiz.game.QuizView;
import gui.mvp.basicquiz.main.MainPresenter;
import gui.mvp.basicquiz.main.MainView;
import gui.mvp.basicquiz.model.Model;
import gui.mvp.basicquiz.overview.OverviewPresenter;
import gui.mvp.basicquiz.overview.OverviewView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    private MainPresenter initApplication() throws IOException
    {
        String arg = getParameters().getUnnamed().get(0);

        MainPresenter mainPresenter = new MainPresenter();
        MainView mainView = new MainView();
        OverviewPresenter overviewPresenter = new OverviewPresenter();
        OverviewView overviewView = new OverviewView();
        QuizView quizView = new QuizView();
        QuizPresenter quizPresenter = new QuizPresenter();
        Model questionModel = ModelInitializer.initModel(arg);

        mainPresenter.setView(mainView);
        mainPresenter.setOverviewPresenter(overviewPresenter);
        mainPresenter.setQuizPreseter(quizPresenter);
        mainView.setMainPresenter(mainPresenter);

        overviewPresenter.setView(overviewView);
        overviewPresenter.setQuestionModel(questionModel);

        overviewView.setPresenter(overviewPresenter);

        quizPresenter.setView(quizView);
        quizPresenter.setModel(questionModel);

        quizView.setQuizPresenter(quizPresenter);

        return mainPresenter;
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // main Presenter wird initialisiert
        MainPresenter mainPresenter = initApplication();
        mainPresenter.showQuizView(); // Fehler möglicherweise wegen Löschung
                                      // der ids oder Elemente
        mainPresenter.showOverviewView();

        // Initialiesung der Scene
        Scene scene = new Scene(mainPresenter.getView(), 1000, 1000);

        // Stage wird initialisiert
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quiz");
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
