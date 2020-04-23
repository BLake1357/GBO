package gui.mvp.basicquiztable;

import java.io.IOException;

import gui.mvp.basicquiztable.game.QuizPresenter;
import gui.mvp.basicquiztable.game.QuizView;
import gui.mvp.basicquiztable.main.MainPresenter;
import gui.mvp.basicquiztable.main.MainView;
import gui.mvp.basicquiztable.model.Model;
import gui.mvp.basicquiztable.overview.OverviewPresenter;
import gui.mvp.basicquiztable.overview.OverviewView;
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
        mainPresenter.showQuizView();

        // Initialiesung der Scene
        Scene scene = new Scene(mainPresenter.getView(), 700, 500);

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
