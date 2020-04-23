package gui.mvp.editquiz.main;

import gui.mvp.editquiz.editor.EditorPresenter;
import gui.mvp.editquiz.game.QuizPresenter;
import gui.mvp.editquiz.overview.OverviewPresenter;

public class MainPresenter
{
    private MainView view;

    private OverviewPresenter overviewPresenter;

    private QuizPresenter quizPresenter;

    private EditorPresenter editorPresenter;

    public MainPresenter()
    {
    }

    public void setView(MainView view)
    {
        this.view = view;
    }

    public MainView getView()
    {
        return view;
    }

    public void setOverviewPresenter(OverviewPresenter overviewPresenter)
    {
        this.overviewPresenter = overviewPresenter;
    }

    public void setQuizPreseter(QuizPresenter qPresenter)
    {
        this.quizPresenter = qPresenter;
    }

    public void setEditorPresenter(EditorPresenter editorPresenter)
    {
        this.editorPresenter = editorPresenter;
    }

    public void showMainView()
    {
        this.getView();
    }

    /**
     * Quizview wird Angezeigt
     */
    public void showQuizView()
    {
        view.setContent(quizPresenter.getQuizView());
    }

    /**
     * OverviewView wird angezeigt
     */
    public void showOverviewView()
    {
        this.overviewPresenter.questions();
        view.setContent(overviewPresenter.getView());
    }

    public void showEditorView()
    {
        this.editorPresenter.questions();
        this.view.setContent(editorPresenter.getView());
    }

    /**
     * Startet ein neues Quiz
     */
    public void restartQuiz()
    {
        this.quizPresenter.restartQuiz();
        this.view.setContent(this.quizPresenter.restartQuiz());
    }

    /**
     * Setzt das Quiz an der letzten Stelle fort
     */
    public void continueQuiz()
    {
        this.view.setContent(this.quizPresenter.continueQuiz());
    }
}
