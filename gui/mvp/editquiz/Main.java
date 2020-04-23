package gui.mvp.editquiz;

import java.io.IOException;

import gui.mvp.editquiz.editor.EditorPresenter;
import gui.mvp.editquiz.editor.EditorView;
import gui.mvp.editquiz.game.QuizPresenter;
import gui.mvp.editquiz.game.QuizView;
import gui.mvp.editquiz.main.MainPresenter;
import gui.mvp.editquiz.main.MainView;
import gui.mvp.editquiz.model.Model;
import gui.mvp.editquiz.overview.OverviewPresenter;
import gui.mvp.editquiz.overview.OverviewView;
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
        Model model = ModelInitializer.initModel(arg);
        EditorPresenter editorPresenter = new EditorPresenter();
        EditorView editorView = new EditorView();

        mainPresenter.setView(mainView);
        mainPresenter.setOverviewPresenter(overviewPresenter);
        mainPresenter.setQuizPreseter(quizPresenter);
        mainPresenter.setEditorPresenter(editorPresenter);
        mainView.setMainPresenter(mainPresenter);

        overviewPresenter.setView(overviewView);
        overviewPresenter.setQuestionModel(model);

        overviewView.setPresenter(overviewPresenter);

        quizPresenter.setView(quizView);
        quizPresenter.setModel(model);

        quizView.setQuizPresenter(quizPresenter);

        editorPresenter.setQuestionModel(model);
        editorPresenter.setView(editorView);

        editorView.setPresenter(editorPresenter);

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
