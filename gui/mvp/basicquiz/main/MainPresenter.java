package gui.mvp.basicquiz.main;

import gui.mvp.basicquiz.game.QuizPresenter;
import gui.mvp.basicquiz.overview.OverviewPresenter;

public class MainPresenter
{
    private MainView view;

    private OverviewPresenter overviewPresenter;

    private QuizPresenter quizPresenter;

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

    /**
     * Startet ein neues Quiz
     */
    public void restartQuiz()
    {
        this.quizPresenter.restartQuiz();
        this.view.setContent(this.quizPresenter.restartQuiz());
    }

    public void continueQuiz()
    {
        this.view.setContent(this.quizPresenter.continueQuiz());
    }
}
